import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Report } from 'src/app/core/model/report';
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
}
