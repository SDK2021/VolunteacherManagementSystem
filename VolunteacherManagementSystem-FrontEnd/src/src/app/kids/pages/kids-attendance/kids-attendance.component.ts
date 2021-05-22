import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Kid } from 'src/app/core/model/kid';
import { authentication } from 'src/app/home/shared-services/authentication.service';

import { KidsService } from '../../shared-services/kids.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { Attendance } from 'src/app/core/model/attendance';
import { finalize } from 'rxjs/operators';
@Component({
  selector: 'app-kids-attendance',
  templateUrl: './kids-attendance.component.html',
  styleUrls: ['./kids-attendance.component.css']
})

@Injectable({
  providedIn: 'root'
})
export class KidsAttendanceComponent implements OnInit {

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  villages:Array<any>

  disabled:boolean=true

  showProgressbar:boolean=false
  showSpinner:Boolean=false

  page:number=0
  totalKidsPages:number
  filter:string
  selectedVillage:number
  selectedArea:number
  selectedGroup:number

  kidslist:Array<Kid>
  presentkids:Number[] = []
  groupSelected:number = 0
  attendance:Attendance;
  groups:Array<KidsGroup>
  constructor(private route:ActivatedRoute, private kidsService:KidsService,private _auth:authentication,private router:Router,private _snackBar: MatSnackBar) {
    this.villages=["Timba","Paldi"];
  }

  ngOnInit() {
    this.getkidsgroup()
  
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

  onScroll() {
    console.log("Hello");
    
    if(this.page < this.totalKidsPages - 1)
    {
      this.page += 1
      this.getPageableKids(this.page);
    }
  }
  getPageableKids(page: number) {
    if(this.filter === "vga")
    {
      this.kidsService.getAllKidsByAreaAndGroupAndVillage(page,this.selectedArea,this.selectedGroup,this.selectedVillage).subscribe(data=>{
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
  }

  getkids()
  {
    // this.page = 0
    // this.filter = filter
    // this.showSpinner=true
    // this.kidsService.getkidslist(page).subscribe(data =>{
    //   this.kidslist=data['content'];
    //   this.totalKidsPages = data['totalPages']
    //   this.showSpinner=false 
    // },error=>{
    //   this.handleError(error)
    // });
    // console.log("Hello")
    this.kidslist = []
  }
  
  addKid(event)
  {
    if(event.target.checked)
    {
      this.presentkids.push(event.target.value)
    }
    else
    {
      let index = this.presentkids.indexOf(event.target.value)
      this.presentkids.splice(index,1)
    }
    console.log(this.presentkids)
    if(this.presentkids.length==0)
    {
        this.disabled=true
    }
    else
    {
      this.disabled=false
    }
 
  }

  addSessionAttendance()
  {
    this.showProgressbar=true
    this.attendance = new Attendance();
    let sessionId = this.route.snapshot.params['id'];
    console.log(sessionId)
    this.kidsService.sessionById(sessionId).pipe(finalize(()=>{
      this.kidsService.kidGroupById(this.groupSelected).pipe(finalize(()=>{
        this.kidsService.addKidsAttendance(this.attendance,this.presentkids).subscribe(data=>{
          console.log(data)
          this.showProgressbar=false
          this.openSnackBar()
          this.router.navigate(['/user/sessions/sessions-list'])
          
        },error=>{
          this.handleError(error)
        })
      })).subscribe(data=>{
        this.attendance.group = data
      })
    })).subscribe(data=>{
      this.attendance.session = data
    })
  }

  getKidsByTaluka(talukaId:number)
  {
    // this.kidsService.getkidslist(0).subscribe(data =>{
    //   for(var kid of this.kidslist)
    //   {
    //     if(kid.area.village.taluka.talukaId == talukaId)
    //     {
    //       this.kidslist.concat(kid)
    //     }
    //   }
    // });
    this.kidslist = []
  }

  getKidsByVillage(villageId:number)
  {
    // this.kidsService.getAllKidsByVillage(0,villageId).subscribe(data=>{
    //   this.kidslist = data['content']
    // })
    this.kidslist = []
  }

  getKidsByArea(areaId:number)
  {
    // this.kidsService.getAllKidsByArea(0,areaId).subscribe(data=>{
    //   this.kidslist = data['content']
    // })
    this.kidslist = []
  }

  getKidsByGroup(groupId:number)
  {
    // this.kidsService.getAllKidsByGroup(groupId).subscribe(data=>{
    //   this.kidslist = data
    // })
    this.kidslist = []
  }

  getKidsByVillageAndGroup(villageId:number, groupId:number)
  {
    // this.kidsService.getAllKidsByVillageAndGroup(0,villageId, groupId).subscribe(data=>{
    //   this.kidslist = data['content']
    // })
    this.kidslist = []
  }

  getKidsByAreaAndGroupAndVillage(areaId:number, groupId:number, villageId:number,filter:string)
  {
    this.selectedVillage = villageId
    this.selectedGroup = groupId
    this.selectedArea = areaId
    this.page= 0
    this.filter = filter
    this.kidsService.getAllKidsByAreaAndGroupAndVillage(this.page,areaId,groupId,villageId).subscribe(data=>{
      this.kidslist = data['content']
      this.totalKidsPages = data['totalPages']
      console.log(data);
    },error=>{
      this.handleError(error)
    })
  }

  openSnackBar() {
    this._snackBar.open('Attendance is taken successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  getkidsgroup()
    {
      this.kidsService.getkidsgrouplist().subscribe(data =>{
        this.groups=data;
      },error=>{
        this.handleError(error)
      });
      console.log(this.groups)
    }
  
    trackGroupsById(index,g:KidsGroup)
    {
      return g.groupId
    }
  
}
