import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MenuModule } from 'primeng/menu';
import { MenuItem, MessageService } from 'primeng/api';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule, MenuModule, DialogModule, ButtonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  items: MenuItem[] | undefined;
  constructor(private router: Router, private messageService: MessageService) {}
  logoutDialogVisible: boolean = false;
  showSessionDialog() {
    this.logoutDialogVisible = true;
  }
  closeSessionDialog() {
    this.logoutDialogVisible = false;
  }
  handleLogOut() {
    sessionStorage.clear();
    // window.location.reload();
    this.router.navigate(['login']);
    this.messageService.add({
      severity: 'success',
      summary: 'Sesión cerrada',
      detail: 'Has cerrado la sesión correctamente',
    });
  }
  ngOnInit(): void {
    this.items = [
      {
        label: 'Inicio',
        icon: 'pi pi-home',
        command: () => this.router.navigate(['']),
      },
      {
        label: 'Gruas',
        icon: 'pi pi-truck',
        command: () => this.router.navigate(['dashboard/gruas']),
      },
      {
        label: 'Rentas',
        icon: 'pi pi-address-book',
        command: () => this.router.navigate(['dashboard/rentas']),
      },
      {
        label: 'Salir',
        icon: 'pi pi-times',
        command: () => {
          this.showSessionDialog();
          //   sessionStorage.clear();
          //   // window.location.reload();
          //   this.router.navigate(['login']);
          //   this.messageService.add({
          //     severity: 'success',
          //     summary: 'Sesión cerrada',
          //     detail: 'Has cerrado la sesión correctamente',
          //   });
        },
      },
    ];
  }
}
