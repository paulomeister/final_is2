import { Routes } from '@angular/router';
import { Home } from './home/home';
import { About } from './about/about';
import { ConsultaComponent } from './consulta/consulta';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: Home },
  { path: 'about', component: About },
  // { path: 'consulta', component: ConsultaComponent }, // Hidden for production
  { path: '**', redirectTo: '/home' }
];
