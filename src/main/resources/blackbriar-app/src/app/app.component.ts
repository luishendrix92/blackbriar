import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent 
{
  title = 'blackbriar';
  
  //This variable controls the no logged bar
  loggedIn: boolean = false;

  ngOnInit()
  {
    
  }
}
