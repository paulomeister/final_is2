import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface ConsultaResponse {
  cedula: string;
  nombre: string;
  fecha_consulta: string;
  energia: {
    periodo: string;
    consumo: number;
    valor_a_pagar: number;
  };
  agua: {
    periodo: string;
    consumido: number;
    valor_a_pagar: number;
  };
  valor_total_a_pagar: number;
}

@Injectable({
  providedIn: 'root'
})
export class Consulta {
  private apiUrl = 'http://localhost:3001/api/consulta';

  constructor(private http: HttpClient) {}

  consultarCedula(cedula: string): Observable<ConsultaResponse> {
    return this.http.post<ConsultaResponse>(this.apiUrl, { cedula })
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Error desconocido';
    
    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      switch (error.status) {
        case 404:
          errorMessage = 'Cédula no encontrada';
          break;
        case 400:
          errorMessage = 'Cédula inválida';
          break;
        case 500:
          errorMessage = 'Error interno del servidor';
          break;
        default:
          errorMessage = `Error del servidor: ${error.status}`;
      }
    }
    
    return throwError(() => new Error(errorMessage));
  }
}
