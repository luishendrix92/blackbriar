import { Component, OnInit } from '@angular/core';

import { RegisterService } from './../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  validatePassword: string = "";
  studentOrInstructor: boolean = false;
  images: string [] = ["assets/dawn.png", "assets/night.jpg", "assets/bonfire.jpg"];
  selectedImage: string = "";

  ngOnInit()
  {
    let val= this.images.length;
    let rand = Math.floor(Math.random() * val);
    this.selectedImage = this.images[rand];
  }

  register()
  {
    if(this.studentOrInstructor)
    {
      console.log("Instructor");
    }
    else
    {
      console.log("Student");
    }

    if(this.password.length < 8)
    {
      Materialize.toast({html: 'Your password must to have at least 8 characters'});
    }
    else if(this.hasNumbers(this.firstName) && this.hasNumbers(this.lastName))
    {
      Materialize.toast({html: 'Your first name and last name does not have numbers'});
    }
    else if(this.password != this.validatePassword)
    {
      Materialize.toast({html: 'The passwords are different'});
    }
    else if(this.email.length == 0)
    {
      Materialize.toast({html: 'You have to enter your email'});
    }
    else
    {
      this.registerService.register(this.firstName, this.lastName, this.email, this.password).
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

  hasNumbers(text)
  {
    let numeros="0123456789";
    for(let i=0; i<text.length; i++)
    {
       if (numeros.indexOf(text.charAt(i),0)!=-1)
       {
          return 1;
       }
    }
    return 0;
 }

  constructor(private registerService: RegisterService) { }

}
