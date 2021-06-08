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
import { FileUploadService } from 'src/app/core/services/file-upload.service';
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
  showImageSpinner:boolean=true

  showProgressbar:boolean=false

  load()
  {
    this.showImageSpinner=false
  }

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  isShow:boolean=false
  showForm:boolean=false
  timeLinePost:Timelinepost;

  isPostCreated:boolean=false

  constructor(private fileService:FileUploadService,private router:Router,private timelineService:TimeLineService, private _snackBar: MatSnackBar,private _authService:authentication,private userSerice:UsersService) { }

  ngOnInit(): void {

    // this.imageURL = localStorage.getItem("imageURL")
   
    // if(this.imageURL!=null)
    // {
    //   this.fileService.delete(this.imageURL)
    //   console.log("deleted");
    //   localStorage.removeItem("imageURL")
      
    // }
  }

  // ngOnDestroy()
  // {
  //   if(this.isPostCreated==false)
  //   {
  //     if(this.imageURL!=null)
  //     {
  //       this.fileService.delete(this.imageURL)
  //       localStorage.removeItem("imageURL")
  //     }
       
  //     console.log("Bye Bye");
      
  //   }
  // }


  show(isShow):void
  {
    this.showImageSpinner=true
    this.showForm=isShow
    
    
    this.imageURL = localStorage.getItem("imageURL")
     localStorage.removeItem("imageURL")
    console.log(this.imageURL);
    
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
            //this.isPostCreated=true
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
