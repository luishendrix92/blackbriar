import { Component, OnInit } from '@angular/core';

import { LoginService } from './../login.service';
import { AppComponent } from './../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent  {

  email: string = "";
  password: string = "";
  images: string [] = ["assets/dawn.png", "assets/night.jpg", "assets/bonfire.jpg"];
  selectedImage: string = "";
  
  constructor (private loginService : LoginService, private appComponent: AppComponent)
  {

  }

  ngOnInit()
  {
    let val= this.images.length;
    let rand = Math.floor(Math.random() * val);
    this.selectedImage = this.images[rand];

    
  }

  login()
  {
    // Lo de abajo es un ejemplo de lo que se puede hacer importando un componente
    // construyendolo y modificando sus variables 
    //this.appComponent.loggedIn = true; 
    console.log(this.email);
    console.log(this.password);
    this.loginService.login(this.email, this.password).
    subscribe(
      data  => 
      { 
        console.log("POST Request is successful ", data);
      },
      error  => 
      { 
        console.log("Error", error); 
      }
    );
  }


}
