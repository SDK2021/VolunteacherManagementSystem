import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Notification } from 'src/app/core/model/notification';
import { Participant } from 'src/app/core/model/participant';
import { Session } from 'src/app/core/model/session';
import { User } from 'src/app/core/model/user';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { NotificationsService } from '../../services/notifications.service';
import { UsersService } from '../../services/users.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { MatDialog } from '@angular/material/dialog';
import { Event } from 'src/app/core/model/event';
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  notifications: Array<Notification> = []
  user: User
  usertype: String;
  attendedUsers: User[] = [];
  events: Event[] = []
  sessions: Session[] = []
  participantUser: Participant;
  today: Date = new Date()
  page: number = 0
  totalPages: number
  showProgressbar: boolean = false
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  disabled: boolean = null

  constructor(private dialog: MatDialog, private _snackBar: MatSnackBar, private router: Router, private eventService: EventsService, private notiService: NotificationsService, private authService: authentication, private userService: UsersService, private notificationService: NotificationsService) { }

  ngOnInit(): void {
    if (!this.authService.isUserLogin) {
      this.router.navigate(['login'])
    }
    this.getAllNotifications(this.page)
    this.attendedUsers = []
    this.participantUser = new Participant()
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


  getAllNotifications(page) {

    let authuser: string[];
    let email: string;

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).subscribe(
        (data) => {

          this.user = data
          this.usertype = this.user.type.type;

          this.notificationService.getNotifications(page, this.usertype).pipe(finalize(() => {

            for (let notification of this.notifications) {
              if (notification.event != null) {
                this.events.push(notification.event)
              }
              if (notification.session != null) {

                this.sessions.push(notification.session)
              }
            }
            let authUser: string[]
            let userId: number
            authUser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ")
            this.userService.getUserByEmail(atob(authUser[0])).pipe(finalize(() => {

              for (let i = 0; i < this.events.length; i++) {
                for (let user of this.events[i].users) {
                  if (user.userId == userId) {
                    this.events[i].disable = true
                    break
                  }

                }
              }


              for (let i = 0; i < this.sessions.length; i++) {
                for (let user of this.sessions[i].users) {
                  if (user.userId == userId) {
                    this.sessions[i].disable = true
                    break
                  }
                  else if (this.isSessionDisable(this.sessions[i])) {
                    this.sessions[i].disable = true
                  }
                }


              }

            })).subscribe(data => {
              userId = data.userId
            })
          })).subscribe(data => {

            this.notifications = this.notifications.concat(data['content'])

            for (let i = 0; i < this.notifications.length; i++) {
              if (this.notifications[i].session != null) {
                if (this.isSessionDisable(this.notifications[i].session)) {
                  this.notifications[i].session.disable = true
                }
              }
              if (this.notifications[i].event != null) {
                if (this.isEventDisable(this.notifications[i].event)) {
                  this.notifications[i].event.disable = true
                }
              }
            }
            this.totalPages = data['totalPages']
          });
        },
        (error) => console.log(error)
      );
    }
  }

  isSessionDisable(session: Session): boolean {

    let currentDate: Date = new Date()
    let sDate: string = session.sessionDate
    let sTime: string = session.endingTime
    let h: string = sTime.split(":")[0]
    let m: string = sTime.split(":")[1]

    let d = new Date(sDate)
    d.setHours(Number.parseInt(h))
    d.setMinutes(Number.parseInt(m))




    if (currentDate > d) {
      return true
    }
    else {

      return false
    }



  }

  isEventDisable(event: Event): boolean {

    let currentDate: Date = new Date()
    let sDate: string = event.eventDate
    let sTime: string = event.eventEndingTime
    let h: string = sTime.split(":")[0]
    let m: string = sTime.split(":")[1]
    let d = new Date(sDate)
    d.setHours(Number.parseInt(h))
    d.setMinutes(Number.parseInt(m))

    if (currentDate >= d) {
      return true
    }
    else
      return false

  }


  openSnackBar() {
    this._snackBar.open('Registered successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  addSessionVolunteacher(value) {
    this.disabled = true
    this.showProgressbar = true

    let authuser: string[];
    let email: string;

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).pipe(finalize(() => {
        this.notiService.addVolunteacher(this.attendedUsers, value).subscribe(data => {

          this.showProgressbar = false

          this.attendedUsers = []
          setTimeout(() => {

            this.notifications = []

            for (let i = 0; i <= this.page; i++) {
              this.getAllNotifications(i)

            }
            this.openSnackBar()
            this.disabled = false

          }, 1000);
        }, error => {
          this.handleError(error)
        })
      })).subscribe(
        (data) => {

          this.attendedUsers.push(data)

        })
    }
  }

  addEventVolunteacher(value) {
    this.disabled = true
    this.showProgressbar = true

    let authuser: string[];
    let email: string;
    let users: User[] = []

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).pipe(finalize(() => {


        this.notiService.addVTParticipant(users, value).subscribe(data => {

          this.showProgressbar = false

          setTimeout(() => {

            this.notifications = []

            for (let i = 0; i <= this.page; i++) {
              this.getAllNotifications(i)
            }
            this.openSnackBar()
            this.disabled = false
          }, 1000);
        }, error => {
          this.handleError(error)
        })
      })).subscribe(
        (data) => {
          users.push(data)
        })
    }
  }

  openSessionDialog(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {

      if (data.delete) {
        this.addSessionVolunteacher(id)
      }
    })
  }
  openEventDialog(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {

      if (data.delete) {
        this.addEventVolunteacher(id)
      }
    })
  }

  onScroll() {


    if (this.page < this.totalPages - 1) {
      this.page += 1
      this.getPageablePosts(this.page);
    }
  }
  getPageablePosts(page: number) {
    this.getAllNotifications(page)
  }


}
