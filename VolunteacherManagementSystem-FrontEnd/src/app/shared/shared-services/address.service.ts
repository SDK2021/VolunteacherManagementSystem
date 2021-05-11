import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
      return this.http.get<Country[]>(`${"http://localhost:9090/vms/countries"}`)
  }

  getStates(countryid:number):Observable<State[]>
  {
      return this.http.get<State[]>(`${"http://localhost:9090/vms/countries/"}${countryid}${"/states"}`)
  }

  getDistricts(stateId:number):Observable<District[]>
  {
      return this.http.get<District[]>(`${"http://localhost:9090/vms/states/"}${stateId}${"/districts"}`)
  }

  getDistrictById(districtId:number):Observable<District>
  {
      return this.http.get<District>(`${"http://localhost:9090/vms/districts/"}${districtId}`);
  }

  getVillages(talukaId:number):Observable<Village[]>
  {
    return this.http.get<Village[]>(`${"http://localhost:9090/vms/talukas/"}${talukaId}${"/villages"}`);
  }

//   getVillagesByTaluka(talukaId:number):Observable<Village[]>
//   {
//     return this._http.get<Village[]>(`${"http://localhost:9090/vms/talukas/"}${talukaId}${"/villages"}`);
//   }

  getAreas(viilageId:number):Observable<Area[]>
  {
    return this.http.get<Area[]>(`${"http://localhost:9090/vms/villages/"}${viilageId}${"/areas"}`);
  }

  getVillageByid(villageId:number):Observable<Village>
  {
    return this.http.get<Village>(`${"http://localhost:9090/vms/villages/"}${villageId}`)
  }

  getTalukaById(talukaId:number):Observable<Taluka>
  {
    return this.http.get<Taluka>(`${"http://localhost:9090/vms/talukas/"}${talukaId}`)
  }

  getAllArea():Observable<Area[]>
  {
    return this.http.get<Area[]>(`${"http://localhost:9090/vms/areas"}`)
  }

  getAllVillages():Observable<Village[]>
  {
    return this.http.get<Village[]>(`${"http://localhost:9090/vms/villages"}`);
  }

  getTalukas(districtId:number):Observable<Taluka[]>
  {
    return this.http.get<Taluka[]>(`${"http://localhost:9090/vms/districts/"}${districtId}${"/talukas"}`)
  }

  getGroupById(id:number):Observable<KidsGroup>
  {
    return this.http.get<KidsGroup>(`${"http://localhost:9090/vms/kids-groups/"}${id}`)
  }

  saveVillage(villageId:number,village:Village):Observable<Village>
  {
    return this.http.put<Village>(`${"http://localhost:9090/vms/villages/"}${villageId}`,village)
  }
}
