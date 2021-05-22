import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Applicantrequest } from '../model/applicantrequest';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

  constructor(private _httpclient:HttpClient) { }

 

  getEvent():Observable<Event[]>{

    return this._httpclient.get<Event[]>(`${"http://localhost:9090/vms/events?page=0"}`)
    .pipe(retry(1),catchError(this.handleError));

  }

  getoneuser(id:number):Observable<User>{
    
    return this._httpclient.get<User>(`${"http://localhost:9090/vms/users"}`+`${id}`)
    .pipe(retry(1),catchError(this.handleError));
  }
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Client side Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Server side : Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

  

}
