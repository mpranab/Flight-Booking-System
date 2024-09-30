import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { RegisterService } from '../services/register.service';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  usernameErrors = {
    pattern: false,
    required:false
  };

  hidePassword: boolean = true;
  status:string="";
  registerRequest: any = {
    username: '',
    email: '',
    password: '',
    role: '',
    contact: ''
  };
 
  passwordErrors = {
    required: false,
    minlength: false,
    uppercase: false,
    lowercase: false,
    number: false,
    special: false
  };
 
  emailErrors = {
    required: false,
    pattern: false,
    uppercase: false
  };
 
  constructor(private userService: RegisterService,private router:Router){}
 
  register(registerForm: any) {
    // Check if any form field is empty
    const formValues = registerForm.value;
    if (!formValues.username || !formValues.email || !formValues.password) {
      // Use Swal for warning message about empty fields
      Swal.fire('Warning', 'Please fill in all the fields.', 'warning');
      return; // Stop further execution
    }
 
    // If all fields are filled, proceed with form submission
    this.userService.registerUser(formValues).subscribe(
      (response) => {
        console.log(response);
        // Use Swal for success message
        Swal.fire('Success', 'Registered Successfully', 'success');
        this.router.navigate(['/login']);
      },
      (error) => {
        if (error.status === 200) {
          // Use Swal for success message
          Swal.fire('Success', 'Registered Successfully', 'success');
          this.router.navigate(['/login']);
        } else if (error.status === 400) {
          console.log("Username or emailId already exists. Please give correct data.");
          // Use Swal for error message
          Swal.fire('Error', 'Username or emailId already exists. Please give correct data.', 'error');
        } else {
          console.error("An error occurred:", error);
          // Use Swal for error message
          Swal.fire('Error', 'An error occurred. Please try again later.', 'error');
        }
      }
    );
  }
 
  
  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
 
  validatePassword(password: string) {
    this.passwordErrors = {
      required: !password,
      minlength: password.length < 8,
      uppercase: !/[A-Z]/.test(password),
      lowercase: !/[a-z]/.test(password),
      number: !/\d/.test(password),
      special: !/[!@#$%^&*(),.?":{}|<>]/.test(password)
    };
  }
  

  validateUsername(username: string) {
    const pattern = /^[A-Z][A-Za-z0-9]{0,19}$/;
    this.usernameErrors.pattern = pattern.test(username);
  }
 
  validateEmail(email: string) {
    this.emailErrors = {
      required: !email,
      pattern: !/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(email),
      uppercase: /[A-Z]/.test(email)
    };
  }

}