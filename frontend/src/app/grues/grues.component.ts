import { Component, OnInit } from '@angular/core';
import { Grue } from '../_model/Grue';
import { GruasService } from '../_service/gruas.service';
import { ButtonModule } from 'primeng/button';
import { RentasService } from '../_service/rentas.service';
import { ClientesService } from '../_service/clientes.service';
import { Client } from '../_model/Client';
import { Rent, RentForm, RentStatus } from '../_model/Rent';
import { MessageService } from 'primeng/api';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { InputNumberModule } from 'primeng/inputnumber';

@Component({
  selector: 'app-grues',
  standalone: true,
  imports: [ButtonModule, FormsModule, DialogModule, InputTextModule, FloatLabelModule, InputNumberModule],
  templateUrl: './grues.component.html',
  styleUrl: './grues.component.css',
})
export class GruesComponent implements OnInit {
  $listaGruas: Grue[] = [];
  $currentClient: Client | null = null;
  selectedGrueId: number  = 0;
  rentForm: RentForm = {
    startDate: null,
    endDate: null,
    months: 0
  }
  isFormOpen: boolean = false;
  email: string | null = sessionStorage.getItem('username');
  // username:
  constructor(
    private gruaService: GruasService,
    private rentService: RentasService,
    private clientesService: ClientesService,
    private messageService: MessageService
  ) {}
  ngOnInit(): void {
    this.gruaService.getAllGrues().subscribe((gruas: Grue[]) => {
      this.$listaGruas = gruas;
    });
    this.clientesService.getCurrentClient().subscribe(client => {
      this.$currentClient = client;
    })
  }
  handleRentGrue(): void {
    if(!this.$currentClient) return;
    this.gruaService.getGrueById(this.selectedGrueId).subscribe(grue => {
      const rentToAdd: Rent = {
        client: this.$currentClient,
        createdAt: new Date(),
        startDate: this.rentForm.startDate,
        updatedAt: new Date(),
        endDate: this.rentForm.endDate,
        grue: grue,
        status: RentStatus.PENDING,
        totalPrice: grue.pricePerMonth  * this.rentForm.months,
      }
      this.closeFormDialog()
      this.messageService.add({
        severity: 'success',
        summary: 'Petición recibida',
        detail: 'Hemos recibido tu solicitud correctamente'
      })
      this.rentForm = {
        startDate: null,
        endDate: null,
        months: 0
      }
      this.rentService.addRent(rentToAdd).subscribe(rent => {
        console.log(rent);
        this.messageService.add({
          severity: 'success',
          summary: 'Grua rentada',
          detail: 'Has rentado una grúa correctamente'
        })
      })
    })
  }
  openFormDialog(id: number){
    this.isFormOpen = true;
    this.selectedGrueId = id;
  }
  closeFormDialog(){
    this.isFormOpen = false;
  }
}
