import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Timelinepost } from 'src/app/core/model/timelinepost';

@Injectable({
  providedIn: 'root'
})
export class TimeLineService {

  constructor(private _httpclient:HttpClient) { }
  
  getTimelinePosts(page:number):Observable<Timelinepost[]>{
    return this._httpclient.get<Timelinepost[]>(`${"http://localhost:9090/vms/posts?page="}${page}`).pipe(retry(3))
  
  }

  createTimelinePost(post:Timelinepost):Observable<Timelinepost>
  {
    return this._httpclient.post<Timelinepost>(`${"http://localhost:9090/vms/posts"}`,post).pipe(retry(3))
  }

  deleteTimelinePost(id:number)
  {
    return this._httpclient.delete(`${"http://localhost:9090/vms/posts/"}${id}`).pipe(retry(3))
  }
}
