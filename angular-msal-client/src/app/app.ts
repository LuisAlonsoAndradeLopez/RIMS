import { Component, OnInit, inject } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.html',
})
export class App implements OnInit {
  private msalService = inject(MsalService);

  loginInProgress = false;

  userEmail: string | null = null;

  private http = inject(HttpClient);

  async ngOnInit() {
    // Initialize MSAL
    await this.msalService.instance.initialize();

    // Handle login redirect
    const result = await this.msalService.instance.handleRedirectPromise();

    if (result?.account) {
      // Set active account from redirect
      this.msalService.instance.setActiveAccount(result.account);
    }

    // Restore existing session
    this.restoreUser();
  }

  restoreUser() {
    const activeAccount = this.msalService.instance.getActiveAccount();

    if (activeAccount) {
      this.userEmail = activeAccount.username;
      return;
    }

    const accounts = this.msalService.instance.getAllAccounts();

    if (accounts.length > 0) {
      const account = accounts[0];

      this.msalService.instance.setActiveAccount(account);

      this.userEmail = account.username;
    }
  }

  async login() {
    if (this.loginInProgress) return;

    this.loginInProgress = true;

    await this.msalService.loginRedirect();
  }

  logout() {
    this.userEmail = null;

    this.msalService.logoutRedirect();
  }

  callApi() {
    const account = this.msalService.instance.getActiveAccount();

    if (!account) {
      console.log('User not logged in');
      return;
    }

    this.http.get('http://localhost:8081/api/users').subscribe({
      next: (response) => {
        console.log('API Response:', response);
      },

      error: (error) => {
        console.error('API Error:', error);
      },
    });
  }
}
