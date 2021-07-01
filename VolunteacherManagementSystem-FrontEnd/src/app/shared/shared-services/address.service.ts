import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';
import { Area } from 'src/app/core/model/area';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http:HttpClient) { }

  getCountries():Observable<Country[]>
  {
      return this.http.get<Country[]>(`${"http://localhost:9090/vms/countries"}`).pipe(retry(3))
  }

  getStates(countryid:number):Observable<State[]>
  {
      return this.http.get<State[]>(`${"http://localhost:9090/vms/countries/"}${countryid}${"/states"}`).pipe(retry(3))
  }

  getDistricts(stateId:number):Observable<District[]>
  {
      return this.http.get<District[]>(`${"http://localhost:9090/vms/states/"}${stateId}${"/districts"}`).pipe(retry(3))
  }

  getDistrictById(districtId:number):Observable<District>
  {
      return this.http.get<District>(`${"http://localhost:9090/vms/districts/"}${districtId}`).pipe(retry(3))
  }

  getVillages(talukaId:number):Observable<Village[]>
  {
    return this.http.get<Village[]>(`${"http://localhost:9090/vms/talukas/"}${talukaId}${"/villages"}`).pipe(retry(3));
  }

//   getVillagesByTaluka(talukaId:number):Observable<Village[]>
//   {
//     return this._http.get<Village[]>(`${"http://localhost:9090/vms/talukas/"}${talukaId}${"/villages"}`);
//   }

  getAreas(viilageId:number):Observable<Area[]>
  {
    return this.http.get<Area[]>(`${"http://localhost:9090/vms/villages/"}${viilageId}${"/areas"}`).pipe(retry(3));
  }

  getVillageByid(villageId:number):Observable<Village>
  {
    return this.http.get<Village>(`${"http://localhost:9090/vms/villages/"}${villageId}`).pipe(retry(3))
  }

  getTalukaById(talukaId:number):Observable<Taluka>
  {
    return this.http.get<Taluka>(`${"http://localhost:9090/vms/talukas/"}${talukaId}`).pipe(retry(3))
  }

  getAllArea():Observable<Area[]>
  {
    return this.http.get<Area[]>(`${"http://localhost:9090/vms/areas"}`).pipe(retry(3))
  }

  getAllVillages():Observable<Village[]>
  {
    return this.http.get<Village[]>(`${"http://localhost:9090/vms/villages"}`).pipe(retry(3));
  }

  getTalukas(districtId:number):Observable<Taluka[]>
  {
    return this.http.get<Taluka[]>(`${"http://localhost:9090/vms/districts/"}${districtId}${"/talukas"}`).pipe(retry(3))
  }

  getGroupById(id:number):Observable<KidsGroup>
  {
    return this.http.get<KidsGroup>(`${"http://localhost:9090/vms/kids-groups/"}${id}`).pipe(retry(3))
  }

  saveVillage(villageId:number,village:Village):Observable<Village>
  {
    return this.http.put<Village>(`${"http://localhost:9090/vms/villages/"}${villageId}`,village).pipe(retry(3))
  }

  saveArea(areaId:number,area:Area):Observable<Area>
  {
    return this.http.put<Area>(`${"http://localhost:9090/vms/areas/"}${areaId}`,area).pipe(retry(3))
  }

  deleteArea(areaId:number)
  {
    return this.http.delete(`${"http://localhost:9090/vms/areas/"}${areaId}`).pipe(retry(3))
  }

  deleteVillage(viilageId:number)
  {
    return this.http.delete(`${"http://localhost:9090/vms/villages/"}${viilageId}`).pipe(retry(3));
  }

  deleteGroup(id:number)
  {
    return this.http.delete(`${"http://localhost:9090/vms/kids-groups/"}${id}`).pipe(retry(3))
  }
}
