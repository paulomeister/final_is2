import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';

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
export class ApiService {

  constructor(private http: HttpClient) {}

  /**
   * Consulta la deuda consolidada de un cliente por su cédula
   * @param cedula - La cédula del cliente
   * @returns Observable con la información de deuda consolidada
   */
  consultarDeudaConsolidada(cedula: string): Observable<DeudaConsolidadaResponse> {
    const url = `${environment.apiBaseUrl}/api/v1/clientes/${cedula}/deuda-consolidada`;
    
    console.log('API Service - Making request to:', url);
    console.log('API Service - Environment base URL:', environment.apiBaseUrl);
    
    return this.http.get<DeudaConsolidadaResponse>(url)
      .pipe(
        tap((response) => {
          console.log('API Service - Success response:', response);
        }),
        catchError((error) => {
          console.error('API Service - HTTP Error:', error);
          return this.handleError(error);
        })
      );
  }

  /**
   * Maneja los errores HTTP y devuelve mensajes descriptivos
   * @param error - Error HTTP recibido
   * @returns Observable con error descriptivo
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Error desconocido';

    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente (red, conexión, etc.)
      errorMessage = `Error de conexión: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      switch (error.status) {
        case 404:
          errorMessage = 'Cédula no encontrada. Verifique el número e intente nuevamente.';
          break;
        case 400:
          errorMessage = 'Cédula inválida. Por favor, ingrese un número de cédula válido.';
          break;
        case 500:
          errorMessage = 'Error interno del servidor. Intente nuevamente más tarde.';
          break;
        case 0:
          errorMessage = 'No se pudo conectar con el servidor. Verifique que el backend esté ejecutándose.';
          break;
        default:
          errorMessage = `Error del servidor: ${error.status} - ${error.statusText}`;
      }
    }

    return throwError(() => new Error(errorMessage));
  }
}
