import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) 
  { 
    console.log("marrano pero de registro");
  }

  register(firstName, lastName, email, password)
  {
    console.log(JSON.stringify({firstName: firstName, lastName: lastName, email: email, password: password, photo: "default.jpg" }));
    let jsonCoded = JSON.stringify({firstName: firstName, lastName: lastName, email: email, password: password, photo: "default.jpg" });
    return this.http.post("http://localhost:8080/api/users", jsonCoded);
  }
}
