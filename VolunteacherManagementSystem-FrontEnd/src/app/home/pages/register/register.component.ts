
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Applicantrequest } from 'src/app/core/model/applicantrequest';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  applicantRequest:Applicantrequest = new Applicantrequest()
  invalidEmail:boolean = false;
  invalidMobileNumber:boolean = false;
  showForm:boolean=true

  disabled:boolean=false

  showProgressbar:boolean=false

  constructor(private router:Router,private _userService:UsersService) { }
  ngOnInit() {
    this.invalidEmail = false;
    this.invalidMobileNumber = false
  }

  register(){
    this.disabled=true
    this.showProgressbar=true
    this.applicantRequest.status = 0;
    //this.applicantRequest.requestDate = new Date().toISOString().slice(0, 19).replace('T', ' ');

    this._userService.registration(this.applicantRequest).subscribe(data=>{
      this.invalidMobileNumber = false
      this.disabled=false
      this.invalidEmail = false
      this.showProgressbar=false
      this.showForm=false
    },
      error =>{ 
        if(error.status == 409)
        {
          this.invalidEmail = true
          this.showProgressbar=false

          setTimeout(() => {
            this.router.navigate([''])
          }, 3000);
        }
        else if(error.status == 400)
        {
          this.invalidEmail = false
          this.invalidMobileNumber = true
          this.showProgressbar=false
          setTimeout(() => {
            this.router.navigate([''])
          }, 3000);
        }
        else if(error.status == 500)
        {
          this.router.navigate(['/internal-server-error'])
        }
        else
        {
          this.router.navigate(['/error-page'])
        }
      },
     
    );
  }
}
