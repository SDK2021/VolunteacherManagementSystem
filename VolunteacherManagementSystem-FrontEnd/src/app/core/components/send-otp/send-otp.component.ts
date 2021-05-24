import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-send-otp',
  templateUrl: './send-otp.component.html',
  styleUrls: ['./send-otp.component.css']
})
export class SendOtpComponent implements OnInit {

  constructor(private router: Router, private userService: UsersService) { }
  invalidOTP: boolean = false
  timeleft: number = 90
  cnt: number = 0;
  interval: any;
  showSpinner: boolean = false
  email:string
  

  isEmailSent:boolean=false
  ngOnInit(): void {
    if(localStorage.getItem("username") == null)
    {
      this.router.navigate(['']);
    }    
    this.email = this.getMyEmail()
    console.log(this.email);
    
    console.log(this.timeleft)
    this.startTimer();
  }

  getMyEmail():string
  {
    let authuser:string[] = localStorage.getItem("username").split(" ")
    let email:string = ''
   
    return authuser[1].replace(/(.{2})(.*)(?=@)/,
    function(gp1, gp2, gp3) { 
      for(let i = 0; i < gp3.length; i++) { 
        gp2+= "*"; 
      } return gp2; 
    });
  }

  startTimer() {
    this.interval = setInterval(() => {
      if (this.timeleft > 0) {
        this.timeleft--;
      } else {
        this.pauseTimer();
      }
    }, 1000)
  }

  pauseTimer() {
    clearInterval(this.interval);
    this.sendOTP();
  }

  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else {
      this.router.navigate(['/error-page'])
    }

  }

  verifyOTP(val) {
    let username: string[]
    console.log(val.otp);
    if (localStorage.getItem("username") != null)
      username = localStorage.getItem("username").split(" ");
    this.userService.verifyOTP(val.otp, +username[0]).subscribe(data => {
      if (data == true) {
        this.invalidOTP = false

        this.router.navigate(['change-password'])
        clearInterval(this.interval);
      }
      else {
        this.invalidOTP = true
      }
    }, error => {
      this.handleError(error)
    })
  }

  sendOTP() {
    this.isEmailSent=false
    this.showSpinner = true
    clearInterval(this.interval);
    this.timeleft = 90;

    let username: string[]
    if (localStorage.getItem("username") != null)
      username = localStorage.getItem("username").split(" ");
    this.userService.sendOTP(username[1]).subscribe(data => {
      this.showSpinner = false
      this.isEmailSent=true
      this.startTimer();
      console.log(data)
    }, error => {
      this.handleError(error)
    })
  }



}
