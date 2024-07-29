import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginObj: any={

    "username": "",
    "password": ""
  };

  http = inject(HttpClient)


  loginForm() {
    this.http.post('http://localhost:4041/login', this.loginObj).subscribe({
      next: (response) => {
        console.log(response);
        alert('Login successful!');
      },
      error: (err) => {
        console.error('Login error:', err);
        alert('Login failed!');
      }
    });
  }

}