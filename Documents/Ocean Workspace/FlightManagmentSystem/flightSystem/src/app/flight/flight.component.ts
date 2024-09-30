import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';
import { Flight } from '../model/flight';
import { FlightService } from '../services/flight.service';
import { Router } from '@angular/router';
import { BookingService } from '../services/booking.service';
import { UserAuthService } from '../services/user-auth.service';
@Component({
  selector: 'app-flight',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './flight.component.html',
  styleUrl: './flight.component.css'
})
export class FlightComponent implements OnInit {

  departureCity: string = '';
  arrivalCity: string = '';
  flights: Flight[] = [];
  errorMessage: string = '';

  username:string | null="";
  ngOnInit(): void {
    this.username=localStorage.getItem("username");
  }

  constructor(private flightService: FlightService, private router:Router) {}

  onSubmit(): void {
    this.flightService.searchFlight(this.departureCity, this.arrivalCity).subscribe({
      next: (data: Flight[]) => {
        this.flights = data;
        this.errorMessage = '';
      },
      error: (err) => {
        this.errorMessage = 'No flights found';
        this.flights = [];
      }
    });
  }
}

