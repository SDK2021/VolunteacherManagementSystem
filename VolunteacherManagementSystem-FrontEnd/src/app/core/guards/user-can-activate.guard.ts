import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';

@Injectable({
  providedIn: 'root'
})
export class UserCanActivateGuard implements CanActivate {

  constructor(private router: Router, private userService: UsersService, private authService: authentication) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.authenticateUser()
  }

  authenticateUser() {
    if (this.authService.isUserLogin()) {
      let authuser: string[];
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      let b = this.userService.getUserByEmail(atob(authuser[0])).pipe(take(1), map(data => {
        // alert(data.type.type)
        if (data.type.type === 'VOLUNTEACHER' || data.type.type === 'LOCAL VOLUNTEACHER')
          return true
        else
          return this.router.createUrlTree(['/error'])
      }))
      return b
    }
    else {
      return this.router.createUrlTree(['/login'])
    }
  }

}
