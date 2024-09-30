import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Payment } from '../model/payment';
@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {

  amount!: number;
  custName!: string;
  transactionDetails!:Payment;
  cvc!: string;
  cardNumber!: string;
  expirationDate!: Date;
  
 booking:any={};
 
  constructor(private route: ActivatedRoute, private paymentService: PaymentService) { }
 
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.booking.fare = params['fare'];
      this.amount = this.booking.fare;
    });
  }
 
  createTransaction(): void {
    if (!this.amount || !this.custName || !this.cvc || !this.cardNumber || !this.expirationDate) {
      Swal.fire({
        title: 'Error',
        text: 'All fields are required',
        icon: 'error',
        confirmButtonText: 'OK'
      });
      return;
    }
    this.paymentService.createTransaction(this.amount)
      .subscribe(
        (transactionDetails: any) => { // Use 'any' type here or adjust the type to match the response
          this.transactionDetails = transactionDetails;
          console.log("SUCCESS!! - Your transaction was successful");
          console.log(this.transactionDetails);
          Swal.fire({
            title: 'Success',
            text: 'Your transaction was successful',
            icon: 'success',
            confirmButtonText: 'OK'
          });
        },
        error => {
          console.error('Error creating transaction:', error);
          console.log("ERROR - Payment Unsuccessful");
          Swal.fire({
            title: 'Error',
            text: 'Payment Unsuccessful',
            icon: 'error',
            confirmButtonText: 'OK'
          });
        }
      );
  }
 
}  

