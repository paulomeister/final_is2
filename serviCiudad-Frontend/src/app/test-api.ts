import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from './api.service';

@Component({
  selector: 'app-test-api',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div style="padding: 20px; border: 1px solid #ccc; margin: 20px;">
      <h3>API Test Component</h3>
      <button (click)="testApi()" [disabled]="loading">Test API Call</button>
      <div *ngIf="loading">Loading...</div>
      <div *ngIf="result">
        <h4>Result:</h4>
        <pre>{{ result | json }}</pre>
      </div>
      <div *ngIf="error" style="color: red;">
        <h4>Error:</h4>
        {{ error }}
      </div>
    </div>
  `
})
export class TestApiComponent {
  loading = false;
  result: any = null;
  error: string = '';

  constructor(private apiService: ApiService) {}

  testApi() {
    this.loading = true;
    this.result = null;
    this.error = '';

    console.log('TestApiComponent - Starting API test...');
    
    this.apiService.consultarDeudaConsolidada('1234500002').subscribe({
      next: (data) => {
        console.log('TestApiComponent - Success:', data);
        this.result = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('TestApiComponent - Error:', error);
        this.error = error.message;
        this.loading = false;
      }
    });
  }
}
