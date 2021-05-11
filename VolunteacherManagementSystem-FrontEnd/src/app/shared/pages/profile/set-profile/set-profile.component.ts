import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
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
  private userId:number
  baseUrl:string="/vms/users/profile"
  constructor(private router:Router,private fileService:FileUploadService,private authService:authentication,private userService:UsersService,private profileService:ProfileService) { }

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

  onShow(isShow)
  {
    console.log(localStorage.getItem("imageURL"))
    let url:String = localStorage.getItem("imageURL")
    localStorage.removeItem('imageURL')
    let user:Array<string>
    user=localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
    
    this.userService.getUserByEmail(atob(user[0])).pipe(finalize(()=>{
      this.profileService.setProfile(url,this.userId).subscribe(data=>{
        console.log(data)
      })
    })).subscribe(data=>{
      //this.fileService.delete(data.photo)
      this.userId=data.userId 
    },error=>{
      this.handleError(error)
    })
  }
}
