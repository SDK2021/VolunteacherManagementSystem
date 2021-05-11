import { Component, OnInit } from '@angular/core';
import { Sessionreport } from 'src/app/core/model/sessionreport';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { finalize } from 'rxjs/operators';
import { UsersService } from '../../services/users.service';
import { authentication } from 'src/app/home/shared-services/authentication.service';

@Component({
  selector: 'app-session-reporting',
  templateUrl: './session-reporting.component.html',
  styleUrls: ['./session-reporting.component.css']
})
export class SessionReportingComponent implements OnInit {

  feedback:Sessionreport = new Sessionreport()
  showProgressbar:boolean=false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';


  today:Date=new Date()
  constructor(private router:Router,private userService:UsersService,private authService:authentication, private route:ActivatedRoute,private sessionService:SessionsService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
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

  
  openSnackBar() {
    this._snackBar.open('Submitted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  addSessionReport()
  {
    this.showProgressbar=true
    let authUser:string[]
    authUser=localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
    this.userService.getUserByEmail(atob(authUser[0])).pipe(finalize(()=>{

      this.sessionService.sessionById(this.route.snapshot.params['id']).pipe(finalize(()=>{
          this.sessionService.addSessionReporting(this.feedback).subscribe(data=>{
            this.showProgressbar=false
            this.openSnackBar()
            this.router.navigate(['/user/home'])
          console.log(data);
        
      },error=>{
        this.handleError(error)
      })
    })).subscribe(data=>{
      this.feedback.session = data
    })
  })).subscribe(data=>{
    this.feedback.user = data
  })
} 
}
