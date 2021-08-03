import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { error } from "@angular/compiler/src/util";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { authentication } from './home/shared-services/authentication.service'

@Injectable()
export class httpinterceptor implements HttpInterceptor {

    constructor(private _auth:authentication) { }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if(this._auth.isUserLogin())
        {
          let token = localStorage.getItem(this._auth.LOCAL_STORAGE_ATTRIBUTE_USERNAME)
          let string = token.split(" ");
        //   console.log(req.url)
          req = req.clone({
            setHeaders: 
            {
                'authorization': "Basic " + btoa(atob(string[0])+ ":" + atob(string[1])),
                'Content-Type':'application/json'
            }
           })
        }
         return next.handle(req);
     }
  }