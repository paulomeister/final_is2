import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataService, DeudaConsolidadaResponse } from '../data.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-info-display',
  imports: [CommonModule],
  templateUrl: './info-display.html',
  styleUrl: './info-display.css'
})
export class InfoDisplayComponent implements OnInit, OnDestroy {
  consultaData: DeudaConsolidadaResponse | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  
  private subscriptions: Subscription[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    // Subscribe to data changes
    this.subscriptions.push(
      this.dataService.consultaData$.subscribe(data => {
        this.consultaData = data;
      })
    );

    this.subscriptions.push(
      this.dataService.isLoading$.subscribe(loading => {
        this.isLoading = loading;
      })
    );

    this.subscriptions.push(
      this.dataService.errorMessage$.subscribe(error => {
        this.errorMessage = error;
      })
    );
  }

  ngOnDestroy(): void {
    // Clean up subscriptions
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  formatCurrency(value: number): string {
    return this.dataService.formatCurrency(value);
  }

  formatDate(dateString: string): string {
    return this.dataService.formatDate(dateString);
  }
}
