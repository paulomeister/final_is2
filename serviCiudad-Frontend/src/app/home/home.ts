import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Consulta, ConsultaResponse } from '../consulta';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  cedula: string = '';
  consultaData: ConsultaResponse | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  showResults: boolean = false;

  constructor(private consultaService: Consulta) {}

  consultar(): void {
    if (!this.cedula.trim()) {
      this.errorMessage = 'Por favor ingrese un número de cédula válido';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.showResults = false;
    this.consultaData = null;

    this.consultaService.consultarCedula(this.cedula.trim()).subscribe({
      next: (data: ConsultaResponse) => {
        this.consultaData = data;
        this.showResults = true;
        this.isLoading = false;
      },
      error: (error: Error) => {
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
