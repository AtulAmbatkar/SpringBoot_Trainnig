import { NgFor } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthServiceService } from '../../service/auth-service.service';
import { response } from 'express';

@Component({
  selector: 'app-api-dashboard',
  standalone: true,
  imports: [RouterOutlet,RouterLink,NgFor],
  templateUrl: './api-dashboard.component.html',
  styleUrl: './api-dashboard.component.scss'
})
export class ApiDashboardComponent implements OnInit {
  
  currentUser !: string;

  constructor(private service: AuthServiceService,private http : HttpClient) {}


  ngOnInit(): void {
    debugger
    const headers = this.service.createAuthorizationHeader();
    console.log('Headers:', headers.keys()); // Check headers being set
    this.http.get('http://localhost:4041/users/profile', { headers }).subscribe(
      (response: any) => {
        this.currentUser = response.username;
      }
    );
  }
  
  
    


}