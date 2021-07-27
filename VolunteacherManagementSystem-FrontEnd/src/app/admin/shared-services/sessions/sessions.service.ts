import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Attendance } from 'src/app/core/model/attendance';
import { Content } from 'src/app/core/model/content';
import { Notification } from 'src/app/core/model/notification';
import { Session } from 'src/app/core/model/session';
import { Sessionreport } from 'src/app/core/model/sessionreport';
import { User } from 'src/app/core/model/user';

@Injectable({
  providedIn: 'root'
})
export class SessionsService {

  constructor(private httpclient:HttpClient) { }

  
  getNotifications(page:number,month:number,year:number,usertype:String):Observable<Notification[]>{
    console.log(usertype)
    return this.httpclient.get<Notification[]>(`${"http://localhost:9090/vms/notifications?page="}${page}${"&month="}${month}${"&year="}${year}${"&usertype="}${usertype}`)
    .pipe(retry(3));
  }

  downloadSessions():Observable<Object>
  {
    let header = {
      responseType: 'blob' as 'json',
      headers:new HttpHeaders({
      })
    }
    return this.httpclient.get(`${"http://localhost:9090/vms/sessions-download"}`,header).pipe(retry(3))
  }

  getSessionsByMonthAndYear(page:number,month:number,year:number):Observable<Session[]>
  {
      return this.httpclient.get<Session[]>(`${"http://localhost:9090/vms/sessions?page="}${page}${"&month="}${month}${"&year="}${year}`)
      .pipe(retry(3))
  }

  sessionById(sessionId:number):Observable<Session>
  {
    return this.httpclient.get<Session>(`${"http://localhost:9090/vms/sessions/"}${sessionId}`).pipe(retry(3))
  }

  addSessionReporting(sessionReporting:Sessionreport)
  {
    return this.httpclient.post<Session>(`${"http://localhost:9090/vms/session-reports"}`,sessionReporting).pipe(retry(3))
  }
  addSession(session:Session):Observable<Session>
  {
    return this.httpclient.post<Session>(`${"http://localhost:9090/vms/sessions/"}`,session).pipe(retry(3))
  }

  editSession(sessionId:number,session:Session):Observable<Session>
  {
    return this.httpclient.put<Session>(`${"http://localhost:9090/vms/sessions/"}${sessionId}`,session).pipe(retry(3))
  }
  deleteSession(id:number)
  {
    return this.httpclient.delete(`${"http://localhost:9090/vms/sessions/"}${id}`).pipe(retry(3))
  }
  
  getAllSessions(page:number):Observable<Session[]>
  {
    return this.httpclient.get<Session[]>(`${"http://localhost:9090/vms/all-sessions?page="}${page}`).pipe(retry(3))
  }

  getAllSessionReportsBySession(page:number,id:number):Observable<Sessionreport[]>
  {
    return this.httpclient.get<Sessionreport[]>(`${"http://localhost:9090/vms/session-reports?page="}${page}${"&sessionId="}${id}`).pipe(retry(3))
  }

  deleteSessionReport(id:number)
  {
    return this.httpclient.delete(`${"http://localhost:9090/vms/session-reports/"}${id}`).pipe(retry(3))
  }

  getKidsAttendance(gid:number,sid:number):Observable<Attendance[]>
  {
    return this.httpclient.get<Attendance[]>(`${"http://localhost:9090/vms/attendance?groupId="}${gid}${"&sessionId="}${sid}`).pipe(retry(3))
  }

  
  addContent(content:Content):Observable<Content>
  {
    return this.httpclient.post<Content>(`${"http://localhost:9090/vms/contents"}`,content).pipe(retry(3))
  }

  getContentByGroup(id:number):Observable<Content>
  {
    return this.httpclient.get<Content>(`${"http://localhost:9090/vms/contents/"}${id}`).pipe(retry(3))
  }
  
  getAllContents():Observable<Content[]>
  {
    return this.httpclient.get<Content[]>(`${"http://localhost:9090/vms/contents"}`).pipe(retry(3))
  }

  getSessionsByProject(page:number,pid:number):Observable<Session[]>
  {
    return this.httpclient.get<Session[]>(`${"http://localhost:9090/vms/sessions-project?page="}${page}${"&project="}${pid}`).pipe(retry(3))
  }

  getSessionsByVillage(page:number,vid:number):Observable<Session[]>
  {
    return this.httpclient.get<Session[]>(`${"http://localhost:9090/vms/sessions-village?page="}${page}${"&village="}${vid}`).pipe(retry(3))
  }

  
}
