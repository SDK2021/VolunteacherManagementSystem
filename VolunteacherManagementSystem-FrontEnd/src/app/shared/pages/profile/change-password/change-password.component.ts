import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private userService:UsersService,private auth:authentication,private router:Router) { }
  updateSuccessfully:boolean = false
  ngOnInit(): void {
    this.updateSuccessfully = false
  }

  handleError(error)
  {
    console.log(error);
    console.log(error.status);
    
    if(error.status===500)
    {
      this.router.navigate(['internal-server-error'])
    }
    else
    {
      this.router.navigate(['error-page'])
    }
  }

  updatePassword(val)
  {
    let username:string[] = localStorage.getItem("username").split(" ");
    this.userService.updatePassword(val.newPass,+username[0]).subscribe(data=>
      {
        console.log(data + "success");
        this.router.navigate(['login'])
        this.updateSuccessfully = true
        localStorage.removeItem("username")
      },error=>{
        this.updateSuccessfully = false
        this.handleError(error)
      })
  }


}
