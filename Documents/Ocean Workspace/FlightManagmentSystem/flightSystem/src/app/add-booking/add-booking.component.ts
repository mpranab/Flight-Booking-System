import { Component, OnInit } from '@angular/core';
import { Booking } from '../model/booking';
import { BookingService } from '../services/booking.service';
import { FormArray, FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgModel } from '@angular/forms';
import { Flight } from '../model/flight';
import { FlightService } from '../services/flight.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { HttpClient } from '@angular/common/http';
import { Passenger } from '../model/passenger';
@Component({
  selector: 'app-add-booking',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './add-booking.component.html',
  styleUrl: './add-booking.component.css'
})
export class AddBookingComponent implements OnInit {

booking: Booking = new Booking();
successMessage: string = '';
username: string | null = '';

constructor(private bookingService: BookingService, private route: ActivatedRoute, private router:Router) {}

ngOnInit() {
  this.route.queryParams.subscribe(params => {
    this.username = localStorage.getItem('username');
    this.booking.flightNo = params['flightNo'];
    this.booking.date = params['departureDate'];
    this.booking.fare = params['fare'];
  });
}

onSubmit(bookingForm: NgForm) {
  if (bookingForm.valid) {
    this.bookingService.addBooking(this.booking).subscribe(
      (response) => {
        console.log('Booking added successfully', response);
        this.successMessage = 'Booking added successfully!';
        bookingForm.resetForm();
      },
      (error) => {
        console.error('Error adding booking', error);
      }
    );
  }
}

addPassenger() {
  this.booking.passengers.push(new Passenger());
}

removePassenger(index: number) {
  this.booking.passengers.splice(index, 1);
}


// booking: Booking = new Booking();
//   successMessage: string = '';
//   username: string | null = '';

//   constructor(private bookingService: BookingService, private route: ActivatedRoute, private router:Router) {}

//   ngOnInit() {
//     this.route.queryParams.subscribe(params => {
//       this.username = localStorage.getItem('username');
//       this.booking.flightNo = params['flightNo'];
//       this.booking.date = params['departureDate'];
//       this.booking.fare = params['fare'];
//     });
//   }

//   onSubmit(bookingForm: NgForm) {
//     if (bookingForm.valid) {
//       this.bookingService.addBooking(this.booking).subscribe(
//         (response) => {
//           console.log('Booking added successfully', response);
//           this.successMessage = 'Booking added successfully!';
//           bookingForm.resetForm();
//           this.booking.passengers = []; // Reset passengers array
//         },
//         (error) => {
//           console.error('Error adding booking', error);
//         }
//       );
//     }
//   }

//   addPassenger() {
//     if (this.booking.passengers.length < this.booking.seats) {
//       this.booking.passengers.push(new Passenger());
//     }
//   }

//   removePassenger(index: number) {
//     this.booking.passengers.splice(index, 1);
//   }
  book()
  {
    const queryParams = {
      bookingId: this.booking.bookingId,
      flightNo: this.booking.flightNo,
      fare: this.booking.fare,
      seats: this.booking.seats,
      username: this.booking.username,
      date: this.booking.date
    };
    this.router.navigate(['/payment'],{queryParams});
  }
}
