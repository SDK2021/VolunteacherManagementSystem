import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Kid } from 'src/app/core/model/kid';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { Session } from 'src/app/core/model/session';
import { User } from 'src/app/core/model/user';
import { KidsService } from 'src/app/kids/shared-services/kids.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {

  tab1:boolean
  tab2:boolean=true

  isShow:boolean=false;
  kids:Kid[]=new Array()
  allKids:Kid[]=new Array()

  groups:KidsGroup[]=new Array()
  groupId:number
  users:Array<User>=new Array()
  showVtSpinner:boolean=false
  showSpinner:boolean=false
  noUsers:boolean=false
  noKids:boolean=false
  uLength:number
  kLength:number
 

  session:Session=new Session()

  search:string=''
  constructor(private kidsService:KidsService,private router:Router,private sessionService:SessionsService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.getUsersBySession(this.route.snapshot.params['id'])
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




  show()
  {
    this.isShow=!this.isShow;
  }

  showTab1(show:boolean)
  {
    console.log("tab1"+show)
    this.tab1=show
    this.getSession()
  }
  showTab2(show:boolean)
  {
    console.log("tab2"+show)
    this.tab2=show
  }

  getUsersBySession(id:number)
  {
    this.showVtSpinner=true
      this.sessionService.sessionById(id).subscribe(data=>{
        this.users=data.users
        this.showVtSpinner=false
        if (data != null) {
          this.uLength = this.users.length
          this.noUsers=false
        }
        //this.vLength=0
        if(this.uLength==0)
        {
          this.noUsers=true
        }
      },error=>{
        this.handleError(error)
      })
  }

  selectedGroup(event)
  {
    this.groupId = event.target.value;
    console.log(this.groupId);
    this.noKids=false
    this.kLength=0
 //   this.getKidsAttendance(this.groupId,this.route.snapshot.params['id'])
    
    this.getAllKidsByGroup(0,this.session.village.villageId,this.groupId)
  }

  getSession()
  {
    this.sessionService.sessionById(this.route.snapshot.params['id']).subscribe(data=>{
      this.session=data
    })
  }
  getkidsgroup()
  {
    this.kidsService.getkidsgrouplist().subscribe(data =>{
      this.groups=data;
    },error=>{
      this.handleError(error)
     });
    
  }

  getKidsAttendance(gid:number,sid:number)
  {
    this.showSpinner=true
    this.sessionService.getKidsAttendance(gid,sid).subscribe(data=>{
      console.log(data);
      
        this.kids=data.kids
        this.showSpinner=false
        if (data != null) {
          this.kLength = this.kids.length
          this.noKids=false
        }
        //this.kLength=0
        if(this.kLength==0)
        {
          this.noKids=true
        }
      },error=>{
        this.handleError(error)
      })
  }

  getAllKidsByGroup(page:number,villageId:number,groupId:number)
  {
    this.sessionService.getKidsAttendance(this.groupId,this.route.snapshot.params['id']).pipe(finalize(()=>{
      this.kidsService.getAllKidsByVillageAndGroup(page,villageId,groupId).pipe(finalize(()=>{

      })).subscribe(data=>{
          this.allKids=data['content']
          console.log(this.allKids);
          for(let k of this.allKids)
          {
            for(let kid of this.kids)
              {
              if(kid.kidId == k.kidId)
              {
                k.attendance=true
                break
              }
              else
              {
                k.attendance=false
              }
            }
          }
        });
    })).subscribe(k=>{
      this.kids=k.kids     
    })
     
  }
}
