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
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
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

  villages: Array<any>

  disabled: boolean = true

  showProgressbar: boolean = false
  showSpinner: Boolean = false

  page: number = 0
  totalKidsPages: number
  filter: string
  selectedVillage: number
  selectedArea: number
  selectedGroup: number

  disabledMessage:boolean=null

  kidslist: Array<Kid>=new Array()
  presentkids: Number[] = []
  groupSelected: number = 0
  attendance: Attendance;
  groups: Array<KidsGroup>

  defaultVillage:number=1
  constructor(private sessionService:SessionsService,private route: ActivatedRoute, private kidsService: KidsService, private _auth: authentication, private router: Router, private _snackBar: MatSnackBar) {
    this.villages = ["Timba", "Paldi"];
  }

  ngOnInit() {
    this.getkidsgroup()
    this.sessionService.sessionById(this.route.snapshot.params['id']).subscribe(data=>{
      this.defaultVillage=data.village.villageId
    })
   
  }

  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else {
      this.router.navigate(['error-page'])
    }
  }

  onScroll() {
 
    if (this.page < this.totalKidsPages - 1) {
      this.page += 1
      this.getPageableKids(this.page);
    }
  }

  getPageableKids(page: number) {
    if (this.filter === "vga") {
      this.kidsService.getAllKidsByAreaAndGroupAndVillage(page, this.selectedArea, this.selectedGroup, this.selectedVillage).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
  }

  getkids() {
    this.kidslist = []
  }

  addKid(event) {
    if (event.target.checked) {
      this.presentkids.push(event.target.value)
    }
    else {
      let index = this.presentkids.indexOf(event.target.value)
      this.presentkids.splice(index, 1)
    }
 
    if (this.presentkids.length == 0) {
      this.disabled = true
    }
    else {
      this.disabled = false
    }

  }

  addSessionAttendance() {
    this.disabled=true
    this.disabledMessage=true
    this.showProgressbar = true
    this.attendance = new Attendance();
    let sessionId = this.route.snapshot.params['id'];
  
    this.kidsService.sessionById(sessionId).pipe(finalize(() => {
      this.kidsService.kidGroupById(this.groupSelected).pipe(finalize(() => {
        this.kidsService.addKidsAttendance(this.attendance, this.presentkids).subscribe(data => {
     
          this.showProgressbar = false
          this.openSnackBar()
          this.disabled=false
          this.disabledMessage=false
          this.router.navigate(['/user/sessions/sessions-list'])

        }, error => {
          this.handleError(error)
        })
      })).subscribe(data => {
        this.attendance.kidsGroup = data
      })
    })).subscribe(data => {
      this.attendance.session = data
    })
  }

  getKidsByTaluka(talukaId: number) {
   
    this.kidslist = []
  }

  getKidsByVillage(villageId: number) {
   
    this.kidslist = []
  }

  getKidsByArea(areaId: number) {
    
    this.kidslist = []
  }

  getKidsByGroup(groupId: number) {
    
    this.kidslist = []
  }

  getKidsByVillageAndGroup(villageId: number, groupId: number) {
   
    this.kidslist = []
  }

  getKidsByAreaAndGroupAndVillage(areaId: number, groupId: number, villageId: number, filter: string) {
    this.selectedVillage = villageId
    this.selectedGroup = groupId
    this.selectedArea = areaId
    this.page = 0
    this.filter = filter
    this.kidsService.getAllKidsByAreaAndGroupAndVillage(this.page, areaId, groupId, villageId).subscribe(data => {
      this.kidslist = data['content']
      this.kidslist=this.calculateAge(this.kidslist)
      this.totalKidsPages = data['totalPages']
     
    }, error => {
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
  getkidsgroup() {
    this.kidsService.getkidsgrouplist().subscribe(data => {
      this.groups = data;
    }, error => {
      this.handleError(error)
    });

  }

  trackGroupsById(index, g: KidsGroup) {
    return g.groupId
  }

  calculateAge(kidsList:Array<Kid>):Array<Kid>
  {
      for(let k of kidsList)
      {
        let currentDate=new Date()
        let bDate=new Date(k.dob)
        
        let diffInSec= Math.abs(currentDate.getTime()-bDate.getTime())
       
        k.age=(diffInSec/(1000 * 3600 * 24)/365)+1
        let array:Array<string>=k.age.toString().split('.')
        k.age=Number.parseInt(array[0])
       
      }
      return kidsList
     
  }
}
