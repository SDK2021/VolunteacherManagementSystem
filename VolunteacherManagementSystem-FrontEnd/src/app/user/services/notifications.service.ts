import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Notification } from 'src/app/core/model/notification';
import { Participant } from 'src/app/core/model/participant';
import { Session } from 'src/app/core/model/session';
import { User } from 'src/app/core/model/user';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

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

  getNotifications(page:number,usertype:String):Observable<Notification[]>{
    console.log(usertype)
    let today:Date =new Date()
    return this._httpclient.get<Notification[]>(`${"http://localhost:9090/vms/users-notifications?page="}${page}${"&month="}${today.getMonth()+1}${"&year="}${today.getFullYear()}${"&usertype="}${usertype}`)
    .pipe(retry(3),catchError(this.handleError));
  }

  addVolunteacher(users:User[],sessionId:number):Observable<Session>
  {
    let header = {
      headers:new HttpHeaders({
        "sessionId":sessionId.toString()
      })
    }
    return this._httpclient.post<Session>(`${"http://localhost:9090/vms/session-volunteachers"}`,users,header).pipe(retry(3),catchError(this.handleError));;
  }

  addVTParticipant(participant:Participant,eventId:number):Observable<Participant>
  {
    return this._httpclient.post<Participant>(`${"http://localhost:9090/vms/participants"}`,participant).pipe(retry(3),catchError(this.handleError));;
  }

  addNotification(notification:Notification):Observable<Notification>
  {
    return this._httpclient.post<Notification>(`${"http://localhost:9090/vms/notifications"}`,notification)
  }
}
