import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../../../service/auth-service.service';

@Component({
  selector: 'app-addemployee',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './addemployee.component.html',
  styleUrl: './addemployee.component.scss'
})
export class AddemployeeComponent {

  

  addEmployee: FormGroup = new FormGroup({
    ename:new FormControl("",[Validators.required]),
    department: new FormControl("",[Validators.max(20),Validators.required]),
    position:new FormControl("",[Validators.required]),
    salary:new FormControl("",[Validators.required])
  });

   http =  inject(HttpClient)
  router =inject(Router)
  service = inject(AuthServiceService)

  
submitForm() {
  debugger
  alert(this.addEmployee.value)
  const headers = this.service.createAuthorizationHeader();
  this.http.post('http://localhost:5052/employees', this.addEmployee.value,{ headers }).subscribe(
     (Response:any) =>{
      alert('Succesfully Employee add ' + Response.msg);
    }
  );
}

}
