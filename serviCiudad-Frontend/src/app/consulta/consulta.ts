import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, DeudaConsolidadaResponse } from '../api.service';

@Component({
  selector: 'app-consulta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './consulta.html',
  styleUrls: ['./consulta.css']
})
export class ConsultaComponent {
  
  // Variables del formulario
  cedula: string = '';
  isLoading: boolean = false;
  
  // Variables para los datos
  deudaData: DeudaConsolidadaResponse | null = null;
  errorMessage: string = '';
  
  constructor(private apiService: ApiService) {}

  /**
   * Realiza la consulta de deuda consolidada
   */
  buscarDeuda(): void {
    // Validar que se haya ingresado una cédula
    if (!this.cedula || this.cedula.trim() === '') {
      this.errorMessage = 'Por favor, ingrese un número de cédula.';
      this.deudaData = null;
      return;
    }

    // Limpiar datos anteriores y errores
    this.deudaData = null;
    this.errorMessage = '';
    this.isLoading = true;

    // Realizar la consulta
    this.apiService.consultarDeudaConsolidada(this.cedula.trim())
      .subscribe({
        next: (data) => {
          this.deudaData = data;
          this.isLoading = false;
        },
        error: (error) => {
          this.errorMessage = error.message;
          this.deudaData = null;
          this.isLoading = false;
        }
      });
  }

  /**
   * Limpia el formulario y los datos mostrados
   */
  limpiar(): void {
    this.cedula = '';
    this.deudaData = null;
    this.errorMessage = '';
    this.isLoading = false;
  }

  /**
   * Formatea un número como moneda colombiana
   * @param value - Valor numérico a formatear
   * @returns String formateado como moneda
   */
  formatearMoneda(value: number): string {
    return new Intl.NumberFormat('es-CO', {
      style: 'currency',
      currency: 'COP',
      minimumFractionDigits: 2
    }).format(value);
  }

  /**
   * Formatea una fecha ISO a formato legible
   * @param fechaISO - Fecha en formato ISO
   * @returns String con fecha formateada
   */
  formatearFecha(fechaISO: string): string {
    try {
      const fecha = new Date(fechaISO);
      return fecha.toLocaleDateString('es-CO', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      });
    } catch (error) {
      return fechaISO;
    }
  }
}
