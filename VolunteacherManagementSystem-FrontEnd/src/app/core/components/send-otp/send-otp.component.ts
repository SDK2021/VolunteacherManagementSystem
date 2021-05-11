import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-send-otp',
  templateUrl: './send-otp.component.html',
  styleUrls: ['./send-otp.component.css']
})
export class SendOtpComponent implements OnInit {

  constructor(private router:Router,private userService:UsersService) { }
  invalidOTP:boolean = false
  timeleft:number = 90
  cnt:number = 0;
  interval:any;
  ngOnInit(): void {
    console.log(this.timeleft)
    this.startTimer();
  }

  startTimer() {
    this.interval = setInterval(() => {
      if(this.timeleft > 0) {
        this.timeleft--;
      } else {
        this.pauseTimer();
      }
    },1000)
  }

  pauseTimer() {
    clearInterval(this.interval);
    this.sendOTP();
  }

  verifyOTP(val)
  {
    console.log(val.otp);
    let username:string[] = localStorage.getItem("username").split(" ");
    this.userService.verifyOTP(val.otp,+username[0]).subscribe(data=>
      {
        if(data == true)
        {
          this.invalidOTP = false
          this.router.navigate(['change-password'])
          clearInterval(this.interval);
        }
        else
        {
          this.invalidOTP = true
        }
      })
  }

  sendOTP()
  {
    clearInterval(this.interval);
    this.timeleft = 90;
    this.startTimer();
    
    let username:string[] = localStorage.getItem("username").split(" ");
    this.userService.sendOTP(username[1]).subscribe(data=>{
      console.log(data)
    })
  }



}
