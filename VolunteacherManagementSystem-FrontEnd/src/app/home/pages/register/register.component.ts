
import { Component, OnInit } from '@angular/core';
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
  constructor(private _userService:UsersService) { }
  ngOnInit() {
    this.invalidEmail = false;
    this.invalidMobileNumber = false
  }

  register(){
    this.applicantRequest.status = 2;
    //this.applicantRequest.requestDate = new Date().toISOString().slice(0, 19).replace('T', ' ');
    console.log(this.applicantRequest)
    this._userService.registration(this.applicantRequest).subscribe(data=>{
      this.invalidMobileNumber = false
      this.invalidEmail = false
      console.log(data)
    },
      error =>{ 
        if(error.status == 409)
        {
          this.invalidEmail = true
        }
        if(error.status == 400)
        {
          this.invalidEmail = false
          this.invalidMobileNumber = true
        }
      },
     
    );
  }
}
