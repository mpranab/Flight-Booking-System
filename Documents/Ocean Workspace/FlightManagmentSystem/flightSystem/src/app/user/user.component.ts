import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-user',
  standalone: true,
  imports: [],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{

  username:string | null="";
  constructor(private router:Router){}
  
  

  ngOnInit(): void {
    this.username=localStorage.getItem("username");
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
