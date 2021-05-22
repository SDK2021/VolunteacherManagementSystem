import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './core/model/user';
import { authentication } from './home/shared-services/authentication.service';
import { UsersService } from './user/services/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'argon-dashboard-angular';

  constructor(private authService:authentication,private userService:UsersService,private router:Router ){}

  ngOnInit() {
    
  //   if(this.authService.isUserLogin())
  //   {
  //     let authUser :string[];
  //     let user:User;

  //     authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ")
  //     this.userService.getUserByEmail(atob(authUser[0])).subscribe(data=>{
  //       user = data
  //       console.log(user);
        
  //       if(user.type.typeId == 1)
  //       {
  //         this.router.navigate(['/admin'])
  //       }
  //       else
  //       {
  //         this.router.navigate(['/user'])
  //       }
  //     })
    
  //   }
  //   else
  //   {
  //     this.router.navigate(['/login'])
  //   }
  // }
  }
}
