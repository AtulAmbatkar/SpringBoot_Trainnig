import { NgFor } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthServiceService } from '../../../service/auth-service.service';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [RouterOutlet,RouterLink,NgFor],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.scss'
})
export class EmployeeListComponent implements OnInit {
    
  
  employeeList: any[] = []; 
  
    constructor(private http: HttpClient, private router: Router,private service :AuthServiceService) {}


    ngOnInit(): void {
      this.getAllUsers();
    
    }
  
    getAllUsers() {
      // Ensure headers is always defined
      debugger
      const headers = this.service.createAuthorizationHeader();
  
      this.http.get("http://localhost:5052/employees", { headers }).subscribe(
        (result: any) => {
          this.employeeList = result;
          console.log(this.employeeList);
        }
      );
    }

    onEdit(employee : any) {
      this.router.navigate(['/edit-employee',employee]);
    }

    addEmployee(){
      this.router.navigateByUrl('/add-employee')
    }

    deleteEmployee(employeeId : number){
      debugger
      confirm("Are you sure you want to delete this item?")
      const headers = this.service.createAuthorizationHeader();
      this.http.delete(`http://localhost:5052/employees/${employeeId}`,{headers}).subscribe(()=>{
        this.employeeList = this.employeeList.filter(emp => emp.eid !== employeeId);
        console.log(`Employee with ID ${employeeId} deleted`);
        this.getAllUsers();
      },
      (error) => {
        console.error('Delete failed', error);
      }
    );
    }
  } 
  
  
  