import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ServiceService } from '../../services/service.service';
import { response } from 'express';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule,FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {


  constructor(public service:ServiceService){ }

  registerForm = new FormGroup({
    username:new FormControl('',[Validators.required]),
    password : new FormControl('',[Validators.required]),
    confirmPassword: new FormControl('',[Validators.required]),
  })
 
  submitForm() {
    console.log('values',this.registerForm.value)
    this.service.register(this.registerForm.value).subscribe({
      next : (response) =>{
        alert('Tank-you For registration' + response.name);
      }
    })
  }
}
