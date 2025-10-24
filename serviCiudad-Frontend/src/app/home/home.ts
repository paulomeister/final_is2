import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, DeudaConsolidadaResponse } from '../api.service';
// import { TestApiComponent } from '../test-api'; // Hidden for production

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule], // TestApiComponent removed for production
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  cedula: string = '';
  consultaData: DeudaConsolidadaResponse | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private apiService: ApiService) {}

  consultar(): void {
    if (!this.cedula.trim()) {
      this.errorMessage = 'Por favor ingrese un número de cédula válido';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.consultaData = null;

    this.apiService.consultarDeudaConsolidada(this.cedula.trim()).subscribe({
      next: (data: any) => {
        this.consultaData = data as DeudaConsolidadaResponse;
        this.isLoading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.isLoading = false;
      }
    });
  }

  limpiarConsulta(): void {
    this.cedula = '';
    this.consultaData = null;
    this.errorMessage = '';
  }

  formatCurrency(value: number): string {
    return new Intl.NumberFormat('es-CO', {
      style: 'currency',
      currency: 'COP',
      minimumFractionDigits: 0
    }).format(value);
  }

  // Demo data method for testing
  testSetData(): void {
    this.consultaData = {
      clienteId: '1234500002',
      nombreCliente: 'Andrés Martínez',
      fechaConsulta: '2025-10-24T10:00:00Z',
      energia: {
        periodo: '202510',
        consumo: '56 kWh',
        valorPagar: 1120.00
      },
      acueducto: {
        periodo: '202508',
        consumo: '31 m³',
        valorPagar: 205575.83
      },
      totalAPagar: 206695.83
    };
    this.isLoading = false;
    this.errorMessage = '';
  }
}
