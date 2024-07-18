import { Component, OnInit } from '@angular/core';
import { Grue } from '../_model/Grue';
import { GruasService } from '../_service/gruas.service';
import { ButtonModule } from 'primeng/button';
import { RentasService } from '../_service/rentas.service';
import { ClientesService } from '../_service/clientes.service';
import { Client } from '../_model/Client';
import { Rent, RentStatus } from '../_model/Rent';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-grues',
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './grues.component.html',
  styleUrl: './grues.component.css',
})
export class GruesComponent implements OnInit {
  $listaGruas: Grue[] = [];
  $currentClient: Client | null = null;
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
  handleRentGrue(id: number): void {
    if(!this.$currentClient) return;
    this.gruaService.getGrueById(id).subscribe(grue => {
      const rentToAdd: Rent = {
        client: this.$currentClient,
        // TODO: Estas fechas deben ser modificadas en un modal
        createdAt: new Date(),
        startDate: new Date(),
        updatedAt: new Date(),
        endDate: new Date() /* + 7  days*/,
        // TODO: Estas fechas deben ser modificadas en un modal
        grue: grue,
        status: RentStatus.PENDING,
        // TODO: El precio debe ser calculado en base a los meses
        totalPrice: grue.pricePerMonth /* * months */,
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
}
