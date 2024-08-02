import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {


  private baseUrl = `http://localhost:4041/`

  constructor(private http:HttpClient) { }

  authSubject = new BehaviorSubject<any>({
    user:null
  })

  login(userData:any):Observable<any>{
    return this.http.post<any>(`${this.baseUrl}login`,userData)
  }

  register(userData:any):Observable<any>{
    return this.http.post<any>(`${this.baseUrl}signup`,userData)
  }

  hello(): Observable<any> {
    return this.http.get(`${this.baseUrl}api/hello`, {
       headers : this.createAuhtorizationHeader()
    })
  }

  private createAuhtorizationHeader() {
    const jwtToken = localStorage.getItem(`jwt`);
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      return new HttpHeaders().set(
        "Authorization","Bearer " + jwtToken
      )
    } else {
      console.log("JWT token not found in local storage");
    }
    return undefined;
  }

}
