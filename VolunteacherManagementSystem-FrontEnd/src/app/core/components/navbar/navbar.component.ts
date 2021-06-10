import { Component, OnInit, ElementRef, Injectable } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';
import { User } from '../../model/user';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

@Injectable({
  providedIn:'root'
})
export class NavbarComponent implements OnInit {
  public focus;
  public listTitles: any[];
  public location: Location;
  userType:string=''
  user:User = new User()

  showImageSpinner:boolean=true

  constructor(private profileService:ProfileService,location: Location,  private element: ElementRef, private router: Router,private authService:authentication, private userService:UsersService) {
    this.location = location;
  }
  oldImage:string=null
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
  ngOnInit() {

    
    if(this.router.url.split('/')[1]=="admin")
      this.userType="admin"
    
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    let user:Array<string>
    user=localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')

    this.userService.getUserByEmail(atob(user[0])).subscribe(data=>{
      this.user=data
      if(data.type.typeId==1)
      {
        this.userType='admin'
        this.profileService.adminProfileImage.subscribe(data=>{
          if(data!=null)
            this.user.photo=data
        })
      }    
      else{
        this.userType='user'
        this.profileService.userProfileImage.subscribe(data=>{
          if(data!=null)
            this.user.photo=data
        })
      }  
      console.log(this.user);
         
    },error=>{
      this.handleError(error)
    })
  }

  
  load()
  {
    this.showImageSpinner=false
  }

  logout()
  {
    this.authService.logout();
    this.router.navigate([''])

  }

}
