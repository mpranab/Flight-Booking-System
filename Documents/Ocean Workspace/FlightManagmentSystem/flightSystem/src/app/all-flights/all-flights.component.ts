import { Component, OnInit } from '@angular/core';
import { Flight } from '../model/flight';
import { FlightService } from '../services/flight.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-all-flights',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './all-flights.component.html',
  styleUrl: './all-flights.component.css'
})
export class AllFlightsComponent implements OnInit {

flights: Flight[] = [];
  loading = true;
  errorMessage: string | undefined;
  successMessage: string | undefined;

  constructor(private flightService: FlightService, private router: Router) { }

  ngOnInit(): void {
    this.flightService.findAll().subscribe(
      (data: Flight[]) => {
        this.flights = data;
        this.loading = false;
      },
      (error: any) => {
        this.errorMessage = error.message || 'Error fetching flights';
        this.loading = false;
      }
    );
  }

  navigateToUpdate(flightNo: number): void {
    this.router.navigate(['/updateflight', flightNo]);
  }

  deleteFlight(flightNo: number): void {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.flightService.deleteById(flightNo).subscribe(
          (success: boolean) => {
            if (success) {
              this.successMessage = 'Flight deleted successfully!';
              this.errorMessage = undefined;
              this.flightService.findAll(); // Refresh the list after deletion
              Swal.fire(
                'Deleted!',
                'Flight has been deleted.',
                'success'
              );
            } else {
              this.errorMessage = 'Failed to delete flight';
              this.successMessage = undefined;
              Swal.fire(
                'Error!',
                'An error occurred while deleting the flight.',
                'error'
              );
            }
          },
          (error: any) => {
            this.errorMessage = 'Error deleting flight';
            this.successMessage = undefined;
            Swal.fire(
              'Error!',
              'An error occurred while deleting the flight.',
              'error'
            );
          }
        );
      }
    });
  }
  
}