import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ButtonModule } from 'primeng/button';
import { ClientLogin } from '../_model/Client';
import { ClientService } from '../_service/client.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, InputTextModule, FloatLabelModule, ButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  constructor(private clientService: ClientService) {}
  clientLogin: ClientLogin = {
    email: '',
    password: '',
  };
  loginClient(): void {
    if (!this.clientLogin.email || !this.clientLogin.password) {
      console.log('Please enter email and password');
      return;
    }
    this.clientService.loginClient(this.clientLogin).subscribe((res: any) => {
      sessionStorage.setItem('token', res.token);
    });
  }
}
