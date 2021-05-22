import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Area } from 'src/app/core/model/area';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { Project } from 'src/app/core/model/project';
import { Village } from 'src/app/core/model/village';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  constructor(private http: HttpClient) { }


  addVillage(village: Village): Observable<Village> {
    return this.http.post<Village>(`${"http://localhost:9090/vms/villages"}`, village).pipe(retry(3))
  }

  addArea(area: Area): Observable<Area> {
    return this.http.post<Area>(`${"http://localhost:9090/vms/areas"}`, area).pipe(retry(3))
  }

  saveVillage(villageId:number,village:Village):Observable<Village>
  {
    console.log("Hello"+village);
    
    return this.http.put<Village>(`${"http://localhost:9090/vms/villages"}${villageId}`, village).pipe(retry(3))
  }

  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${"http://localhost:9090/vms/all-projects"}`).pipe(retry(3))
      
  }


  getProject(id: number): Observable<Project> {
    return this.http.get<Project>(`${"http://localhost:9090/vms/projects/"}` + `${id}`).pipe(retry(3))
     
  }

  addProject(project: Project, vIds: Number[], kIds: Number[]): Observable<Project> {
    let header = {
      headers:
        new HttpHeaders({
          'volunteacherIds': vIds.toString(),
          'kidsIds': kIds.toString()
        })
    }

    return this.http.post<Project>(`${"http://localhost:9090/vms/projects"}`, project, header).pipe(retry(3))
  }

  
  editProject(projectId:number,project: Project, vIds: Number[], kIds: Number[]): Observable<Project> {
    let header = {
      headers:
        new HttpHeaders({
          'volunteacherIds': vIds.toString(),
          'kidsIds': kIds.toString()
        })
    }

    return this.http.put<Project>(`${"http://localhost:9090/vms/projects/"}${projectId}`, project, header).pipe(retry(3))
  }

  deleteProject(id:number)
  {
    return this.http.delete(`${"http://localhost:9090/vms/projects/"}${id}`).pipe(retry(3))
  }

  getTotalVolunteachersByProject(id:number):Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/volunteachers-projects?project="}${id}`).pipe(retry(3))
  }

  getTotalSessionsByProject(id:number):Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/sessions-projects?project="}${id}`).pipe(retry(3))
  }

  getTotalKidsByProject(id:number):Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/kids-projects?project="}${id}`).pipe(retry(3))
  }

  getTotalEventsByProject(id:number):Observable<number>
  {
    return this.http.get<number>(`${"http://localhost:9090/vms/total-events-projects?project="}${id}`).pipe(retry(3))
  }
}
