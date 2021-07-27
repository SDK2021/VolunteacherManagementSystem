import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Kid } from 'src/app/core/model/kid';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { Kidsreport } from 'src/app/core/model/kidsreport';

@Injectable({
  providedIn: 'root'
})
export class KidService {


  constructor(private http: HttpClient) { }

  getAllKidslist(): Observable<Kid[]> {
    return this.http.get<Kid[]>(`${"http://localhost:9090/vms/all-kids"}`).pipe(retry(3))
  }

  getAllKids(page: number): Observable<Kid[]> {
    return this.http.get<Kid[]>(`${"http://localhost:9090/vms/kids?page="}${page}`).pipe(retry(3))
  }

  addKidsGroup(kidGroup: KidsGroup): Observable<KidsGroup> {
    return this.http.post<KidsGroup>(`${"http://localhost:9090/vms/kids-groups"}`, kidGroup).pipe(retry(3))
  }

  deleteKid(id: number) {
    return this.http.delete(`${"http://localhost:9090/vms/kids/"}${id}`).pipe(retry(3))
  }

  getAllKidsReportByKid(id: number, year: number): Observable<Kidsreport[]> {
    return this.http.get<Kidsreport[]>(`${"http://localhost:9090/vms/kid-reports?kid="}${id}${"&year="}${year}`).pipe(retry(3))
  }

  downloadKids(): Observable<Object> {
    let header = {
      responseType: 'blob' as 'json',
      headers: new HttpHeaders({
      })
    }
    return this.http.get(`${"http://localhost:9090/vms/kids-download"}`, header).pipe(retry(3))
  }

  getKidsByStandard(page:number, std:number): Observable<Kid[]> {
    return this.http.get<Kid[]>(`${"http://localhost:9090/vms/kids-by-standard?page="}${page}${"&std="}${std}`).pipe(retry(3))
  }

  getKidsByLevel(page:number, level:number): Observable<Kid[]> {
    return this.http.get<Kid[]>(`${"http://localhost:9090/vms/level-kids?page="}${page}${"&level="}${level}`).pipe(retry(3))
  }


}
