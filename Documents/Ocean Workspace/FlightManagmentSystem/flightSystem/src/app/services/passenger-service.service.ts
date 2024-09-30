import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Passenger } from '../model/passenger';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PassengerServiceService {

  private baseUrl = '/api/passenger';

  constructor(private http: HttpClient) {}

  addPassenger(passenger: Passenger): Observable<Passenger> {
    return this.http.post<Passenger>(`${this.baseUrl}/addPassenger`, passenger);
  }

  getAllPassengers(): Observable<Passenger[]> {
    return this.http.get<Passenger[]>(`${this.baseUrl}/getAll`);
  }

  deletePassengerById(passengerId: string): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/deleteById/${passengerId}`);
  }

  deletePassenger(seat: number, flightNo: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete/${seat}/${flightNo}`);
  }

  countPassengers(flightNo: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count/${flightNo}`);
  }
}
