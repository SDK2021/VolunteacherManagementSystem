import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

import { User } from 'src/app/core/model/user';
import { authentication } from 'src/app/home/shared-services/authentication.service';

import { UsersService } from '../../services/users.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  oldPsinvalid:boolean = false
  showProgressbar:boolean=false
  constructor(private _snackBar: MatSnackBar,private userService:UsersService,private authService:authentication,private router:Router) { }
  updateSuccessfully:boolean = false
  ngOnInit(): void {
    this.updateSuccessfully = false
    this.oldPsinvalid = false
  }
  password:string=''
  oldPassword:string=''
  passwordMatch: boolean
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

  openSnackBar() {
    this._snackBar.open('Password changed  successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  updatePassword(val)
  {
    this.showProgressbar=true
    let username:string[]
    let userId:number
    let user:User =  new User()
    if(this.authService.isUserLogin())
    {
      let authUser:string[]
     
      authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ")
      this.userService.getUserByEmail(atob(authUser[0])).pipe(finalize(()=>{
      this.userService.updatePassword(val.newPass,user.userId,val.oldPass).subscribe(data=>
      {
          console.log(data + "success");
          this.router.navigate(['login'])
          this.updateSuccessfully = true
          this.showProgressbar=false
          this.openSnackBar()
          this.oldPsinvalid = false
      },error=>{
        if(error.status == 400)
        {
          console.log("Hello");
          
          this.updateSuccessfully = false
          this.oldPsinvalid = true
        }
        else
          this.handleError(error)
      })
    })).subscribe(userdata=>{
      user = userdata
      userId= userdata.userId
    })
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
}
