import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ServiceService } from '../../services/service.service';
import { response } from 'express';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

   
    constructor(public service : ServiceService,
      public router : Router
    ) { } 


    loginForm=new FormGroup({
      username:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
  })

  submitForm() {
    console.log('values',this.loginForm.value)
    this.service.login(this.loginForm.value).subscribe({
      next : (response) =>{
        console.log('Login response:',response);
        if(response.token != null){
         alert("Hello, your token is" + response.token);
          localStorage.setItem('jwt',response.token);
          this.router.navigateByUrl("/dashboard");
        }
      }
    })
    
  }
}