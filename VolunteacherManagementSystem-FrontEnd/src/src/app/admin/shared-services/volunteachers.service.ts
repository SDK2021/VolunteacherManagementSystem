import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Applicantrequest } from 'src/app/core/model/applicantrequest';
import { Kid } from 'src/app/core/model/kid';
import { User } from 'src/app/core/model/user';
import { Volunteacher } from 'src/app/core/model/volunteacher';

@Injectable({
  providedIn: 'root'
})
export class VolunteachersService {

  constructor(private http:HttpClient) { }
  
  downloadVolunteachers():Observable<Object>
  {

    let header = {
      responseType: 'blob' as 'json',
      headers:new HttpHeaders({
      })
    }
    return this.http.get(`${"http://localhost:9090/vms/volunteachers-download"}`,header)
  }

  getAllVolunteachers(page:number):Observable<Volunteacher[]>
  {
     return this.http.get<Volunteacher[]>(`${"http://localhost:9090/vms/volunteachers?page="}${page}`)
     .pipe(retry(3))
  }

  getRemainingVolunteachers(projectId:number):Observable<User[]>
  {
    return this.http.get<User[]>(`${"http://localhost:9090/vms/remaining-volunteachers?project="}${projectId}`)
     .pipe(retry(3))
  }

  getRemainingKids(projectId:number):Observable<Kid[]>
  {
    return this.http.get<Kid[]>(`${"http://localhost:9090/vms/remaining-kids?project="}${projectId}`)
     .pipe(retry(3))
  }

  acceptRequest(requestId:number)
  {
    return this.http.post<Applicantrequest>(`${"http://localhost:9090/vms/request-success/"}${requestId}`,null)
  }

  deniedRequest(requestId:number)
  {
    return this.http.post<Applicantrequest>(`${"http://localhost:9090/vms/request-denied/"}${requestId}`,null)
  }
  //Users Requests

  getApplicantrequests(page:number):Observable<Applicantrequest[]>{
  
    return this.http.get<Applicantrequest[]>(`${"http://localhost:9090/vms/applicant-requests?page="}${page}`)
    .pipe(retry(3));
    
  }

  getVoluntecherByid(id:number):Observable<Volunteacher>
  {
    return this.http.get<Volunteacher>(`${"http://localhost:9090/vms/volunteachers/"}${id}`).pipe(retry(3))
  }

  deleteVolunteacher(id:number)
  {
      return this.http.delete(`${"http://localhost:9090/vms/volunteachers/"}${id}`).pipe(retry(3))
  }
}
