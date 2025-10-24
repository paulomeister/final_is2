import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './header/header';
import { InfoDisplayComponent } from './info-display/info-display';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, InfoDisplayComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('serviCiudad-frontend');
}
