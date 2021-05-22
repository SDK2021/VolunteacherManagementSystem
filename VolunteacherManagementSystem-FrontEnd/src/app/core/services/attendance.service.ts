import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { Attendance } from '../model/attendance';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {

  constructor(private _httpclient:HttpClient) { }

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

  getattendancelist():Observable<Attendance[]>{

    return this._httpclient.get<Attendance[]>(`${"http://localhost:9090/vms/attendances"}`)
    .pipe(retry(1),catchError(this.handleError));
    
  }
}
