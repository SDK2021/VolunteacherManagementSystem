import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';
import { Announcement } from 'src/app/core/model/announcement';
import { User } from 'src/app/core/model/user';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { AppHomeService } from 'src/app/user/services/app-home.service';
import { UsersService } from 'src/app/user/services/users.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
// core components
import {
  chartOptions,
  parseOptions,
  chartExample1,
  chartExample2
} from "src/app/variables/charts";
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { DashboardService } from 'src/app/admin/shared-services/dashboard.service';

import { Notification } from 'src/app/core/model/notification';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { Sessionreport } from 'src/app/core/model/sessionreport';
import { finalize } from 'rxjs/operators';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public datasets: any;
  public data: any;
  public salesChart;
  public clicked: boolean = true;
  public clicked1: boolean = false;
  width = document.body.offsetWidth

  notifications:Array<Notification>=new Array()
  nLength:number

  newUsers:Array<Volunteacher>=new Array()
  uLength:number

  requirements:Sessionreport[]=new Array()
  rLength:number

  totalVolunteachers: number;
  totalKids: number;
  totalSessions: number;
  totalEvents: number;

  totalNotificationPages:number
  totalAnnouncementPages:number
  totalUserPages:number
  showImageSpinner:boolean=true




  noAnnouncement:boolean=false
  noUsers:boolean=false
  noRequirements:boolean=false
  noNotifications:boolean=false

  showSpinner:boolean=false
  showProgressbar: boolean = false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  page:number=0
  user: User = new User()

  announcements: Announcement[] = new Array()
  aLength: number
  announcement:Announcement = new Announcement()


  colors: string[] = ["border-purple", "border-yellow", "border-pink", "border-green"]

  array: number[] = [1, 2, 3, 4]
  constructor(private dashboardService: DashboardService, private router: Router, private dialog: MatDialog, private _snackBar: MatSnackBar, private userService: UsersService, private authService: authentication, private homeService: AppHomeService) {

  }

  ngOnInit() {
    this.page=0
    this.newUsers['user']=new User()
    this.announcements['createdBy']=new User()

    this.getAnnouncements(this.page)
    this.getTotalVolunteachers()
    this.getTotalSessions()
    this.getTotalKids()
    this.getTotalEvents()
    this.getUser()
    this.getAdminNotifications(this.page)
    this.getNewUsers(this.page)
    this.getRequirements()
    console.log(this.width)
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

  public updateOptions() {
    this.salesChart.data.datasets[0].data = this.data;
    this.salesChart.update();
  }

  onScroll() {
    if(this.page  < this.totalAnnouncementPages - 1)
    {
      console.log("Hello");
      
      this.page += 1
      this.getPageableAnnouncements(this.page);
    }
  }

  load()
  {
    this.showImageSpinner=false
  }
  getPageableAnnouncements(page: number) {
    this.homeService.getAnnouncements(page).subscribe(data => {
      data['content'].forEach(announcement => {
        this.announcements.push(announcement) 
      });
    },error=>{
      this.handleError(error)
    })
  }

  getAnnouncements(page:number) {
    this.showSpinner=true
    this.homeService.getAnnouncements(page).subscribe(data => {
      this.announcements = data['content']
      this.totalAnnouncementPages = data['totalPages']  
      console.log(this.totalAnnouncementPages);
      
      this.showSpinner=false
      if (data != null) {
        this.aLength = this.announcements.length
        this.noAnnouncement=false
      }
      if(this.aLength==0)
      {
        this.noAnnouncement=true
      }
      console.log(this.announcements)
      
    },error=>{
      this.handleError(error)
    })
  }

  getUser() {
    let authuser: string[]
    authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
    this.userService.getUserByEmail(atob(authuser[0])).subscribe(data => {
      this.user = data
      console.log(this.user);
    },error=>{
      this.handleError(error)
    })
  }

  deleteAnnouncement(id: number) {
    this.showProgressbar = true
    this.dashboardService.deleteAnnouncement(id).subscribe(data => {
      console.log(data);
      this.openDeleteSnackBar()
      setTimeout(() => {
        this.getAnnouncements(this.page)
        this.showProgressbar = false
      }, 2000);
    },error=>{
      this.handleError(error)
    })
  }

  delete(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.deleteAnnouncement(id)
      }
    })
  }

  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  openSnackBar() {
    this._snackBar.open('Announcement created successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getTotalVolunteachers() {
    this.showSpinner=true;
    this.dashboardService.getTotalvolunteachers().subscribe(data=>{
      this.totalVolunteachers=data
      console.log(data);
      this.showSpinner=false;
      console.log(this.totalVolunteachers);
    },error=>{
      this.handleError(error)
    })
  }

  getTotalKids() {
      this.dashboardService.getTotalKids().subscribe(data=>{
        this.totalKids=data
        console.log(this.totalKids);
      },error=>{
        this.handleError(error)
      })
  }

  getTotalSessions() {
    this.showSpinner=true;
    this.dashboardService.getTotalSesssions().subscribe(data=>{
      this.totalSessions=data
      console.log(data);
      this.showSpinner=false;
      console.log(this.totalSessions);
    },error=>{
      this.handleError(error)
    })

  }

  getTotalEvents() {
    this.showSpinner=true;
    this.dashboardService.getTotalEvents().subscribe(data=>{
      this.totalEvents=data
      console.log(data);
      this.showSpinner=false;
      console.log(this.totalEvents);
    },error=>{
      this.handleError(error)
    })
  }

  notificationPage:number = 0
  onNotificationScroll() {
    if(this.notificationPage < this.totalNotificationPages - 1)
    {
      this.notificationPage += 1
      this.getPageableNotifications();
    }
  }

  getPageableNotifications() {
    this.dashboardService.getAdminNotifications(this.notificationPage).subscribe(data => {
      data['content'].forEach(n => {
        this.notifications.push(n)
      });
    },error=>{
      this.handleError(error)
    })
  }

  getAdminNotifications(page:number) {
    this.showSpinner=true
    this.dashboardService.getAdminNotifications(page).subscribe(data => {
      this.notifications = data['content']
      this.totalNotificationPages = data['totalPages']
      if (data != null) {
        this.nLength = this.notifications.length
        this.noNotifications=false
      }
      if(this.nLength==0)
      {
        this.noNotifications=true
      }
      console.log(this.notifications)
      this.showSpinner=false
    },error=>{
      this.handleError(error)
    })
  }

  userPage:number = 0
  onUserScroll() {
    if(this.userPage < this.totalUserPages - 1)
    {
      this.userPage += 1
      this.getPageableUsers();
    }
  }

  getPageableUsers() {
    this.dashboardService.getNewUsers(this.userPage).subscribe(data => {
      data['content'].forEach(n => {
        this.newUsers.push(n)
        console.log(n+" push .... s");
        
      });
    },error=>{
      this.handleError(error)
    })
  }
  addAnnouncement()
  {
    this.showProgressbar=true
    console.log(this.announcement);
    
    if(this.authService.isUserLogin())
    {
      let authUser: string[] = []
      authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
      this.userService.getUserByEmail(atob(authUser[0])).pipe(finalize(() => {
        let today = new Date()
        this.announcement.creationTime = today.getHours() + "-" + today.getMinutes() + "-" + today.getSeconds()
        this.dashboardService.addAnnouncement(this.announcement).subscribe(data=>{
          this.showProgressbar=false
          this.openSnackBar()
          this.announcement.data=""
          this.getAnnouncements(0)
          console.log(data); 
        })
      })).subscribe(data=>{
        this.announcement.createdBy = data
      })
    }
  }
  getNewUsers(page:number) {
    this.showSpinner=true
    this.dashboardService.getNewUsers(page).subscribe(data => {
      this.newUsers = data['content']
      this.totalUserPages = data['totalPages']
      if (data != null) {
        this.uLength = this.newUsers.length
        this.noUsers=false
      }
      //this.uLength=0
      if(this.uLength==0)
      {
        this.noUsers=true
      }
      console.log(this.newUsers)
      this.showSpinner=false
    },error=>{
      this.handleError(error)
    })
  }

  getRequirements() {
    this.showSpinner=true
    this.dashboardService.getRequirements().subscribe(data => {
      this.requirements = data
      console.log(data);
      if (data != null) {
        this.rLength = this.requirements.length
        this.noRequirements=false
      }
      //this.rLength=0
      if(this.rLength==0)
      {
        this.noRequirements=true
      }
      console.log(this.requirements)
      this.showSpinner=false
    },error=>{
      this.handleError(error)
    })
  }



  
}
