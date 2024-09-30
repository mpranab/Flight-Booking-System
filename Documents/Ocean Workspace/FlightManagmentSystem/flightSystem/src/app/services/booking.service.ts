import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from '../model/booking';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private baseUrl = 'http://localhost:8081/api/booking';

  constructor(private http: HttpClient) {}

  getAllBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.baseUrl);
  }

  addBooking(booking: Booking): Observable<Booking[]> {
    return this.http.post<Booking[]>(`${this.baseUrl}/add`, booking);
  }

  getBookingById(bookingId: number): Observable<Booking> {
    return this.http.get<Booking>(`${this.baseUrl}/${bookingId}`);
  }

  getBookingByFlightNo(flightNo: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.baseUrl}/flight/${flightNo}`);
  }

  deleteBookingById(bookingId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${bookingId}`);
  }

  updateBookingById(bookingId: number, updatedBooking: Booking): Observable<Booking> {
    return this.http.put<Booking>(`${this.baseUrl}/${bookingId}`, updatedBooking);
  }
}
