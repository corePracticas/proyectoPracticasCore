import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ButtonModule } from 'primeng/button';
import { ClientLogin } from '../_model/Client';
import { ClientService } from '../_service/client.service';
import { MessageService } from 'primeng/api';
import { catchError, throwError } from 'rxjs';
import { ApiJwtResponse } from '../_model/Api';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, InputTextModule, FloatLabelModule, ButtonModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  constructor(
    private clientService: ClientService,
    private messageService: MessageService,
    private router: Router
  ) {}
  clientLogin: ClientLogin = {
    email: '',
    password: '',
  };
  backgroundImage: string = 'background-image: url("https://picsum.photos/832/1280");';
  showToastError(msg: string) {
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: msg,
    });
  }
  showToastSuccess(msg: string) {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: msg,
    });
  }
  loginClient(): void {
    if (!this.clientLogin.email || !this.clientLogin.password) {
      this.showToastError('Por favor rellene todos los campos');
      return;
    }
    this.clientService
      .loginClient(this.clientLogin)
      .pipe(
        catchError((response: any) => {
          const validationErrors: string[] = response.error.errors || [];
          const errorMessage: string =
            response.error.message || 'Un error ha al iniciar sesion';
          if (validationErrors.length > 0) {
            validationErrors.forEach((e: string, index: number) => {
              this.showToastError(e);
            });
          }
          if (errorMessage) {
            this.showToastError(errorMessage);
          }
          return throwError(() => response);
        })
      )
      .subscribe((res: any) => {
        const response: ApiJwtResponse = res;
        if (response.success) {
          sessionStorage.setItem('token', res.token);
          sessionStorage.setItem('username', res.username);
          this.showToastSuccess(res.message);
          this.router.navigate(['/']);
        }
        return res;
      });
  }
}
