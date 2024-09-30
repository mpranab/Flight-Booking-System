import { Component, OnInit } from '@angular/core';
import { Flight } from '../model/flight';
import { FlightService } from '../services/flight.service';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-update-flight',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './update-flight.component.html',
  styleUrl: './update-flight.component.css'
})
export class UpdateFlightComponent  implements OnInit {

  flight: Flight = {
    flightNo: 0,
    departureCity: '',
    arrivalCity: '',
    departureDate: '',
    fare: 0,
    seatCapacity: 0
  };
  errorMessage: string | undefined;
  successMessage: string | undefined;

  constructor(
    private flightService: FlightService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    const flightNoParam = this.route.snapshot.paramMap.get('flightNo');
    if (flightNoParam) {
      const flightNo = +flightNoParam; // Convert string to number
      await this.getFlightDetails(flightNo);
    }
  }

  async getFlightDetails(flightNo: number): Promise<void> {
    try {
      const data = await this.flightService.getByFlightNo(flightNo).toPromise();
      if (data) {
        this.flight = data;
      }
    } catch (error) {
      this.errorMessage = (error as any).error?.message || 'Error fetching flight details';
    }
  }

  async onSubmit(): Promise<void> {
    try {
      await this.flightService.updateFlight(this.flight).toPromise();
      this.successMessage = 'Flight updated successfully!';
      this.errorMessage = undefined;
      // await this.router.navigate(['/']); // Navigate back after successful update
    } catch (error) {
      this.errorMessage = (error as any).error?.message || 'Error updating flight';
      this.successMessage = undefined;
    }
  }
}