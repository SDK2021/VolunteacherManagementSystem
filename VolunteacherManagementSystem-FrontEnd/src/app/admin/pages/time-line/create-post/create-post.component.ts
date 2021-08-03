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
import { TimeLineService } from 'src/app/shared/shared-services/time-line.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  post: Timelinepost = new Timelinepost()
  baseUrl: string = "/vms/users/posts"
  imageURL: string;
  showImageSpinner: boolean = true


  disabled:boolean=null

  showProgressbar: boolean = false

  load() {
    this.showImageSpinner = false
  }

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  isShow: boolean = false
  showForm: boolean = false
  timeLinePost: Timelinepost;

  croppedImage: any = ''
  percentage: number = 0
  @ViewChild(UploadImgComponent) uploadImageComponent: UploadImgComponent

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
    this.showImageSpinner = true
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
    this.disabled=true
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
                  this.timelineService.createTimelinePost(this.timeLinePost).subscribe(data => {
                    
                    this.isPostCreated = true
                    this.showProgressbar = false
                    this.disabled=false
                    this.router.navigate(['/admin/post'])
                  }, error => {
                    this.handleError(error)
                  })
                })).subscribe(data => {
                  user = data
                })
              }
              this.isPostCreated = true
              
            }
          })
        }
      }, error => {
        this.disabled=false
        this.handleError(error)
      }
    ), error => {
      this.disabled=false
      this.handleError(error)
    }
    
  }

  getCroppedImage(image) {
    this.croppedImage = image
  }
}
