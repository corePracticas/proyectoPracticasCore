import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GruasService } from '../_service/gruas.service';
import { RentasService } from '../_service/rentas.service';
import { ClientService } from '../_service/client.service';
import { ClientesService } from '../_service/clientes.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  constructor( 
    private grueService: GruasService,
    private rentService: RentasService,
    private clientService: ClientesService
   ){}
  ngOnInit(): void {
    this.grueService.getAllGrues().subscribe(d => {
      console.log('GRUAS',d)
    })
    this.rentService.getAllRents().subscribe(d => {
      console.log('RENTAS', d)
    })
    this.clientService.getAllClients().subscribe(d => {
      console.log('Clientes', d)
    })
    this.clientService.getClientById(1).subscribe(d => {
      console.log('Cliente por id', d)
    })
  }
}
