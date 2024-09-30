import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Flight } from '../model/flight';
import { FlightService } from '../services/flight.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-flight',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './add-flight.component.html',
  styleUrl: './add-flight.component.css'
})
export class AddFlightComponent {

  flight: Flight = new Flight();
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private flightService: FlightService, private router: Router) {}

  onSubmit() {
    this.flightService.addFlight(this.flight).subscribe(
      response => {
        this.successMessage = 'Flight added successfully!';
        this.errorMessage = null;
        this.flight = new Flight();  // Reset the form
        setTimeout(() => {
          this.router.navigate(['/flights']); // Navigate to flight list after successful addition
        }, 2000);
      },
      error => {
        this.successMessage = null;
        if (error.status === 409) {
          this.errorMessage = 'Flight already exists or arrival and departure cities are the same.';
        } else {
          this.errorMessage = 'An error occurred while adding the flight.';
        }
      }
    );
  }

}
