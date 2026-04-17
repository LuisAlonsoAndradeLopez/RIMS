import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { provideHttpClient, withInterceptorsFromDi, HTTP_INTERCEPTORS } from '@angular/common/http';

import {
  MsalService,
  MsalGuard,
  MsalInterceptor,
  MSAL_INSTANCE,
  MSAL_GUARD_CONFIG,
  MSAL_INTERCEPTOR_CONFIG,
} from '@azure/msal-angular';

import { PublicClientApplication, InteractionType } from '@azure/msal-browser';

import { routes } from './app.routes';

export function MSALInstanceFactory() {
  return new PublicClientApplication({
    auth: {
      clientId: 'd25f3483-ca1b-44e3-b398-03dda592d50c',

      authority: 'https://login.microsoftonline.com/ebecc4e4-d876-48e1-848a-880f6d71bdad',

      redirectUri: 'http://localhost:4200',
    },
  });
}

export function MSALGuardConfigFactory() {
  return {
    interactionType: InteractionType.Redirect,

    authRequest: {
      scopes: ['api://73966a65-f6dc-46f5-b554-8c84028613f8/user-access'],
    },
  };
}

export function MSALInterceptorConfigFactory() {
  const protectedResourceMap = new Map<string, Array<string>>();

  protectedResourceMap.set(
    'http://localhost:8081/api',

    ['api://73966a65-f6dc-46f5-b554-8c84028613f8/user-access'],
  );

  return {
    interactionType: InteractionType.Redirect,

    protectedResourceMap,
  };
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),

    provideHttpClient(withInterceptorsFromDi()),

    {
      provide: MSAL_INSTANCE,
      useFactory: MSALInstanceFactory,
    },

    {
      provide: MSAL_GUARD_CONFIG,
      useFactory: MSALGuardConfigFactory,
    },

    {
      provide: MSAL_INTERCEPTOR_CONFIG,
      useFactory: MSALInterceptorConfigFactory,
    },

    MsalService,
    MsalGuard,

    // ⭐ CRITICAL FIX
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
  ],
};
