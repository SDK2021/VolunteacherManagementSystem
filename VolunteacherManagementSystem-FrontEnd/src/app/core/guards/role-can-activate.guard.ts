
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class RoleCanActivateGuard implements CanActivate {

  user: User = new User()
  u: User = new User()
  authenticated: boolean = false

  constructor(private userService: UsersService, private authService: authentication) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.authenticateUser()
  }

  authenticateUser()
  {
    let authuser: string[];
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      let b: Observable<boolean> = this.userService.getUserByEmail(atob(authuser[0])).pipe(map(data=>{
        alert(data.type.type)
        if(data.type.type==='VOLUNTEACHER')
          return true
        else
          return false
      }))
      return b
  }
}

