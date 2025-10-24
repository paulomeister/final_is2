import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

export interface ConsultaResponse {
  cedula?: string;
  nombre: string;
  fecha_consulta: string;
  energia: {
    periodo: string;
    consumo: string;
    valor_a_pagar: number;
  };
  agua: {
    periodo: string;
    consumido: string;
    valor_a_pagar: number;
  };
  valor_total_a_pagar: number;
}

@Injectable({
  providedIn: 'root'
})
export class Consulta {

  constructor(private http: HttpClient) {}

  // Construye la URL usando el clienteId (cédula) y realiza GET a la ruta requerida
  consultarCedula(cedula: string): Observable<ConsultaResponse> {
    const clienteId = cedula;
    const url = `http://localhost:8080/api/v1/clientes/${encodeURIComponent(clienteId)}/deuda-consolidada`;
    return this.http.get<any>(url)
      .pipe(
        map((data: any) => {
          // Transformar la respuesta del backend al formato que espera la UI
          return {
            cedula: data.clienteId || cedula,
            nombre: data.nombreCliente || data.nombre || '',
            fecha_consulta: data.fechaConsulta || data.fecha_consulta || '',
            energia: {
              periodo: data.energia?.periodo || '-',
              consumo: data.energia?.consumo || '0 kWh',
              valor_a_pagar: typeof data.energia?.valorPagar === 'number' ? data.energia.valorPagar : Number(data.energia?.valorPagar) || 0
            },
            agua: {
              periodo: data.acueducto?.periodo || '-',
              consumido: data.acueducto?.consumo || '0 m³',
              valor_a_pagar: typeof data.acueducto?.valorPagar === 'number' ? data.acueducto.valorPagar : Number(data.acueducto?.valorPagar) || 0
            },
            valor_total_a_pagar: typeof data.totalAPagar === 'number' ? data.totalAPagar : Number(data.totalAPagar) || Number(data.valor_total_a_pagar) || 0
          } as ConsultaResponse;
        }),
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
