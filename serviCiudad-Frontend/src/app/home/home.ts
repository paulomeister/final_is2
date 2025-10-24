import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, DeudaConsolidadaResponse } from '../api.service';
import { TestApiComponent } from '../test-api';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule, TestApiComponent],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  cedula: string = '';
  consultaData: DeudaConsolidadaResponse | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  showResults: boolean = false;

  constructor(private apiService: ApiService) {}

  consultar(): void {
    if (!this.cedula.trim()) {
      this.errorMessage = 'Por favor ingrese un número de cédula válido';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.showResults = false;
    this.consultaData = null;

    this.apiService.consultarDeudaConsolidada(this.cedula.trim()).subscribe({
      next: (data: DeudaConsolidadaResponse) => {
        console.log('Home Component - Received data:', data);
        this.consultaData = data;
        this.showResults = true;
        this.isLoading = false;
      },
      error: (error: Error) => {
        console.error('Home Component - Error:', error);
        this.errorMessage = error.message;
        this.isLoading = false;
        this.showResults = false;
      }
    });
  }

  limpiarConsulta(): void {
    this.cedula = '';
    this.consultaData = null;
    this.errorMessage = '';
    this.showResults = false;
  }

  formatCurrency(value: number): string {
    return new Intl.NumberFormat('es-CO', {
      style: 'currency',
      currency: 'COP',
      minimumFractionDigits: 0
    }).format(value);
  }
}
