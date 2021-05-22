import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';

@Injectable({
  providedIn: 'root'
})
export class AuthCanActivateGuard implements CanActivate {

  constructor(private router:Router,private authService:authentication,private userService:UsersService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      if(this.authService.isUserLogin())
      {
          console.log("Helllo");
          
        let authuser: string[];
        authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
        let b = this.userService.getUserByEmail(atob(authuser[0])).pipe(take(1), map(data => {
       if (data.type.type === 'VOLUNTEACHER' || data.type.type === 'LOCAL VOLUNTEACHER')
       return this.router.createUrlTree(['/user'])
          else
            return this.router.createUrlTree(['/admin'])
        }))
        return b
      }
      else
        return true      
  }
  
}
