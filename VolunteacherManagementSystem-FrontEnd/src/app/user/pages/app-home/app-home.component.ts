import { Component, OnInit } from '@angular/core';

import { Announcement } from 'src/app/core/model/announcement';
import { User } from 'src/app/core/model/user';
import { Event } from 'src/app/core/model/event'
import { AppHomeService } from '../../services/app-home.service';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { UsersService } from '../../services/users.service';
import { NotificationsService } from '../../services/notifications.service';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { Participant } from 'src/app/core/model/participant';
import { finalize } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { TimeLineService } from 'src/app/shared/shared-services/time-line.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { throwMatDialogContentAlreadyAttachedError } from '@angular/material/dialog';
@Component({
  selector: 'app-app-home',
  templateUrl: './app-home.component.html',
  styleUrls: ['./app-home.component.css']
})
export class AppHomeComponent implements OnInit {

  announcements: Array<Announcement>
  aLength: number = 0
  showNewVolunteacher: boolean = false
  users: Array<User>
  uLength: number = 0

  user:User=new User()

  page: number = 0
  participantUser: Participant;
  totalAnnouncementPages:number

  events: Array<Event>

  wish:string=''

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showProgressbar:boolean=false

  public get get_events() {
    return this.events
  }
  displayEvents: Array<Event> = new Array()
  count: number = 0
  eLength: number = 0
  classBlink: boolean

  posts: Timelinepost[] = new Array()

  showSpinner: boolean = false

  newUsers: Array<Volunteacher>
  nLength: number = 0


  slides: String[] = new Array()

  constructor(private _snackBar: MatSnackBar,private postService: TimeLineService, private router: Router, private _sharedservice: AppHomeService, private authService: authentication, private userService: UsersService, private notiService: NotificationsService, private eventService: EventsService) {

  }



  ngOnInit(): void {
    // this.slides['image']=new String()
    this.page=0
    this.getUser()
    this.showSpinner = true
    this.getAnnouncements()
    this.getUsersByDob()
    this.getEvents()
    this.getNewUsers()
    this.getPosts()
    console.log(this.get_events);
    this.showSpinner = false

    let today:Date = new Date()

    if(today.getHours()<12)
    {
        this.wish="Good Morning "
    }
    else if(today.getHours()>=12&&today.getHours()<18)
    {
      this.wish="Good Afternoon"
    }
    else if(today.getHours()>=18&&today.getHours()<21)
    {
      this.wish="Good Evening"
    }
    else
    {
      this.wish="Good Night"
    }
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
  openSnackBar() {
    this._snackBar.open('Registered successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  onScroll() {
    if(this.page < this.totalAnnouncementPages - 1)
    {
      this.page += 1
      this.getPageableAnnouncements(this.page);
    }
  }
  getPageableAnnouncements(page: number) {
    this._sharedservice.getAnnouncements(page).subscribe(data => {
      data['content'].forEach(announcement => {
        this.announcements.push(announcement)
      });
    })
  }

  getPosts() {
    this.postService.getTimelinePosts(0).subscribe(data => {
      this.posts = data['content'];
      console.log(data)
      for (let i = 0; i < 7; i++) {
        this.slides.push(this.posts[i].postPhoto)
      }
      console.log(this.slides);

    }, error => {
      this.handleError(error)
    });
  }
  getAnnouncements() {
    this._sharedservice.getAnnouncements(this.page).subscribe(data => {
      this.announcements = data['content']
      this.totalAnnouncementPages = data['totalPages']
      if (data != null) {
        this.aLength = this.announcements.length
      }
      console.log(this.announcements)
    }, error => {
      this.handleError(error)
    })
  }

  getUsersByDob() {
    this.showNewVolunteacher = true
    this._sharedservice.getUsersByDob().subscribe(
      data => {
        this.users = data
        console.log(this.users)
        if (data != null) {
          this.uLength = this.users.length

        }
        this.showNewVolunteacher = false
      }, error => {
        this.handleError(error)
      })

  }

  getEvents() {
    this._sharedservice.getEvents(0).subscribe(
      data => {
        this.events = data['content']
        console.log(this.events)
        this.displayEvent(this.events)
      }, error => {
        this.handleError(error)
      })
  }

  getNewUsers() {
    this._sharedservice.getNewUsers().subscribe(
      data => {
        this.newUsers = data
        //console.log(this.newUsers)
        if (data != null) {
          this.nLength = this.newUsers.length
        }
        // console.log(this.newUsers)
      }, error => {
        this.handleError(error)
      })
  }

  displayEvent(events: Array<Event>) {
    let currentDate: Date = new Date()

    for (let i = 0; i < events.length; i++) {
      let eDate: string = events[i]["eventDate"]

      let d: Date = new Date(eDate)

      if (currentDate.getDate() == d.getDate()) {
        this.classBlink = true
      }
      else {
        this.classBlink = false
      }

      if (!(currentDate.getDate() > d.getDate())) {
        this.displayEvents[this.count] = events[i]
        this.count++;
        console.log(this.displayEvents);

      }

    }

    this.eLength = this.displayEvents.length



  }

  addEventVolunteacher(value) {
    this.showProgressbar=true
    this.participantUser = new Participant();
    console.log(value)
    let authuser: string[];
    let email: string;

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).pipe(finalize(() => {
        this.eventService.getEventById(value).pipe(finalize(() => {
          this.notiService.addVTParticipant(this.participantUser, value).subscribe(data => {
            console.log(data)
            this.showProgressbar=false
            this.openSnackBar()
          }, error => {
            this.handleError(error)
          })
        })).subscribe(data => {
          this.participantUser.event = data
        })
      })).subscribe(
        (data) => {
          console.log(data)
          this.participantUser.name = data.userName
          this.participantUser.dob = data.dob
          this.participantUser.email = data.email
          this.participantUser.gender = data.gender
          this.participantUser.phoneNumber = data.phoneNumber
          this.participantUser.type = data.type
        })
    }
  }

  getUser() {
    let authUser: string[]
    if (this.authService.isUserLogin) {
      authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')
      this.userService.getUserByEmail(atob(authUser[0])).subscribe(data=>{
        this.user=data
      })
    }

  }
}
