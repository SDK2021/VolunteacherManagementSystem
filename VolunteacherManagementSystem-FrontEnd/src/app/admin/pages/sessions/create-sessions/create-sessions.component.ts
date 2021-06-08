import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { Notification } from 'src/app/core/model/notification';
import { Project } from 'src/app/core/model/project';
import { Session } from 'src/app/core/model/session';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { NotificationsService } from 'src/app/user/services/notifications.service';
import { UsersService } from 'src/app/user/services/users.service';
@Component({
  selector: 'app-create-sessions',
  templateUrl: './create-sessions.component.html',
  styleUrls: ['./create-sessions.component.css']
})
export class CreateSessionsComponent implements OnInit {
  tab1: boolean
  tab2: boolean = true
  edit: boolean = false
  sessions: Array<Session> = []
  session: Session = new Session()
  projects: Array<Project> = []
  notification: Notification = new Notification();
  

  showProgressbar: boolean = false


  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];
  month: string
  year: number

  isShow: boolean = false;
  Show: boolean = true
  countries: Array<Country>
  states: Array<State>
  districts: Array<District>
  talukas: Array<Taluka>
  villages: Array<Village>

  stateSelected: number;
  districtSelected: number;
  talukaSelected: number;
  villageSelected: number;
  projectSelected: number;

  page:number=0
  totalPages:number

  showSpinner:boolean=false
  noSessions:boolean=false
  sLength:number


  constructor(private router: Router, private authService: authentication, private notiService: NotificationsService, private userService: UsersService, private projectService: ProjectsService, private addressService: AddressService, private _snackBar: MatSnackBar, private dialog: MatDialog, private sessionService: SessionsService) {

  }

  ngOnInit(): void {
    this.page=0
    let date: Date = new Date()
    console.log(date)
    this.month = this.monthNames[date.getMonth()]
    //console.log("current"+ this.month)
    this.year = date.getFullYear()
    this.sessions = []
    this.countries = []
    this.states = []
    this.villages = []
    this.talukas = []
    this.districts = []
    this.getSessionsByMonthAndYear(this.page)

    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.getAllVillages();
    this.getProjects();

    this.stateSelected = 7
    this.districtSelected = 141
    this.talukaSelected = 35
    this.villageSelected = 0;
    this.projectSelected = 0;
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

  show() {
    this.isShow = !this.isShow;
  }

  showTab1(show: boolean) {
    console.log("tab1" + show)
    this.tab1 = show
    this.tab2 = false
  }
  showTab2(show: boolean) {
    console.log("tab2" + show)
    this.tab2 = show
    this.tab1 = false
    this.edit = false
  }


  openDialog() {
    this.dialog.open(DialogBoxComponent)
    this.openDeleteSnackBar()
  }

  openAddSnackBar() {
    this._snackBar.open('Session added successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
 
  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  openNotifySnackBar() {
    this._snackBar.open('Notified successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  onSubmit(form: NgForm) {
    console.log(this.session);

  }

  getSessionsByMonthAndYear(page:number) {
    this.showSpinner=true
    let today: Date = new Date()
    this.sessionService.getSessionsByMonthAndYear(page,today.getMonth() + 1, today.getFullYear()).subscribe(data => {
      this.sessions = data['content']
      this.totalPages = data['totalPages']
      console.log(this.sessions);
      this.showSpinner=false
      if (data != null) {
        this.sLength = this.sessions.length
        this.noSessions=false
      }
      //this.sLength=0
      if(this.sLength==0)
      {
        this.noSessions=true
      }

    },error=>{
      this.handleError(error)
    })
  }

  isEdit() {
    this.edit = true
    this.tab1 = true
    this.tab2 = false   
  }

  

 
  addSession(form) {
    //console.log(form)
    if(this.villageSelected > 0 && this.projectSelected >0)
    {
      this.showProgressbar = true
      let sessiondate: string = this.session.sessionDate
      let sdate: string[] = sessiondate.split("-")
      let sessionDate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
      this.session.sessionDate = sessionDate

      this.session.startingTime = this.session.startingTime + ":00"
      this.session.endingTime = this.session.endingTime + ":00"
      this.session.notified = false

      this.projectService.getProject(this.projectSelected).pipe(finalize(() => {
        this.addressService.getVillageByid(this.villageSelected).pipe(finalize(() => {
          this.sessionService.addSession(this.session).subscribe(data => {
            console.log(data)
            this.showProgressbar = false
            this.openAddSnackBar()
            this.showTab2(true)
            setTimeout(()=>{
              this.getSessionsByMonthAndYear(0)
            },2000)
          },error=>{
            this.handleError(error)
          })
        })).subscribe(data => {
          this.session.village = data
        })
      })).subscribe(data => {
        this.session.project = data
      })

      this.isShow = !this.isShow;
    }
  }

  getProjects() {
    this.projectService.getAllProjects().subscribe(data => {
      this.projects = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedProject(event) {
    this.projectSelected = event.target.value;
    console.log(event.target.value);
  }

  getAllCountries() {
    this.addressService.getCountries().subscribe(data => {
      this.countries = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedCountry(event) {
    this.addressService.getStates(event.target.value).subscribe(data => {
      this.states = data
    })
  }

  getAllStates() {
    this.addressService.getStates(8).subscribe(data => {
      this.states = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedState(event) {
    this.stateSelected = event.target.value;
    this.Show = false
    this.villageSelected = 0
    this.talukaSelected = 0
    this.districtSelected = 0
    this.talukas = []
    this.villages = []
    if(event.target.value > 0)
    {
      this.addressService.getDistricts(event.target.value).subscribe(data=>{
      this.districts = data
      })
    }
  }

  getAllDistricts() {
    this.addressService.getDistricts(7).subscribe(data => {
      this.districts = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedDistrict(event) {
    this.villageSelected = 0
    this.talukaSelected = 0
    if(event.target.value > 0)
    {
      this.districtSelected = event.target.value;
      this.addressService.getTalukas(event.target.value).subscribe(data=>{
      this.talukas = data
      this.villages = []
      })
    }
    else{
      this.talukas = []
      this.villages = []
      this.districtSelected = 0
    }
  }

  getAllTalukas() {
    this.addressService.getTalukas(141).subscribe(data => {
      this.talukas = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedTaluka(event) {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if (event.target.value != 0) {
      this.addressService.getVillages(event.target.value).subscribe(data => {
        this.villages = data
      })
    }
  }

  getAllVillages() {
    this.addressService.getVillages(35).subscribe(data => {
      this.villages = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedVillage(event) {
    this.villageSelected = event.target.value;
    console.log(event.target.value);
  }


  notifySession(sessionId, value) {
    this.showProgressbar=true
    console.log(value.target.value)
    let authUser: string[] = []
    this.notification = new Notification()
    if (value.target.value == 1) {
      this.notification.userType = "ALL"
    }

    if (value.target.value == 2) {
      this.notification.userType = "LOCAL VOLUNTEACHER"
    }

    if (value.target.value == 3) {
      this.notification.userType = "VOLUNTEACHER"
    }

    this.notification.notificationType = "session"
    // this.notification.session.notified = true
    this.sessionService.sessionById(sessionId).pipe(finalize(() => {
      authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
      this.userService.getUserByEmail(atob(authUser[0])).pipe(finalize(() => {
        this.notiService.addNotification(this.notification).subscribe(data => {
          console.log(data)
          this.openNotifySnackBar()
          setTimeout(() => { this.getSessionsByMonthAndYear(0)
            this.showProgressbar=false }, 1000);
        },error=>{
          this.handleError(error)
        })
      })).subscribe(data => {
        this.notification.createdBy = data
      })
    })).subscribe(data => {
      this.notification.session = data  
    })
 
  }

  trackById(index: number, s: Session) {
    return s.sessionId
  }

  deleteSession(id:number)
  {
    this.showProgressbar=true
     this.sessionService.deleteSession(id).subscribe(data=>{
       console.log(data);  
       this.openDeleteSnackBar()  
       setTimeout(() => {
        this.getSessionsByMonthAndYear(this.page)
        this.showProgressbar=false
       }, 2000);
     },error=>{
      this.handleError(error)
    })
  }

  delete(id:number)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteSession(id)
      }
    })
  }

  onScroll() {
    if(this.page<this.totalPages)
    {
      this.page += 1
      this.getPageableEvent(this.page);
    }
  }
  getPageableEvent(page: number) {
    let today:Date=new Date()
    this.sessionService.getSessionsByMonthAndYear(page,today.getMonth() + 1, today.getFullYear()).subscribe(data => {
      data['content'].forEach(session => {
        this.sessions.push(session)
      });
    })
  }
}
