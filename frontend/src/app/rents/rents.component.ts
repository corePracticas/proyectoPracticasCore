import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table'
import { Rent } from '../_model/Rent';
import { RentasService } from '../_service/rentas.service';

@Component({
  selector: 'app-rents',
  standalone: true,
  imports: [CommonModule, TableModule],
  templateUrl: './rents.component.html',
  styleUrl: './rents.component.css'
})
export class RentsComponent implements OnInit{
  $rentsList: Rent[]  = [];
  constructor(private rentService: RentasService) {}
  ngOnInit(): void {
    this.rentService.getRentByCurrentUser().subscribe(rents => {
      this.$rentsList = rents;
    });
  }
}
