import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor() { }

   createAuthorizationHeader(): HttpHeaders {
    const jwtToken = localStorage.getItem("token");
    if (jwtToken) {
      console.log("JWT token found:", jwtToken);
      return new HttpHeaders().set("Authorization", "Bearer " + jwtToken);
    } else {
      console.log("JWT token not found in localStorage");
      return new HttpHeaders(); // Return an empty HttpHeaders if no token is found
    }
  }

}
