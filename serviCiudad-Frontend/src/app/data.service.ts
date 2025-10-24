import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export interface DeudaConsolidadaResponse {
  clienteId: string;
  nombreCliente: string;
  fechaConsulta: string;
  energia: {
    periodo: string;
    consumo: string;
    valorPagar: number;
  };
  acueducto: {
    periodo: string;
    consumo: string;
    valorPagar: number;
  };
  totalAPagar: number;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private consultaDataSubject = new BehaviorSubject<DeudaConsolidadaResponse | null>(null);
  private isLoadingSubject = new BehaviorSubject<boolean>(false);
  private errorMessageSubject = new BehaviorSubject<string>('');

  // Observables for components to subscribe to
  public consultaData$ = this.consultaDataSubject.asObservable();
  public isLoading$ = this.isLoadingSubject.asObservable();
  public errorMessage$ = this.errorMessageSubject.asObservable();

  // Current values (for synchronous access)
  get currentData(): DeudaConsolidadaResponse | null {
    return this.consultaDataSubject.value;
  }

  get currentLoadingState(): boolean {
    return this.isLoadingSubject.value;
  }

  get currentErrorMessage(): string {
    return this.errorMessageSubject.value;
  }

  /**
   * Updates the current API response data
   * @param data - The API response data or null to clear
   */
  updateData(data: DeudaConsolidadaResponse | null): void {
    this.consultaDataSubject.next(data);
  }

  /**
   * Updates the loading state
   * @param loading - Whether data is currently being loaded
   */
  setLoading(loading: boolean): void {
    this.isLoadingSubject.next(loading);
  }

  /**
   * Updates the error message
   * @param error - Error message or empty string to clear
   */
  setError(error: string): void {
    this.errorMessageSubject.next(error);
  }

  /**
   * Clears all data and resets state
   */
  clearData(): void {
    this.consultaDataSubject.next(null);
    this.isLoadingSubject.next(false);
    this.errorMessageSubject.next('');
  }

  /**
   * Formats currency values for display
   * @param value - Numeric value to format
   * @returns Formatted currency string
   */
  formatCurrency(value: number): string {
    return new Intl.NumberFormat('es-CO', {
      style: 'currency',
      currency: 'COP',
      minimumFractionDigits: 0
    }).format(value);
  }

  /**
   * Formats date for display
   * @param dateString - ISO date string
   * @returns Formatted date string (dd/MM/yyyy HH:mm)
   */
  formatDate(dateString: string): string {
    try {
      const date = new Date(dateString);
      return date.toLocaleString('es-CO', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false
      });
    } catch (error) {
      return dateString; // Return original if parsing fails
    }
  }
}
