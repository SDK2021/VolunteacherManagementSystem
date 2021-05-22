import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Area } from 'src/app/core/model/area';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { AddKidsComponent } from 'src/app/kids/pages/add-kids/add-kids.component';
import { AddParticipantsComponent } from 'src/app/kids/pages/add-participants/add-participants.component';
import { KidsAttendanceComponent } from 'src/app/kids/pages/kids-attendance/kids-attendance.component';
import { KidsListComponent } from 'src/app/kids/pages/kids-list/kids-list.component';
import { KidsService } from 'src/app/kids/shared-services/kids.service';
import {AdminKidsListComponent} from 'src/app/admin/pages/kids/admin-kids-list/admin-kids-list.component'

import { AddressService } from '../../shared-services/address.service';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})


@Injectable({
  providedIn: 'root'
})
export class AddressComponent implements OnInit {

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  villages:Array<Village>
  areas:Array<Area>
  groups:Array<KidsGroup>
  isShow:boolean = true
  url:string;

  stateSelected:number;
  districtSelected:number;
  talukaSelected:number;
  villageSelected:number;
  areaSelected:number;
  groupSelected:number;

  constructor(private  adminKids:AdminKidsListComponent, private attendanceKid:KidsAttendanceComponent, private addKid:AddKidsComponent, private router:Router ,
    private addressService:AddressService,private kidsService:KidsService ,private _kids:KidsListComponent,private addParti:AddParticipantsComponent) {

   }

  ngOnInit(): void {
    this.isShow = true
    this.getkidsgroup()
    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.getAllVillages();

    this.stateSelected = 7;
    this.districtSelected = 141;
    this.talukaSelected = 35;
    this.villageSelected = 0;
    this.areaSelected = 0;
    this. groupSelected = 0;

    this.url = this.router.url
  }

  handleError(error)
  {
    console.log(error);
    console.log(error.status);
    
    if(error.status===500)
    {
      this.router.navigate(['internal-server-error'])
    }
    else
    {
      this.router.navigate(['error-page'])
    }
  }
  
