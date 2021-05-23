import { group } from '@angular/animations';
import { NumberSymbol } from '@angular/common';
import { Component, Injectable, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Kid } from 'src/app/core/model/kid';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { KidsService } from 'src/app/kids/shared-services/kids.service';

@Component({
  selector: 'app-admin-kids-list',
  templateUrl: './admin-kids-list.component.html',
  styleUrls: ['./admin-kids-list.component.css']
})

@Injectable({
  providedIn:'root'
})

export class AdminKidsListComponent implements OnInit {
  

  groups:Array<string>
  villages:Array<string>
  userType:string
  selectedVillage:number
  selectedArea:number
  selectedGroup:number
  totalKidsPages:number

  showSpinner:boolean=false
  noVillages:boolean=false
  kLength:number;

  page:number=0
  filter:string
  @Input() label:string 

  showImageSpinner:boolean=true

  search:string=''

  kidslist: Array<Kid>=new Array()
  constructor(private kidsService:KidsService,private _auth:authentication,private router:Router) {
    
    // let currentDate=new Date()
    // let bDate=new Date("03-17-2001")
    
    // let diffInSec= Math.abs(currentDate.getTime()-bDate.getTime())
    // console.log(diffInSec);
    
    // let age=(diffInSec/(1000 * 3600 * 24)/365)+1
    // console.log(age)
  }

  
  ngOnInit() 
  {
    this.page=0
    this.getkids(this.page,"all")
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

  load()
  {
    this.showImageSpinner=false
  }
  onScroll() {
    console.log("Hello");
    
    if(this.page < this.totalKidsPages - 1)
    {
      this.page += 1
      this.getPageableKids(this.page);
    }
  }
  getPageableKids(page: number) {
    if(this.filter === "all")
    {
      this.kidsService.getkidslist(page).subscribe(data =>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if(this.filter === "v")
    {
      this.kidsService.getAllKidsByVillage(this.page,this.selectedVillage).subscribe(data=>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if(this.filter === "g")
    {
      this.kidsService.getAllKidsByGroup(this.page,this.selectedGroup).subscribe(data=>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if(this.filter === "a")
    {
      this.kidsService.getAllKidsByArea(this.page,this.selectedArea).subscribe(data=>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if(this.filter === "vg")
    {
      this.kidsService.getAllKidsByVillageAndGroup(this.page,this.selectedVillage, this.selectedGroup).subscribe(data=>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if(this.filter === "vga")
    {
      this.kidsService.getAllKidsByAreaAndGroupAndVillage(this.page,this.selectedArea,this.selectedGroup,this.selectedVillage).subscribe(data=>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
  }

  getkids(page:number,filter:string)
  {
    this.page=0
    this.filter = filter
    this.showSpinner=true
    this.kidsService.getkidslist(page).subscribe(data =>{
      this.kidslist=data['content'];
      this.totalKidsPages = data['totalPages']
      this.kLength=this.kidslist.length
      this.showSpinner=false
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
      
    });
  }

  getKidsByTaluka(talukaId:number)
  {
    this.page =0
    this.kidsService.getkidslist(0).subscribe(data =>{
      for(var kid of this.kidslist)
      {
        if(kid.village.taluka.talukaId == talukaId)
        {
          this.kidslist.concat(kid)
          this.kidslist=this.calculateAge(this.kidslist)
          console.log(this.kidslist);
        }
      }
    });
  }

  getKidsByVillage(villageId:number,filter:string)
  {
    this.page = 0
    this.filter = filter
    this.selectedVillage = villageId
    this.kidsService.getAllKidsByVillage(0,villageId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
      console.log(this.kidslist)
      
    })
  }

  getKidsByArea(areaId:number,filter:string)
  {
    this.page= 0;
    this.filter= filter
    this.selectedArea = areaId
    this.kidsService.getAllKidsByArea(0,areaId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
    })
  }

  getKidsByVillageAndGroup(villageId:number, groupId:number,filter:string)
  {
    this.page = 0;
    this.filter = filter
    this.selectedVillage = villageId
    this.selectedGroup = groupId
    this.kidsService.getAllKidsByVillageAndGroup(0,villageId, groupId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
    })
  }

  getKidsByAreaAndGroupAndVillage(areaId:number, groupId:number, villageId:number,filter:string)
  {
    this.page = 0
    this.filter = filter
    this.selectedVillage = villageId
    this.selectedArea = areaId
    this.selectedGroup = groupId
    this.kidsService.getAllKidsByAreaAndGroupAndVillage(0,areaId,groupId,villageId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(data);
    })
  }

  getKidsByGroup(groupId:number,filter:string)
  {
    this.selectedGroup = groupId
    this.filter = filter
    this.page = 0
    this.kidsService.getAllKidsByGroup(this.page,groupId).subscribe(data=>{
      this.kidslist = data['content']
    })
  }

  calculateAge(kidsList:Array<Kid>):Array<Kid>
  {
      for(let k of kidsList)
      {
        let currentDate=new Date()
        let bDate=new Date(k.dob)
        
        let diffInSec= Math.abs(currentDate.getTime()-bDate.getTime())
        console.log(diffInSec);
        
        k.age=(diffInSec/(1000 * 3600 * 24)/365)+1
        let array:Array<string>=k.age.toString().split('.')
        k.age=Number.parseInt(array[0])
        console.log(k.age)
        
      }
      return kidsList
     
  }

  getUserType():string
  {
      //console.log(this.router.url);
      let array:Array<string>=new Array()
      array=this.router.url.split('/')
      //console.log(array[1]);
      return array[1]
      
  }
}
