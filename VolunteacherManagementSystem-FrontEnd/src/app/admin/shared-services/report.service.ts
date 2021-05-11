import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Report } from 'src/app/core/model/report';

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
}
