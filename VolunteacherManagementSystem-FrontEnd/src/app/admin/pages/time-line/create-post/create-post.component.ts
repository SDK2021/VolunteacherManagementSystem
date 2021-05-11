import { Component, OnInit } from '@angular/core';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { User } from 'src/app/core/model/user';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { TimeLineService } from 'src/app/shared/shared-services/time-line.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  post:Timelinepost=new Timelinepost()
  baseUrl:string="/vms/users/posts"
  imageURL:string;

  showProgressbar:boolean=false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  isShow:boolean=false
  showForm:boolean=false
  timeLinePost:Timelinepost;
  constructor(private router:Router,private timelineService:TimeLineService, private _snackBar: MatSnackBar,private _authService:authentication,private userSerice:UsersService) { }

  ngOnInit(): void {
    this.imageURL=null
  }

  

  show(isShow):void
  {
    this.showForm=isShow
    console.log(this.imageURL);
    
    this.imageURL = localStorage.getItem("imageURL")
    localStorage.removeItem("imageURL")
    
  }
  openSnackBar() {
    this._snackBar.open('Posted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  createPost()
  {
    this.showProgressbar=true
    let user:User
    let authUser:string[]
    this.timeLinePost = new Timelinepost()
    this.timeLinePost.postPhoto = this.imageURL
    this.timeLinePost.postDescription = this.post.postDescription;

    if(this._authService.isUserLogin())
    {
        authUser=localStorage.getItem(this._authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
        this.userSerice.getUserByEmail(atob(authUser[0])).pipe(finalize(()=>{
          this.timeLinePost.createdBy = user
          console.log(this.timeLinePost)
          this.timelineService.createTimelinePost(this.timeLinePost).subscribe(data=>{
            console.log(data)
            this.showProgressbar=false
            this.openSnackBar()
            this.router.navigate(['/admin/post'])
          },error=>{
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
          })
        })).subscribe(data=>{
          user = data
          console.log(data)
        })
    }

  }
}
