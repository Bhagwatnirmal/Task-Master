import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  
  constructor(public http:HttpClient, public app:AppComponent)
  {

  }

  username:string = '';
  password:string = '';

  login()
  {
    let url = 'http://localhost:8080/login' + this.username
    this.http.post(url, this.password).subscribe((data:any)=>
    {
      if(data>0)
      {
        this.app.isLoggedIn = 1;
        this.app.userid = data;
      }
      else if(data == -1)
      {
        alert('Exception');
      }
      else if(data == -2)
      {
        alert('username wrong');
      }
      else if(data == -3)
      {
        alert('multiple username');
      }
      else if(data == -4)
      {
        alert('wrong password');
      }
      else
      {
        alert('something is wrong');        
      }
    })
  }
}