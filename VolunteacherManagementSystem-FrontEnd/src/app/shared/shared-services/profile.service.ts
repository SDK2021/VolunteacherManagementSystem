import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { retry } from 'rxjs/operators';
import { Project } from 'src/app/core/model/project';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { Volunteacher } from 'src/app/core/model/volunteacher';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  userProfileImage = new BehaviorSubject<string>(null)

  adminProfileImage = new BehaviorSubject<string>(null)

  constructor(private _httpclient: HttpClient) { }

  getTotalProject(userId: number): Observable<number> {
    return this._httpclient.get<number>(`${"http://localhost:9090/vms/project-number/"}${userId}`).pipe(retry(3))
  }

  getTotalSessions(userId: number): Observable<number> {
    return this._httpclient.get<number>(`${"http://localhost:9090/vms/total-sessions/"}${userId}`).pipe(retry(3))
  }

  getTotalPosts(userId: number): Observable<number> {
    return this._httpclient.get<number>(`${"http://localhost:9090/vms/total-posts/"}${userId}`).pipe(retry(3))
  }

  getAllPostByUser(page: number, userId: number): Observable<Timelinepost[]> {
    return this._httpclient.get<Timelinepost[]>(`${"http://localhost:9090/vms/user-posts?page="}${page}${"&user="}${userId}`).pipe(retry(3))
  }

  getVolunteacherByUser(userId: number): Observable<Volunteacher> {
    return this._httpclient.get<Volunteacher>(`${"http://localhost:9090/vms/volunteachers/users?user="}${userId}`).pipe(retry(3))
  }

  getAllProject(): Observable<Project[]> {
    return this._httpclient.get<Project[]>(`${"http://localhost:9090/vms/projects-list"}`).pipe(retry(3))
  }

  getAllProjectNumberByUser(userId: number): Observable<Number[]> {
    return this._httpclient.get<Number[]>(`${"http://localhost:9090/vms/project-numbers/"}${userId}`).pipe(retry(3))
  }

  getProjectById(projectId: Number): Observable<Project> {
    return this._httpclient.get<Project>(`${"http://localhost:9090/vms/projects/"}${projectId}`).pipe(retry(3))
  }

  setProfile(url: String, userId: number): Observable<boolean> {
    let header = {
      headers: new HttpHeaders({
        'profileURL': url.toString(),
        'userId': userId.toString()

      })
    }
    return this._httpclient.post<boolean>(`${"http://localhost:9090/vms/set-profile"}`, null, header).pipe(retry(3))
  }

  savePost(postId: number, post: Timelinepost): Observable<Timelinepost> {
    return this._httpclient.put<Timelinepost>(`${"http://localhost:9090/vms/posts/"}${postId}`, post).pipe(retry(3))
  }

  postById(postId: number): Observable<Timelinepost> {
    return this._httpclient.get<Timelinepost>(`${"http://localhost:9090/vms/posts/"}${postId}`).pipe(retry(3))
  }
}
