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

  notifications: Array<Notification>
  user: User
  usertype: String;
  attendedUsers: User[] = [];
  events: Event[] = []
  sessions:Session[] = []
  participantUser: Participant;
  today: Date = new Date()
  showProgressbar: boolean = false
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private dialog: MatDialog, private _snackBar: MatSnackBar, private router: Router, private eventService: EventsService, private notiService: NotificationsService, private authService: authentication, private userService: UsersService, private notificationService: NotificationsService) { }

  ngOnInit(): void {
    this.getAllNotifications()
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


  getAllNotifications() {

    let authuser: string[];
    let email: string;

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).subscribe(
        (data) => {
          console.log(data)
          this.user = data
          this.usertype = this.user.type.type;
          console.log(this.usertype);
          this.notificationService.getNotifications(0, this.usertype).subscribe(data => {

            this.notifications = data['content'];
            this.notificationService.getNotifications(0, "ALL").subscribe(data => {
              this.notifications = this.notifications.concat(data['content']);
              for (let notification of this.notifications) {
                if(notification.event !=null)
                {
                  this.events.push(notification.event)
                }
                if(notification.session !=null)
                {
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
                  }
                }
              })).subscribe(data => {
                userId = data.userId
              })



              console.log(data);
            }, error => {
              this.handleError(error)
            });
            console.log(data);
          });
        },
        (error) => console.log(error)
      );
    }
  }

  openSnackBar() {
    this._snackBar.open('Registered successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  addSessionVolunteacher(value) {
    this.showProgressbar = true
    console.log(value)
    let authuser: string[];
    let email: string;

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).pipe(finalize(() => {
        this.notiService.addVolunteacher(this.attendedUsers, value).subscribe(data => {
          console.log(data)
          this.showProgressbar = false
          this.openSnackBar()
          this.attendedUsers = []
          setTimeout(() => {
            console.log("Hello Krunal #TheProjectPartner");
            this.getAllNotifications()
          }, 1000);
        }, error => {
          this.handleError(error)
        })
      })).subscribe(
        (data) => {
          console.log(data)
          this.attendedUsers.push(data)

        })
    }
  }

  addEventVolunteacher(value) {
    this.showProgressbar = true
    console.log(value)
    let authuser: string[];
    let email: string;
    let users: User[] = []

    if (this.authService.isUserLogin) {
      localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      email = atob(authuser[0]);
      this.userService.getUserByEmail(email).pipe(finalize(() => {
        console.log(users);

        this.notiService.addVTParticipant(users, value).subscribe(data => {
          console.log(data)
          this.showProgressbar = false
          this.openSnackBar()
          setTimeout(() => {
            console.log("Hello Krunal #TheProjectPartner");
            this.getAllNotifications()
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
      console.log(data.delete)
      if (data.delete) {
        this.addSessionVolunteacher(id)
      }
    })
  }
  openEventDialog(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.addEventVolunteacher(id)
      }
    })
  }

}
