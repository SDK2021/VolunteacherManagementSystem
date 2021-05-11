import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Applicantrequest } from 'src/app/core/model/applicantrequest';
import { Participant } from 'src/app/core/model/participant';
import { User } from 'src/app/core/model/user';
import { Usertype } from 'src/app/core/model/usertype';
import { Volunteacher } from 'src/app/core/model/volunteacher';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

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

  getUserByEmail(email:string):Observable<User>
  {
    console.log(email);
    return this._httpclient.get<User>(`${"http://localhost:9090/vms/email-users?email="}${email}`)
  }

  registration(registerUser:Applicantrequest):Observable<Applicantrequest>
  {
    
    return this._httpclient.post<Applicantrequest>(`${"http://localhost:9090/vms/applicant-requests"}`,registerUser)
  }

  getUserType(id:number):Observable<Usertype>
  {
    return this._httpclient.get<Usertype>(`${"http://localhost:9090/vms/user-types/"}${id}`)
  }

  getEvents():Observable<Event[]>
  {
    return this._httpclient.get<Event[]>(`${"http://localhost:9090/vms/events?page=0"}`)
  }

  sendOTP(email:string):Observable<User>
  {
    return this._httpclient.post<User>(`${"http://localhost:9090/vms/send-otp?email="}${email}`,null)
  }

  verifyOTP(otp:string,userId:number):Observable<boolean>
  {
    return this._httpclient.post<boolean>(`${"http://localhost:9090/vms/verify-otp?otp="}${otp}${"&userid="}${userId}`,null)
  }

  updatePassword(password:string,userId:number)
  {
    return this._httpclient.post<boolean>(`${"http://localhost:9090/vms/update-password?password="}${password}${"&userid="}${userId}`,null)
  }
  getAllUserType():Observable<Usertype[]>
  {
    return this._httpclient.get<Usertype[]>(`${"http://localhost:9090/vms/user-types"}`)
  }
 
  saveVolunteacher(volunteacherId:number,volunteacher:Volunteacher):Observable<Volunteacher>
  {
    return this._httpclient.put<Volunteacher>(`${"http://localhost:9090/vms/volunteachers/"}${volunteacherId}`,volunteacher)
  }
}
