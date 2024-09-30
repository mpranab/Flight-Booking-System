import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CheckIn } from '../model/check-in';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CheckInService {

  private baseUrl = 'https://localhost:8085/api/checkIn';

  constructor(private http: HttpClient) {}

  createCheckIn(checkIn: CheckIn): Observable<CheckIn> {
    return this.http.post<CheckIn>(`${this.baseUrl}/performCheckIn`, checkIn);
  }

  getAllCheckIns(): Observable<CheckIn[]> {
    return this.http.get<CheckIn[]>(this.baseUrl);
  }

  getCheckInById(checkIn_id: string): Observable<CheckIn> {
    return this.http.get<CheckIn>(`${this.baseUrl}/${checkIn_id}`);
  }

  getCheckInsByBookingId(bookingId: string): Observable<CheckIn> {
    return this.http.get<CheckIn>(`${this.baseUrl}/booking/${bookingId}`);
  }

  isCheckInDoneForBooking(bookingId: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/checkin-status/${bookingId}`);
  }

  updateCheckIn(id: string, checkIn: CheckIn): Observable<CheckIn> {
    return this.http.put<CheckIn>(`${this.baseUrl}/${id}`, checkIn);
  }

  deleteCheckIn(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
