import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/core/model/user';
import { UsersService } from 'src/app/user/services/users.service';

import { authentication } from '../../shared-services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  constructor(private userService:UsersService,private authService:authentication,private router:Router) {}

  ngOnInit() {
    
    if(this.authService.isUserLogin())
    {
      let authUser :string[];
      let user:User;

      authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ")
      this.userService.getUserByEmail(atob(authUser[0])).subscribe(data=>{
        user = data
        console.log(user);
        
        if(user.type.typeId == 1)
        {
          this.router.navigate(['admin/dashboard'])
        }
        else
        {
          this.router.navigate(['user/home'])
        }
      })
    
    }
    else
    {
      this.router.navigate(['login'])
    }
  }
  ngOnDestroy() {
  }

  errorMessage: string = "Invalid Username or Password"
  successMessage:string = "Login Successfully"
  invalidLogin:boolean = false;
  successLogin:boolean = false;

  loginAuth(value)
  {
    this.authService.loginAuthentication(value.username,value.password).subscribe(
      (data) => 
      { 
        console.log("success" + data);
        this.invalidLogin = false
        this.successLogin = true
        let authUser :string[];
        let user:User;

      authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ")
      this.userService.getUserByEmail(atob(authUser[0])).subscribe(data=>{
        user = data
        console.log(user);
        
        if(user.type.typeId == 1)
        {
          this.router.navigate(['admin/dashboard'])
        }
        else
        {
          this.router.navigate(['user/home'])
        }
      })
      },
      (error) => 
      {
      console.log(error);
       this.invalidLogin = true;
       this.successLogin = false;
      },
    );
    console.log(value);
  }
  
}

