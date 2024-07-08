import { Component } from '@angular/core';
import { Client } from '../_model/Client';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ButtonModule } from 'primeng/button';
import { ClientService } from '../_service/client.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, InputTextModule, FloatLabelModule, ButtonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor( private clientService: ClientService ){}
  client: Client = {
    id: 0,
    name: '',
    email: '',
    password: '',
    createdAt: new Date(),
    updatedAt: new Date(),
    rents: []
  };

  addClient(): void {
    this.clientService.registerClient(this.client).subscribe(d => {
      if(this.client.name == '' || this.client.email == '' || this.client.password == ''){
        console.log('Please fill all fields');
        return;
      }
      console.log(d);
  })
   /*  this.clientService.create(this.client).subscribe((data) => {
      console.log(data);
    }); */
  }

}
