import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Report } from 'src/app/core/model/report';
import { User } from 'src/app/core/model/user';
import { Volunteacher } from 'src/app/core/model/volunteacher';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http:HttpClient) { }

  getReportsByYear(year:number):Observable<Report[]>
  {
    //add argument year 
    return this.http.get<Report[]>(`${"http://localhost:9090/vms/reports?year="}${year}`)
    .pipe(retry(3))
  }

  getAllNewUsers():Observable<Volunteacher[]>
  {
    return this.http.get<Volunteacher[]>(`${"http://localhost:9090/vms/all-new-volunteachers"}`).pipe(retry(3))
  }

  getAllNewKids():Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/total-new-kids"}`).pipe(retry(3))
  }

  getTotalHours():Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/total-sessions-hours"}`).pipe(retry(3))
  }

  getTotalSessionByVillage(villageId:number):Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/total-village-sessions?village="}${villageId}`).pipe(retry(3))
  }

  getTotalKidsByVillage(villageId:number):Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/total-village-kids?village="}${villageId}`).pipe(retry(3))
  }

  getTotalUsersBySessionVillage(villageId:number):Observable<User[]>
  {
    return this.http.get<User[]>(`${"http://localhost:9090/vms/users-session-village?village="}${villageId}`).pipe(retry(3))
  }
}
