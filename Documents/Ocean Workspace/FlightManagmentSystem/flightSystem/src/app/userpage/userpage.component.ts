import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Flight } from '../model/flight';
import { FlightService } from '../services/flight.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-userpage',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './userpage.component.html',
  styleUrl: './userpage.component.css'
})
export class UserpageComponent  implements OnInit{
  departureCity: string = '';
  arrivalCity: string = '';
  departureDate: string = '';
  flights: Flight[] = [];
  minDate:string='';

  constructor(private flightService: FlightService,private router:Router) { }
  username:string | null="";
  ngOnInit(): void {
    this.username=localStorage.getItem("username");
    const today = new Date();
  const yyyy = today.getFullYear();
  const mm = String(today.getMonth() + 1).padStart(2, '0'); // Months start at 0!
  const dd = String(today.getDate()).padStart(2, '0');
  this.minDate = `${yyyy}-${mm}-${dd}`;
  }

  onSubmit() {
    this.flightService.findFlightsBetweenCitiesOnDate(this.departureCity, this.arrivalCity, this.departureDate)
      .subscribe(
        (data: Flight[]) => {
          this.flights = data;
        },
        (error) => {
          console.error('Error fetching flights', error);
        }
      );
  }

  // bookNow(flight: any) {
  //   this.router.navigate(['/addbooking'], { queryParams: { flightNo: flight.flightNo, flight: JSON.stringify(flight) } });
  // }
  bookNow(flight: any) {
    this.router.navigate(['/addbooking'], { 
      queryParams: { 
        flightNo: flight.flightNo, 
        departureDate: flight.departureDate, 
        fare: flight.fare 
      } 
    });
  }
  logout()
  {
      
      Swal.fire({
        title: 'Logout',
        text: 'Are you sure you want to log out?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, logout'
      }).then((result) => {
        if (result.isConfirmed) {
          localStorage.removeItem('username');
      sessionStorage.removeItem('token');
          this.router.navigate(['/login']);
          Swal.fire(
            'Logged out!',
            'You have been logged out successfully.',
            'success'
          );
        }
      });
  }
}

