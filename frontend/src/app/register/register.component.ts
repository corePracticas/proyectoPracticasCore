import { Component } from '@angular/core';
import { Client } from '../_model/Client';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ButtonModule } from 'primeng/button';
import { ClientService } from '../_service/client.service';
import { MessageService } from 'primeng/api';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, InputTextModule, FloatLabelModule, ButtonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  constructor(
    private clientService: ClientService,
    private messageService: MessageService
  ) {}
  client: Client = {
    id: 0,
    name: '',
    email: '',
    password: '',
    createdAt: new Date(),
    updatedAt: new Date(),
    rents: [],
  };
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
  addClient(): void {
    this.clientService.registerClient(this.client)
    .pipe(
      catchError((response: any) => {
        const validationErrors: string[] = response.error.errors || [];
        const errorMessage: string = response.error.message || 'Un error ha ocurrido al registrarse';
        if(validationErrors.length > 0){
          validationErrors.forEach((e: string, index: number) => {
            this.showToastError(e);
          });
        }
        if(errorMessage.length > 60){
          this.showToastError('El correo ya esta registrado');
        }
        if(errorMessage && errorMessage !== 'Validation error' && errorMessage.length < 100){
          this.showToastError(errorMessage);
        }
        return throwError(() => response);
      })
    )
    .subscribe((d) => {
      if (
        this.client.name == '' ||
        this.client.email == '' ||
        this.client.password == ''
      ) {
        this.showToastError('Please fill all fields');
        return;
      }
      this.showToastSuccess('Registrado correctamente');
    });
    /*  this.clientService.create(this.client).subscribe((data) => {
      console.log(data);
    }); */
  }
}
