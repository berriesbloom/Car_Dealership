import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { response } from 'express';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit{
  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
    this.fetchDetails();
  }

  public fetchDetails(){
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const name = (document.getElementById('name') as HTMLInputElement).value;
    const contactNumber = (document.getElementById('contact') as HTMLInputElement).value;
    const password = (document.getElementById('password') as HTMLInputElement).value;

    const userData = {"name":name, "contactNumber":contactNumber, "email":email,"password":password}

    this.http.post('http://localhost:8080/user/signup', userData).subscribe(
      (resp:any) => {
        console.log(resp);
      }
    )
  }
}
