import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Area } from 'src/app/core/model/area';
import { Attendance } from 'src/app/core/model/attendance';
import { Kid } from 'src/app/core/model/kid';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { Kidsreport } from 'src/app/core/model/kidsreport';
import { Session } from 'src/app/core/model/session';
import { Village } from 'src/app/core/model/village';

@Injectable({
  providedIn: 'root'
})
export class KidsService {

  constructor(private _httpclient:HttpClient) { }

  
  
  addParticipants(kidIds:Number[],eventId:number):Observable<Event>
  {
    let header = {
      headers:new HttpHeaders({
        'kidsIds': kidIds.toString(),
        'eventId':eventId.toString()
      })
    }
    return this._httpclient.post<Event>(`${"http://localhost:9090/vms/kids-participants"}`,null,header);    
  }
  getkidsgrouplist():Observable<KidsGroup[]>{

    return this._httpclient.get<KidsGroup[]>(`${"http://localhost:9090/vms/kids-groups"}`)
    .pipe(retry(1));

  }

  getkidslist(page:number):Observable<Kid[]>{
    console.log("Hello");
    
    return this._httpclient.get<Kid[]>(`${"http://localhost:9090/vms/kids?page="}${page}`).pipe(retry(1));
    
  }

  getAllKidsByVillage(page:number,villageId:number):Observable<Kid[]>
  {
    return this._httpclient.get<Kid[]>(`${"http://localhost:9090/vms/village-kids?page="}${page}${"&village="}${villageId}`);
  }

  getAllKidsByArea(page:number,areaId:number):Observable<Kid[]>
  {
    return this._httpclient.get<Kid[]>(`${"http://localhost:9090/vms/area-kids?page="}${page}${"&area="}${areaId}`);
  }

  getAllKidsByVillageAndGroup(page:number,villageId:number, groupId:number):Observable<Kid[]>
  {
    return this._httpclient.get<Kid[]>(`${"http://localhost:9090/vms/village-group-kids?page="}${page}${"&village="}${villageId}${"&group="}${groupId}`)
  }

  getAllKidsByAreaAndGroupAndVillage(page:number,areaId:number, groupId:number, villageId:number):Observable<Kid[]>
  {
    return this._httpclient.get<Kid[]>(`${"http://localhost:9090/vms/area-group-village-kids?page="}${page}${"&area="}${areaId}${"&group="}${groupId}${"&village="}${villageId}`)
  }

  getKidReport(kidId:number):Observable<Kidsreport[]>
  {
    return this._httpclient.get<Kidsreport[]>(`${"http://localhost:9090/vms/kids/"}${kidId}${"/kids-reports"}`)
  }

  kidById(kidId:number):Observable<Kid>
  {
    return this._httpclient.get<Kid>(`${"http://localhost:9090/vms/kids/"}${kidId}`)
  }
  addKidReport(kidReport:Kidsreport):Observable<Kidsreport>
  {
    console.log(kidReport)
    //console.log("Hrllo")
    return this._httpclient.post<Kidsreport>(`${"http://localhost:9090/vms/kids-reports"}`,JSON.stringify(kidReport)) 
  }

  getAreaById(areaId:number):Observable<Area>
  { 
    return this._httpclient.get<Area>(`${"http://localhost:9090/vms/areas/"}${areaId}`)
  }

  addKid(kid:Kid):Observable<Kid>
  {
    return this._httpclient.post<Kid>(`${"http://localhost:9090/vms/kids"}`,JSON.stringify(kid));    
  }

  kidGroupById(groupId:number):Observable<KidsGroup>
  {
    return this._httpclient.get<KidsGroup>(`${"http://localhost:9090/vms/kids-groups/"}${groupId}`);
  }

  
  villageById(villageId:number):Observable<Village>
  {
    return this._httpclient.get<Village>(`${"http://localhost:9090/vms/villages/"}${villageId}`);
  }

  addKidsAttendance(attendance:Attendance,kidsids:Number[]):Observable<Attendance>
  {
    let header = {
      headers:new HttpHeaders({
        'kidsIds':kidsids.toString()
      })
    }
    return this._httpclient.post<Attendance>(`${"http://localhost:9090/vms/add-kids-attendance"}`,attendance,header);
  }

  sessionById(sessionId:number):Observable<Session>
  {
    return this._httpclient.get<Session>(`${"http://localhost:9090/vms/sessions/"}${sessionId}`)
    .pipe(retry(1));
  }

  getAllKidsByGroup(page:number,groupId:number):Observable<Kid[]>
  {
    return this._httpclient.get<Kid[]>(`${"http://localhost:9090/vms/group-kids?page="}${page}${"&group="}${groupId}`)
  }

  kidReportById(id:number):Observable<Kidsreport>
  {
    return this._httpclient.get<Kidsreport>(`${"http://localhost:9090/vms/kids-reports/"}${id}`)
  }

  editKidsGroup(groupId:number,KidsGroup:KidsGroup):Observable<KidsGroup>
  {
    return this._httpclient.put<KidsGroup>(`${"http://localhost:9090/vms/kids-groups/"}${groupId}`,KidsGroup)
  }
}
