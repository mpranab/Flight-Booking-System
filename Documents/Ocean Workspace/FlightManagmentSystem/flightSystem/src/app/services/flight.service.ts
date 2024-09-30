import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Flight } from '../model/flight';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FlightService {

  private baseUrl = 'http://localhost:8082/flight';

  constructor(private http: HttpClient) {}

  addFlight(flight: Flight): Observable<Flight> {
    return this.http.post<Flight>(`${this.baseUrl}/addFlight`, flight);
  }

  searchFlight(departCity: string, arrivalCity: string): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.baseUrl}/searchByCity/${departCity}/${arrivalCity}`);
  }

  getByFlightNo(flightNo: number): Observable<Flight> {
    return this.http.get<Flight>(`${this.baseUrl}/findByNumber/${flightNo}`);
  }

  findAll(): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.baseUrl}/findAll`);
  }

  findFlightsBetweenCitiesOnDate(departureCity: string, arrivalCity: string, departureDate: string): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.baseUrl}/search/${departureCity}/${arrivalCity}/${departureDate}`);
  }

  deleteById(flightNo: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/deleteById/${flightNo}`);
  }

  updateFlight(flight: Flight): Observable<Flight> {
    return this.http.put<Flight>(`${this.baseUrl}/update`, flight);
  }
}
