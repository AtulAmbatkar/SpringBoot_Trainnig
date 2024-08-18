import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from '../../../service/auth-service.service';

@Component({
  selector: 'app-editemployee',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './editemployee.component.html',
  styleUrls: ['./editemployee.component.scss']
})
export class EditemployeeComponent implements OnInit {

  // employeeForm: FormGroup;

   employee : any ;
  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private service: AuthServiceService
  ) {}

  addEmployee: FormGroup = new FormGroup({
    eid : new FormControl("",[Validators.required]),
    ename:new FormControl("",[Validators.required]),
    department: new FormControl("",[Validators.max(20),Validators.required]),
    position:new FormControl("",[Validators.required]),
    salary:new FormControl("",[Validators.required])
  });

  ngOnInit(): void {
    // Extract parameters from the URL
    this.route.paramMap.subscribe(params => {
      const eid = params.get('eid');
      const ename = params.get('ename');
      const department = params.get('department');
      const position = params.get('position');
      const salary = params.get('salary');

      // Set form values
      this.addEmployee.setValue({
        eid: eid || '',
        ename: ename || '',
        department: department || '',
        position: position || '',
        salary: salary || ''
      });
    });
  }

  /*saveChanges(){
    const headers = this.service.createAuthorizationHeader();
    this.http.put('http://localhost:5052/employees/${eid}',this.addEmployee.value,{ headers }).subscribe((Response:any) =>{
      alert('Succesfully Employee edit' + Response.msg);
    });
  }*/

    saveChanges(){
    const headers = this.service.createAuthorizationHeader();
    const eid = this.addEmployee.get('eid')?.value;

    this.http.put(`http://localhost:5052/employees/${eid}`, this.addEmployee.value, { headers })
      .subscribe((response: any) => {
        alert('Successfully updated employee: ' + response.msg);
      });
    }
}
