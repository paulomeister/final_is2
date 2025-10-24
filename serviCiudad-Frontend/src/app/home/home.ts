import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, DeudaConsolidadaResponse } from '../api.service';
import { DataService } from '../data.service';
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

  constructor(
    private apiService: ApiService,
    private dataService: DataService
  ) {}

  consultar(): void {
    if (!this.cedula.trim()) {
      this.dataService.setError('Por favor ingrese un número de cédula válido');
      return;
    }

    this.dataService.setLoading(true);
    this.dataService.setError('');
    this.dataService.updateData(null);

    this.apiService.consultarDeudaConsolidada(this.cedula.trim()).subscribe({
      next: (data: any) => {
        console.log('Received API data:', data);
        console.log('Data type:', typeof data);
        console.log('Data keys:', Object.keys(data));
        
        // Check if data has the expected structure
        if (data && typeof data === 'object') {
          this.dataService.updateData(data as DeudaConsolidadaResponse);
          console.log('Updated DataService with:', data);
        } else {
          console.error('Invalid data structure received:', data);
          this.dataService.setError('Formato de datos inválido recibido del servidor');
        }
        this.dataService.setLoading(false);
      },
      error: (error: Error) => {
        console.error('API Error:', error);
        this.dataService.setError(error.message);
        this.dataService.setLoading(false);
      }
    });
  }

  limpiarConsulta(): void {
    this.cedula = '';
    this.dataService.clearData();
  }

  formatCurrency(value: number): string {
    return new Intl.NumberFormat('es-CO', {
      style: 'currency',
      currency: 'COP',
      minimumFractionDigits: 0
    }).format(value);
  }

  // Demo data method for testing - now calls actual API
  testSetData(): void {
    // Use a demo cedula to fetch real data from API
    const demoCedula = '1234500002';
    this.cedula = demoCedula;
    
    this.dataService.setLoading(true);
    this.dataService.setError('');
    this.dataService.updateData(null);

    this.apiService.consultarDeudaConsolidada(demoCedula).subscribe({
      next: (data: any) => {
        console.log('Received API data:', data);
        console.log('Data type:', typeof data);
        console.log('Data keys:', Object.keys(data));
        
        // Check if data has the expected structure
        if (data && typeof data === 'object') {
          this.dataService.updateData(data as DeudaConsolidadaResponse);
          console.log('Updated DataService with:', data);
        } else {
          console.error('Invalid data structure received:', data);
          this.dataService.setError('Formato de datos inválido recibido del servidor');
        }
        this.dataService.setLoading(false);
      },
      error: (error: Error) => {
        console.error('API Error:', error);
        this.dataService.setError(error.message);
        this.dataService.setLoading(false);
      }
    });
  }
}
