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
    await this.msalService.instance.initialize();

    const result = await this.msalService.instance.handleRedirectPromise();

    if (result?.account) {
      this.msalService.instance.setActiveAccount(result.account);
    }

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

  async callApi() {
    const account = this.msalService.instance.getActiveAccount();

    if (!account) {
      console.log('User not logged in');
      return;
    }

    try {
      const result = await this.msalService.instance.acquireTokenSilent({
        account: account,
        scopes: ['api://73966a65-f6dc-46f5-b554-8c84028613f8/user-access'],
      });

      const headers = {
        Authorization: `Bearer ${result.accessToken}`,
      };

      this.http.get('http://localhost:8081/api/users', { headers }).subscribe({
        next: (response) => {
          console.log('API Response:', response);
        },

        error: (error) => {
          console.error('API Error:', error);
        },
      });
    } catch (error) {
      console.error('Token Error:', error);
    }
  }
}
