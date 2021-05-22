import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Requirement } from 'src/app/core/model/requirement';
import { School } from 'src/app/core/model/school';

@Injectable({
  providedIn: 'root'
})
export class SchoolService {
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
  constructor(private http:HttpClient) { }

  getAllSchool(page:number):Observable<School[]>
  {
     return this.http.get<School[]>(`${"http://localhost:9090/vms/schools?page="}${page}`)
  }

  getAllRequirements():Observable<Requirement[]>
  {
     return this.http.get<Requirement[]>(`${"http://localhost:9090/vms/requirements"}`)
  }

  addSchool(school:School):Observable<School>
  {
    return this.http.post<School>(`${"http://localhost:9090/vms/schools"}`,school)
  }

  editSchool(schoolId:number,school:School):Observable<School>
  {
    return this.http.put<School>(`${"http://localhost:9090/vms/schools/"}${schoolId}`,school)
  }

  deleteSchool(id:number)
  {
    return this.http.delete(`${"http://localhost:9090/vms/schools/"}${id}`)
  }

  getSchoolById(schoolId):Observable<School>
  {
    return this.http.get<School>(`${"http://localhost:9090/vms/schools/"}${schoolId}`)
  }

  downloadSchools():Observable<Object>
  {
    let header = {
      responseType: 'blob' as 'json',
      headers:new HttpHeaders({
      })
    }
    return this.http.get(`${"http://localhost:9090/vms/schools-download"}`,header)
  }
}
