import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Kid } from 'src/app/core/model/kid';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { KidsService } from 'src/app/kids/shared-services/kids.service';

@Component({
  selector: 'app-kids-list',
  templateUrl: './kids-list.component.html',
  styleUrls: ['./kids-list.component.css']
})
export class KidsListComponent implements OnInit {
  

  groups:Array<string>
  villages:Array<string>
  userType:string

  showSpinner:boolean=false
  noVillages:boolean=false
  kLength:number;

  page:number=0
  @Input() label:string 

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
    this.getkids(this.page)
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

  getkids(page:number)
  {
    this.showSpinner=true
    this.kidsService.getkidslist(page).subscribe(data =>{
      this.kidslist=data['content'];
      this.kLength=this.kidslist.length
      this.showSpinner=false
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
      
    });
  }

  getKidsByTaluka(talukaId:number)
  {
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

  getKidsByVillage(villageId:number)
  {
    this.kidsService.getAllKidsByVillage(0,villageId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
      console.log(this.kidslist)
      
    })
  }

  getKidsByArea(areaId:number)
  {
    this.kidsService.getAllKidsByArea(0,areaId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
    })
  }

  getKidsByVillageAndGroup(villageId:number, groupId:number)
  {
    this.kidsService.getAllKidsByVillageAndGroup(0,villageId, groupId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(this.kidslist);
    })
  }

  getKidsByAreaAndGroupAndVillage(areaId:number, groupId:number, villageId:number)
  {
    this.kidsService.getAllKidsByAreaAndGroupAndVillage(0,areaId,groupId,villageId).subscribe(data=>{
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      console.log(data);
    })
  }

  getKidsByGroup(groupId:number)
  {
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
