import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { MenubarModule } from 'primeng/menubar';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule, CardModule, MenubarModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  items: MenuItem[] = [];
  mail: string = "contacto@alquilergruas.com";
  backgroundAboutImg: string = 'background-image: url("https://picsum.photos/640/480");';
  constructor( 
    private router: Router
   ){}
  ngOnInit() {
    this.items = [
      {
        label: 'Inicio',
        icon: 'pi pi-fw pi-home',
        command: () => this.scrollTo('inicio')
      },
      {
        label: 'Servicios',
        icon: 'pi pi-fw pi-cog',
        command: () => this.scrollTo('servicios')
      },
      {
        label: 'Sobre Nosotros',
        icon: 'pi pi-fw pi-info-circle',
        command: () => this.scrollTo('about')
      },
      {
        label: 'Contacto',
        icon: 'pi pi-fw pi-envelope',
        command: () => this.scrollTo('contacto')
      },
      {
        label: 'Alquilar',
        icon: 'pi pi-fw pi-car',
        command: () => this.router.navigate(['dashboard'])
      }
    ];
  }
  scrollTo(section: string) {
    const element = document.getElementById(section);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
