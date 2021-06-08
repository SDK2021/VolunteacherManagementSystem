import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { User } from 'src/app/core/model/user';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-set-profile',
  templateUrl: './set-profile.component.html',
  styleUrls: ['./set-profile.component.css']
})
export class SetProfileComponent implements OnInit {

  //get User Id
  private userId: number
  baseUrl: string = "/vms/users/profile"
  imageURL: string =null
  user: User = new User()
  oldImage: String = ''
  isPhotoEdited: boolean = false
  showSpinner:boolean=false
  constructor(private router: Router, private fileService: FileUploadService, private authService: authentication, private userService: UsersService, private profileService: ProfileService) { }

  ngOnInit(): void {
    // this.showSpinner=false
    // this.imageURL = localStorage.getItem("imageURL")

    // if (this.imageURL != null) {
    //   this.fileService.delete(this.imageURL)
    //   console.log("deleted");
    //   localStorage.removeItem("imageURL")

    // }

  }

  // ngOnDestroy() {
  //   if (this.isPhotoEdited == false) {
  //     if (this.imageURL != null) {
  //       this.fileService.delete(this.imageURL)
  //       localStorage.removeItem("imageURL")
  //     }

  //     console.log("Bye Bye");

  //   }
  // }

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

  onShow(isShow) {
    console.log("Hello i am first");
    this.imageURL = localStorage.getItem("imageURL")
    // localStorage.removeItem("imageURL")
    console.log(isShow);
    if(isShow)
      this.uploadPhoto()
  }

  uploadPhoto()
  {

    this.showSpinner=true
    let user: Array<string>
    user = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')

    this.userService.getUserByEmail(atob(user[0])).pipe(finalize(() => {
      this.profileService.setProfile(this.user.photo, this.userId).subscribe(data => {
        console.log(data)
        // this.isPhotoEdited = true
        // if (this.oldImage != null) {
        //   this.fileService.delete(this.oldImage)
        //   localStorage.removeItem("imageURL")
         
        // }
        // localStorage.removeItem("imageURL")
        this.showSpinner=false
        if(this.user.type.typeId==1)
          this.router.navigate(['/admin/profile/posts'])
        else
          this.router.navigate(['/user/profile/posts'])
      })
    })).subscribe(data => {
      this.user = data
      this.userId = data.userId
      // if (this.imageURL != null) {
      //   this.oldImage = this.user.photo
      //   this.user.photo = this.imageURL
      // }
      this.user.photo = this.imageURL
    }, error => {
      this.handleError(error)
    })
  }
}