  getAllCountries()
  {
    this.addressService.getCountries().subscribe(data=>{
      this.countries = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedCountry(event)
  {
    this.addressService.getStates(event.target.value).subscribe(data=>{
      this.states = data
    },error=>{
      this.handleError(error)
    })
  }

  getAllStates() 
  {
    this.addressService.getStates(8).subscribe(data=>{
      this.states = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedState(event)
  {
    this.stateSelected = event.target.value;
    this.isShow = false
    this.villageSelected = 0
    this.talukaSelected = 0
    this.districtSelected = 0
    this.areaSelected = 0
    this.talukas = []
    this.villages = []
    this.areas = []
    if(event.target.value > 0)
    {
      this.addressService.getDistricts(event.target.value).subscribe(data=>{
      this.districts = data
      },error=>{
        this.handleError(error)
      })
    }
  }

  getAllDistricts() 
  {
    this.addressService.getDistricts(7).subscribe(data=>{
    this.districts = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedDistrict(event)
  {
    this.villageSelected = 0
    this.talukaSelected = 0
    this.areaSelected = 0
    this.isShow = false
    if(event.target.value > 0)
    {
      this.districtSelected = event.target.value;
      this.addressService.getTalukas(event.target.value).subscribe(data=>{
      this.talukas = data
      this.areas=[]
      this.villages = []
      },error=>{
        this.handleError(error)
      })
    }
    else{
      this.talukas = []
      this.villages = []
      this.areas = []
      this.districtSelected = 0
    }
  }

  getAllTalukas() 
  {
    this.addressService.getTalukas(141).subscribe(data=>{
    this.talukas = data
 //   this._kids.getkids(0);
    },error=>{
      this.handleError(error)
    })
  }

  selectedTaluka(event)
  {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if(event.target.value != 0)
    {
          this.addressService.getVillages(event.target.value).subscribe(data=>{
          this.villages = data
          this.areas = []
          if(this.url.endsWith("/add-participants"))
          {
            this.addParti.getKidsByTaluka(event.target.value,"t")
          }
          if(this.url.endsWith("/kids-list"))
          {
            this._kids.getKidsByTaluka(event.target.value,"t")
          }
          if(this.url.endsWith("/kids-attendance"))
          {
            this.attendanceKid.getKidsByTaluka(event.target.value)
          }
          if(this.url.endsWith("/kids"))
          {
            this.adminKids.getKidsByTaluka(event.target.value)
          }
        },error=>{
          this.handleError(error)
        })
    }
    else
    {
      if(this.url.endsWith("/add-participants"))
      {
        this.addParti.getkids(0,"all");
      }
      if(this.url.endsWith("/kids-list"))
      {
        this._kids.getkids(0,"all");
      }
      if(this.url.endsWith("/kids-attendance"))
      {
        this.attendanceKid.getkids();
      }
      if(this.url.endsWith("/kids"))
      {
        this.adminKids.getkids(0,"all");
      }
      this.talukaSelected = 0
      this.villageSelected = 0
      this.areaSelected = 0
      this.areas = []
      this.villages = []
    }
  }

  getAllVillages() 
  {
    this.addressService.getVillages(35).subscribe(data=>{
    this.villages = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedVillage(event)
  {
    this.villageSelected = event.target.value;
    console.log(event.target.value);
    if(event.target.value!=0)
    {
        this.addressService.getAreas(event.target.value).subscribe(data=>{
        this.areas = data
        if(this.groupSelected !=0)
        {
          if(this.url.endsWith("/add-participants"))
          {
            this.addParti.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected,"vg")
          }
          if(this.url.endsWith("/kids-list"))
          {
            this._kids.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected,"vg")
          }
          if(this.url.endsWith("/kids-attendance"))
          {
            this.attendanceKid.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected)
          }
          if(this.url.endsWith("/kids"))
          {
            this.adminKids.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected,"vg")
          }
        }
        else
        {
          if(this.url.endsWith("/add-participants"))
          {
            this.addParti.getKidsByVillage(event.target.value,"v")
          }
          if(this.url.endsWith("/kids-list"))
          {
            this._kids.getKidsByVillage(event.target.value,"v")
          }
          if(this.url.endsWith("/kids-attendance"))
          {
            this.attendanceKid.getKidsByVillage(event.target.value)
          }
          if(this.url.endsWith("/kids"))
          {
            this.adminKids.getKidsByVillage(event.target.value,"v")
          }
        }
      },error=>{
        this.handleError(error)
      })
    }
    else
    {
      this.areas = []
      this.areaSelected = 0
      if(this.groupSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByGroup(this.groupSelected,"g")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByGroup(this.groupSelected,"g")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByGroup(this.groupSelected)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByGroup(this.groupSelected,"g")
        }
      }
      else
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getkids(0,"all");
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getkids(0,"all");
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getkids();
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getkids(0,"all");
        }
      }
    }
  }

  selectedArea(event)
  {
    this.areaSelected = event.target.value;
    this.addKid.selectedArea =  event.target.value;
    if(event.target.value!=0)
    {
      if(this.groupSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByAreaAndGroupAndVillage(this.areaSelected,this.groupSelected,this.villageSelected,"vga")
               }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByAreaAndGroupAndVillage(this.areaSelected,this.groupSelected,this.villageSelected,"vga")
        } 
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByAreaAndGroupAndVillage(this.areaSelected,event.target.value,this.villageSelected,"vga")
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByAreaAndGroupAndVillage(this.areaSelected,this.groupSelected,this.villageSelected,"vga")
        } 
      }
      else
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByArea(event.target.value,"a")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByArea(event.target.value,"a")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByArea(event.target.value)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByArea(event.target.value,"a")
        }
      }
    }
    else
    {
      if(this.groupSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected,"vg")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected,"vg")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByVillageAndGroup(this.villageSelected,this.groupSelected,"vg")
        }
      }
      else
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByVillage(this.villageSelected,"v")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByVillage(this.villageSelected,"v");
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByVillage(this.villageSelected);
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByVillage(this.villageSelected,"v");
        }
      }
    }
  }

  getkidsgroup()
  {
    this.kidsService.getkidsgrouplist().subscribe(data =>{
      this.groups=data;
    },error=>{
      this.handleError(error)
    });
    
  }

  selectedGroup(event)
  {
    this.groupSelected = event.target.value;
    this.addKid.selectedGroup = event.target.value;
    this.attendanceKid.groupSelected = event.target.value;
    if(event.target.value != 0)
    {
      if(this.areaSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByAreaAndGroupAndVillage(this.areaSelected,event.target.value,this.villageSelected,"vga")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByAreaAndGroupAndVillage(this.areaSelected,event.target.value,this.villageSelected,"vga")
        }  
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByAreaAndGroupAndVillage(this.areaSelected,event.target.value,this.villageSelected,"vga")
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByAreaAndGroupAndVillage(this.areaSelected,event.target.value,this.villageSelected,"vga")
        } 
      }
      else if(this.villageSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByVillageAndGroup(this.villageSelected,event.target.value,"vg")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByVillageAndGroup(this.villageSelected,event.target.value,"vg")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByVillageAndGroup(this.villageSelected,event.target.value)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByVillageAndGroup(this.villageSelected,event.target.value,"vg")
        }
      }
      else
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByGroup(event.target.value,"g")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByGroup(event.target.value,"g")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByGroup(event.target.value)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByGroup(event.target.value,"g")
        }
      }
    }
    else 
    {
      if(this.areaSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByArea(this.areaSelected,"a")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByArea(this.areaSelected,"a")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByArea(this.areaSelected)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByArea(this.areaSelected,"a")
        }
      }
      else if(this.villageSelected !=0)
      {
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getKidsByVillage(this.villageSelected,"v")
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getKidsByVillage(this.villageSelected,"v")
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getKidsByVillage(this.villageSelected)
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getKidsByVillage(this.villageSelected,"v")
        }
      }
      else{
        if(this.url.endsWith("/add-participants"))
        {
          this.addParti.getkids(0,"all");
        }
        if(this.url.endsWith("/kids-list"))
        {
          this._kids.getkids(0,"all");
        }
        if(this.url.endsWith("/kids-attendance"))
        {
          this.attendanceKid.getkids();
        }
        if(this.url.endsWith("/kids"))
        {
          this.adminKids.getkids(0,"all");
        }
      }
    }
  }

  trackGroupsById(index,g:KidsGroup)
  {
    return g.groupId
  }
  
  trackCountriesById(index,c:Country)
  {
    return c.countryId
  }
  
  trackStatesById(index,s:State)
  {
    return s.stateId
  }
  
  trackDistrictsById(index,d:District)
  {
    return d.districtId
  }
  
  trackTalukasById(index,t:Taluka)
  {
    return t.talukaId
  }
  
  trackVillagesById(index,v:Village)
  {
    return v.villageId
  }
  
  trackAreaById(index,a:Area)
  {
    return a.areaId
  }
}
