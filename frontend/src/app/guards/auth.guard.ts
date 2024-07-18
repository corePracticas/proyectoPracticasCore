import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ClientService } from '../_service/client.service';

export const authGuard: CanActivateFn = (route, state) => {
  const clientService = inject(ClientService);
  const router = inject(Router);

  const token = sessionStorage.getItem('token');
  const username = sessionStorage.getItem('username');

  if (token && username) {
    return clientService.getSessionStatus(username, token).pipe(
      map((response: any) => {
        if (response.success) {
          return true;
        } else {
          router.navigate(['/login']);
          return false;
        }
      }),
      catchError(() => {
        return of(false);
      })
    );
  } else {
    router.navigate(['/login']);
    return of(false);
  }
};
