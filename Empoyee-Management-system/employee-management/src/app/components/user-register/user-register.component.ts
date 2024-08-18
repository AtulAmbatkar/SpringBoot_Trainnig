
import { HttpClient } from '@angular/common/http';
import { Component, inject} from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.scss'
})
export class UserRegisterComponent {

  // userForm: FormGroup;

  userForm: FormGroup = new FormGroup({
    email:new FormControl("",[Validators.required,Validators.email]),
    username: new FormControl("",[Validators.max(20),Validators.required]),
    password:new FormControl("",[Validators.required])
  });

  http = inject(HttpClient)
  router =inject(Router)

  
submitForm() {
  debugger
  alert(this.userForm.value)
  this.http.post('http://localhost:4041/users/register', this.userForm.value).subscribe(
     (Response:any) =>{
      alert('Tank-you For registration' + Response.token);
      this.router.navigateByUrl('/layout');
    }
  );
}
}

