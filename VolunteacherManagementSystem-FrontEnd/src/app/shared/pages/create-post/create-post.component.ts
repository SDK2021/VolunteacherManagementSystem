import { Component, OnInit, ViewChild } from '@angular/core';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { UploadImgComponent } from 'src/app/core/components/upload-img/upload-img.component';
import { FileUpload } from 'src/app/core/model/file-upload';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { User } from 'src/app/core/model/user';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';
import { TimeLineService } from '../../shared-services/time-line.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})

export class CreatePostComponent implements OnInit {

  post: Timelinepost = new Timelinepost()
  baseUrl: string = "/vms/users/posts"
  imageURL: string = null;

  croppedImage: any = ''
  percentage: number = 0
  @ViewChild(UploadImgComponent) uploadImageComponent: UploadImgComponent

  showProgressbar: boolean = false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  isShow: boolean = false
  showForm: boolean = false
  timeLinePost: Timelinepost;
  isPostCreated: boolean = false

  

  constructor(private fileService: FileUploadService, private router: Router, private timelineService: TimeLineService, private _snackBar: MatSnackBar, private _authService: authentication, private userSerice: UsersService) { }

  ngOnInit(): void {
  }
 
  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else {
      this.router.navigate(['error-page'])
    }
  }

  show(isShow): void {
    this.showForm = isShow
  }
  openSnackBar() {
    this._snackBar.open('Posted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  createPost() {

    this.showProgressbar = true
    const file = this.uploadImageComponent.image;
    this.fileService.pushFileToStorage(new FileUpload(file), this.baseUrl).subscribe(
      percentage => {
        this.percentage = Math.round(percentage);

        if (this.percentage == 100) {


          this.fileService.imageUrl.subscribe(data => {
            this.imageURL = data
            //alert("in component..." + this.imageURL)
            if (this.imageURL != null && this.isPostCreated == false) {
              let user: User
              let authUser: string[]
              let userID: number
              this.timeLinePost = new Timelinepost()
              this.timeLinePost.postPhoto = this.imageURL
              localStorage.removeItem('imageURL')
              //alert("Alerting photo")
              //alert(this.imageURL)
              this.timeLinePost.postDescription = this.post.postDescription;

              if (this._authService.isUserLogin()) {
                authUser = localStorage.getItem(this._authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
                this.userSerice.getUserByEmail(atob(authUser[0])).pipe(finalize(() => {
                  this.timeLinePost.createdBy = user
                  console.log(this.timeLinePost)
                  this.timelineService.createTimelinePost(this.timeLinePost).subscribe(data => {
                    console.log(data)
                    this.isPostCreated = true
                    this.showProgressbar = false
                    this.router.navigate(['/user/posts'])
                  }, error => {
                    this.handleError(error)
                  })
                })).subscribe(data => {
                  user = data
                  console.log(data)
                })
              }
               this.isPostCreated = true
            }
          })
        }
      }, error => {
        this.handleError(error)
      }
    ),error=>{
      this.handleError(error)
    }}

  
  getCroppedImage(image) {
    this.croppedImage = image
  }

}


