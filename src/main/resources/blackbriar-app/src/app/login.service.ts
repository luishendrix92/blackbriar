import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) 
  { 
    console.log("marrano");
  }

  login(email, password)
  {
    console.log(JSON.stringify({ email: email, password: password }));
    let jsonCoded = JSON.stringify({ email: email, password: password });
    return this.http.post("http://localhost:8080/api/users/login", jsonCoded);
  }
}
