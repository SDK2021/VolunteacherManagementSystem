import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private _snackBar: MatSnackBar,private userService:UsersService,private auth:authentication,private router:Router) { }
  updateSuccessfully:boolean = false
  password:string=''
  

  passwordMatch: boolean
  ngOnInit(): void {
    if(localStorage.getItem("username") == null)
    {
      this.router.navigate(['']);
    }
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
  checkPassword(conPass: string) {

    if (conPass.length > 0) {
      if (conPass == this.password) {
        this.passwordMatch = false
      }
      else {
        this.passwordMatch = true
      }
    }
  }
  openSnackBar() {
    this._snackBar.open('Password changed  successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  updatePassword(val)
  {
    let username:string[]
    if(localStorage.getItem("username")!=null)
      username = localStorage.getItem("username").split(" ");
    this.userService.updatePassword(val.newPass,+username[0]).subscribe(data=>
      {
        console.log(data + "success");
        this.router.navigate(['login'])
        this.updateSuccessfully = true
        this.openSnackBar()
        localStorage.removeItem("username")
      },error=>{
        this.updateSuccessfully = false
        this.handleError(error)
      })
  }

  ngOnDestroy():void
  {
    localStorage.removeItem("username")
  }
}
