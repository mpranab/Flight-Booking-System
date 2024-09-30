import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  url:string="http://localhost:8090/payment"
  constructor(private http:HttpClient) { }
  createTransaction(amount: number): Observable<any> {
    return this.http.get<any>(`${this.url}/createTransaction/${amount}`);
  }
}
