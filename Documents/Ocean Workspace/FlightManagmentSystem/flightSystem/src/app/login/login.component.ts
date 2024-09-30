import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { UserAuthService } from '../services/user-auth.service';
import { RegisterService } from '../services/register.service';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  hidePassword: boolean = true;
  status: any;
  data = {
    username: '',
    password: '',
  };

  constructor(
    private authService: RegisterService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {}

  res: any = {};

  handleLogin() {
    if (this.data.username && this.data.password) {
      this.authService.authenticateUser(this.data).subscribe(
        (response) => {
          console.log(response);
          this.res = response;
          this.userAuthService.setRoles(this.res.roles);
          this.userAuthService.setToken(this.res.token);

          localStorage.setItem('username', this.res.username);
          sessionStorage.setItem('token', this.res.token);
          const role = this.res.roles[0];

          let message = '';
          let route = '';

          if (role === 'ROLE_ADMIN') {
            message = 'Logged In Successfully';
            route = '/admin';
          } else if (role === 'ROLE_USER') {
            message = 'Logged In Successfully';
            route = '/userpage';
          }

          Swal.fire({
            title: 'Success',
            text: message,
            icon: 'success',
            confirmButtonText: 'OK',
          }).then(() => {
            this.router.navigate([route]);
          });
        },
        (error) => {
          Swal.fire({
            title: 'Error',
            text: 'Invalid Details',
            icon: 'error',
            confirmButtonText: 'OK',
          });
        }
      );
    } else {
      console.log('Fields are empty!!');
      Swal.fire({
        title: 'Warning',
        text: 'Please fill in all fields.',
        icon: 'warning',
        confirmButtonText: 'OK',
      });
    }
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
}
