import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table'

@Component({
  selector: 'app-rents',
  standalone: true,
  imports: [CommonModule, TableModule],
  templateUrl: './rents.component.html',
  styleUrl: './rents.component.css'
})
export class RentsComponent {

}
