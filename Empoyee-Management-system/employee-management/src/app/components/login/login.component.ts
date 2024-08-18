import { HttpClient } from '@angular/common/http';
import { Component, Inject, inject, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  userObj:any ={
    username:'',
    password:''
  };

  router = inject(Router)
  http = inject(HttpClient)

  /*onLogin(){
    debugger
    this.http.post("http://localhost:8765/users/login",this.userObj).subscribe((res:any)=>{
      if(res.token){
        localStorage.setItem("token",res.token)
      alert('login sussess')
      this.reouter.navigateByUrl('dashboard')
      }else{
        alert("wrong creadintial")
      }
    } )
  }*/

    onLogin() {
      debugger;
      
      this.http.post<any>("http://localhost:4041/users/login", this.userObj).subscribe(
        (res:any) => {
          if (res && res.token) {
            localStorage.setItem("token", res.token);
            alert('Login successful');
            this.router.navigateByUrl('/dashboard');
          } else {
            alert("Wrong credentials");
          }
        },
        (error) => {
          console.error('Login error:', error);
          alert("An error occurred while trying to log in. Please try again.");
        }
      );
    }
}
