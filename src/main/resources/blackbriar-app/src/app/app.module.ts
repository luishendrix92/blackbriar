// Modules
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Route, RouterModule } from '@angular/router';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { HttpClientModule } from '@angular/common/http';
import { MaterializeModule } from 'angular2-materialize';

// Components
import { LoginComponent } from './login/login.component';
import { NavbarNoLoggedComponent } from './navbar-no-logged/navbar-no-logged.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { NavbarLoggedComponent } from './navbar-logged/navbar-logged.component';
import { InstructorDashboardComponent } from './instructor/instructor-dashboard/instructor-dashboard.component';

// Services
import { LoginService } from './login.service';
import { RegisterService } from './register.service';


const routes: Route[] = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'instructor/instructor-dashboard', component: InstructorDashboardComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarNoLoggedComponent,
    HomeComponent,
    RegisterComponent,
    NavbarLoggedComponent,
    InstructorDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    RouterModule.forRoot(routes),
    AngularFontAwesomeModule,
    FormsModule,
    HttpClientModule,
    MaterializeModule
  ],
  providers: [
    LoginService,
    RegisterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
