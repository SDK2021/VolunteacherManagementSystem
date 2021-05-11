import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from 'src/app/core/model/project';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { Volunteacher } from 'src/app/core/model/volunteacher';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private _httpclient:HttpClient) { }

  getTotalProject(userId:number):Observable<number>
    {
        return this._httpclient.get<number>(`${"http://localhost:9090/vms/project-number/"}${userId}`)
    }

    getTotalSessions(userId:number):Observable<number>
    {
        return this._httpclient.get<number>(`${"http://localhost:9090/vms/total-sessions/"}${userId}`)
    }

    getTotalPosts(userId:number):Observable<number>
    {
        return this._httpclient.get<number>(`${"http://localhost:9090/vms/total-posts/"}${userId}`)
    }

    getAllPostByUser(page:number,userId:number):Observable<Timelinepost[]>
    {
      return this._httpclient.get<Timelinepost[]>(`${"http://localhost:9090/vms/user-posts?page="}${page}${"&user="}${userId}`)
    }

    getVolunteacherByUser(userId:number):Observable<Volunteacher>
    {
      return this._httpclient.get<Volunteacher>(`${"http://localhost:9090/vms/volunteachers/users?user="}${userId}`)
    }

    getAllProject():Observable<Project[]>
    {
        return this._httpclient.get<Project[]>(`${"http://localhost:9090/vms/projects-list"}`)
    }

    getAllProjectNumberByUser(userId:number):Observable<Number[]>
    {
        return this._httpclient.get<Number[]>(`${"http://localhost:9090/vms/project-numbers/"}${userId}`)
    }

    getProjectById(projectId:Number):Observable<Project>
    {
        return this._httpclient.get<Project>(`${"http://localhost:9090/vms/projects/"}${projectId}`)
    }

    setProfile(url:String,userId:number):Observable<boolean>
    {
      let header = {
        headers:new HttpHeaders({
          'profileURL':url.toString(),
          'userId':userId.toString()

        })
      }
      return this._httpclient.post<boolean>(`${"http://localhost:9090/vms/set-profile"}`,null,header)
    }

    
}
