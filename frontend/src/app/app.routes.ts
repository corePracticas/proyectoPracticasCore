import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { authGuard } from './guards/auth.guard';
import { GruesComponent } from './grues/grues.component';
import { RentsComponent } from './rents/rents.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent, canActivate: [authGuard]},
  {path: 'dashboard', component: DashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: 'gruas', component: GruesComponent },
      { path: 'rentas', component: RentsComponent },
      { path: '', pathMatch: 'full', component: GruesComponent},
    ]
  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: '**', redirectTo: ''}
];
