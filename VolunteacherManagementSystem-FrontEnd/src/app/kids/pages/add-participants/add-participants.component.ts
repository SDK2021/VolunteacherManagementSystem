import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { KidsService } from '../../shared-services/kids.service';
import { Activity } from 'src/app/core/model/activity';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Kid } from 'src/app/core/model/kid';

@Component({
  selector: 'app-add-participants',
  templateUrl: './add-participants.component.html',
  styleUrls: ['./add-participants.component.css']
})

@Injectable({
  providedIn: 'root'
})
export class AddParticipantsComponent implements OnInit {
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showSpinner:Boolean=false
  totalKidsPages:number
  villages: Array<any>
  areas: Array<any>
  groups: Array<KidsGroup>

  selectedArea:number
  selectedVillage:number
  selectedGroup:number

  search:string=''

  filter:string;
  showProgressbar:boolean=false
  disabled:boolean=true

  eventId: number;
  kidslist: Array<Kid> = new Array()
  activities: Array<Activity>
  kidsParticipateIds: Array<number> = []

  page: number=0

  showImageSpinner:boolean=true

  constructor(private kidsService: KidsService, private _auth: authentication, private router: Router, private _snackBar: MatSnackBar,
    private eventService: EventsService, private route: ActivatedRoute) {
    this.villages = ["Timba", "Miroli"]
    this.areas = ["Thakorvas", "Nagri"]
    // this.groups=["Group1","Group2"]

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

  ngOnInit() {
    this.page = 0
    this.getkidsgroup()
    // this.getActivities()
    console.log(this.router.url)
    this.getkids(0,"all")
    this.eventId = this.route.snapshot.params['id']
    this.getEventById(this.eventId);
  }
  openSnackBar() {
    this._snackBar.open('Participants are added successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  addParticipatedKids() {
    this.showProgressbar=true
    this.kidsService.addParticipants(this.kidsParticipateIds, this.eventId).subscribe(data => {
      this.showProgressbar=false
      this.openSnackBar()
      this.router.navigate(['/user/kids'])
      console.log(data)
    }, error => {
      this.handleError(error)
    })

  }
  selectedKid(event) {
    if (event.target.checked) 
    {
      this.kidsParticipateIds.push(event.target.value)  
    }
    else 
    {
      let index = this.kidsParticipateIds.indexOf(event.target.value)
      this.kidsParticipateIds.splice(index, 1)
    }
    console.log(this.kidslist);
    
    if(this.kidsParticipateIds.length===0)
    {
      this.disabled=true
    }
    else{
      this.disabled=false
    }
  }
  getkidsgroup() {
    this.kidsService.getkidsgrouplist().subscribe(data => {
      this.groups = data['content'];
    }, error => {
      this.handleError(error)
    });

  }
  getEventById(eventId: number) {
    this.eventService.getEventById(eventId).subscribe(data => {
      console.log(data)
      this.activities = data.activities
    }, error => {
      this.handleError(error)
    })
  }
  getkids(page: number,filter:string) {
    this.page = 0
    this.filter = filter
   this.showSpinner=true
    this.kidsService.getkidslist(page).subscribe(data => {
      this.showSpinner=false
      this.kidslist = data['content'];
      this.totalKidsPages = data['totalPages']

    }, error => {
      this.handleError(error)
    });
  }

  getKidsByTaluka(talukaId:number,filter:string)
  {
    this.page = 0
    this.filter = filter
    this.kidsService.getkidslist(0).subscribe(data =>{
      for(var kid of this.kidslist)
      {
        if(kid.village.taluka.talukaId == talukaId)
        {
          this.kidslist.concat(kid)
         
          console.log(this.kidslist);
        }
      }
    });
  }

  getKidsByVillage(villageId:number,filter:string)
  {
    this.selectedVillage = villageId
    this.page = 0
    this.filter = filter
    this.kidsService.getAllKidsByVillage(this.page,villageId).subscribe(data=>{
      this.kidslist = data['content']
      this.totalKidsPages = data['totalPages']
      console.log(this.kidslist);
      console.log(this.kidslist)
      
    },error=>{
      this.handleError(error)
    })
    console.log(this.kidsParticipateIds.indexOf(1));
    
  }

  getKidsByArea(areaId:number,filter:string)
  {
    this.selectedArea = areaId
    this.page = 0
    this.filter = filter
    this.kidsService.getAllKidsByArea(this.page,areaId).subscribe(data=>{
      this.kidslist = data['content']
      this.totalKidsPages = data['totalPages']
      console.log(this.kidslist);
    },error=>{
      this.handleError(error)
    })
  }

  getKidsByVillageAndGroup(villageId:number, groupId:number,filter:string)
  { 
    this.selectedVillage = villageId
    this.selectedGroup = groupId
    this.page = 0
    this.filter = filter
    this.kidsService.getAllKidsByVillageAndGroup(this.page,villageId, groupId).subscribe(data=>{
      this.kidslist = data['content']
      this.totalKidsPages = data['totalPages']
      
      console.log(this.kidslist);
    },error=>{
      this.handleError(error)
    })
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

  getKidsByGroup(groupId:number,filter:string)
  {
    this.selectedGroup = groupId
    this.page= 0
    this.filter = filter
    this.kidsService.getAllKidsByGroup(this.page,groupId).subscribe(data=>{
      this.kidslist = data['content']
      this.totalKidsPages = data['totalPages']
    },error=>{
      this.handleError(error)
    })
  }


  trackGroupsById(index, g: KidsGroup) {
    return g.groupId
  }

  getActivities() {
    this.eventService.getActivities().subscribe(data => {
      this.activities = data
      console.log(this.activities);

    }, error => {
      this.handleError(error)
    })
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

  
  load()
  {
    this.showImageSpinner=false
  }
}
