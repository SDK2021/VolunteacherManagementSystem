import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Announcement } from 'src/app/core/model/announcement';
import { User } from 'src/app/core/model/user';
import { Event } from 'src/app/core/model/event';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { Payment } from 'src/app/core/model/payment';

@Injectable({
  providedIn: 'root'
})
export class AppHomeService {

  constructor(private httpclient : HttpClient) { }


  getAnnouncements(page:number):Observable<Announcement[]>
  {
    return this.httpclient.get<Announcement[]>(`${"http://localhost:9090/vms/announcements?page="}${page}`).pipe(
      retry(3))
  }

  getUsersByDob():Observable<User[]>
  {
    return this.httpclient.get<User[]>(`${"http://localhost:9090/vms/birth-users"}`).pipe(
      retry(3)
    )
  }

  getEvents(page:number):Observable<Event[]>
  {
    return this.httpclient.get<Event[]>(`${"http://localhost:9090/vms/events?page="}${page}`).pipe(
      retry(3)
    )
  }  

  getNewUsers():Observable<Volunteacher[]>
  {
    return this.httpclient.get<Volunteacher[]>(`${"http://localhost:9090/vms/volunteachers/day"}`).pipe(
      retry(3)
    )
  }  

  addPayment(payment:Payment):Observable<Payment>
  {
    return this.httpclient.post<Payment>(`${"http://localhost:9090/vms/payments"}`,payment).pipe(retry(3))
  }

  alreadyRegister(eventId:number,email:string):Observable<boolean>
  {
    return this.httpclient.get<boolean>(`${"http://localhost:9090/vms/already-registered?event="}${eventId}${"&email="}${email}`).pipe(retry(3))
  }
}
