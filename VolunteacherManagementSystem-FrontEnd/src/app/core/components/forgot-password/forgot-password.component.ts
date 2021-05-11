import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private userService:UsersService,private router:Router) { }

  invalidEmail:boolean = false

  ngOnInit(): void {
    this.invalidEmail = false
  }

  sendOTP(val)
  {
    console.log(val.username);
    this.userService.sendOTP(val.username).subscribe(data=>{
      console.log(data)
      this.router.navigate(['forgot-password/send-otp'])
      this.invalidEmail = false
      localStorage.setItem("username",data.userId.toString() + " " + val.username);
    },
    (error) =>{

      if(error.status == 400)
      {
        this.invalidEmail = true
        this.router.navigate(['forgot-password'])
      }
     
    })
  }

}
