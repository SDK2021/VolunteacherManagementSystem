import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Notification } from 'src/app/core/model/notification';
import { User } from 'src/app/core/model/user';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';



@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.css']
})
export class SessionsComponent implements OnInit {


  sessions: Array<Notification> = new Array()
  displaySessions: Array<Notification> = new Array()
  sLength:number=0
//  notifications: Array<Notification>
  user: User
  usertype: String;
  showSpinner:boolean=false
  today:Date
  currentMonth:number;
  currentYear:number
  page:number

  noSession:boolean=false

  constructor(private router:Router,private sessionService: SessionsService, private userService: UsersService, private authService: authentication) {

  }

  ngOnInit(): void {
    this.getAllNotifications()
    this.today = new Date()
    this.currentMonth = this.today.getMonth() + 1;
    this.currentYear = this.today.getFullYear()
    this.page = 0

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

  onScroll()
  {
    this.page +=1
    this.getPageableEvent(this.page);
  }
  getPageableEvent(page: number) {
    this.sessionService.getNotifications(this.page,this.currentMonth,this.currentYear,this.usertype).subscribe(data =>{
      data['content'].forEach(noti => {
        this.sessions.push(noti)
      });
    })
  }

  getAllNotifications()
  {
    this.showSpinner=true
    let authuser:string[];
    let email:string;
    
        if(this.authService.isUserLogin)
        {
          localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
          authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
          email = atob(authuser[0]);
          this.userService.getUserByEmail(email).subscribe(
            (data)=>{
            this.user = data
            this.usertype = this.user.type.type;
            this.sessionService.getNotifications(this.page,this.currentMonth,this.currentYear,this.usertype).subscribe(data =>{
              for (const noti of data['content']) {
              if(noti.session != null)
              {
                if(Array(noti.session.users).length > 0)
                {
                  noti.session.users.forEach(user => {
                    if(user.userId == this.user.userId)
                    {
                      this.sessions.push(noti);
                    }
                  });
                }  
              }  
            }
            
            this.sessionService.getNotifications(this.page,this.currentMonth,this.currentYear,"ALL").subscribe(data =>{
            for (const noti of data['content']) {
              if(noti.session != null)
              {
                if(Array(noti.session.users).length > 0)
                {
                  noti.session.users.forEach(user => {
                    if(user.userId == this.user.userId)
                    {
                      this.sessions.push(noti) 
                    }
                  });
                }   
              }    
            }
            this.getFilteredSessions(this.sessions)
            this.showSpinner=false
            if(this.displaySessions.length==0)
            {
              this.noSession=true
            }
            else{

            }
            },error=>{
              this.handleError(error)
            });
          });
          },
          (error) => console.log(error)
          ); 
          
        }
  }



  getFilteredSessions(sessions: Array<Notification>) {
   
    
    let currentDate: Date = new Date()
    for (let i = 0; i < sessions.length; i++) {
      if(sessions[i]["session"]!=null)
      {
      sessions[i]["show"] = true;
      let sDate: string = sessions[i]["session"]["sessionDate"]
      let sTime: string = sessions[i]["session"]["startingTime"]
      let h: string = sTime.split(":")[0]
      let m: string = sTime.split(":")[1]

      let d = new Date(sDate)
      d.setHours(Number.parseInt(h))
      d.setMinutes(Number.parseInt(m))

      if (currentDate.getTime() >= d.getTime()) {
        this.displaySessions.push(sessions[i])
        let sEndingTime: string = sessions[i]["session"]["endingTime"]
        let h: string = sEndingTime.split(":")[0]
        let m: string = sEndingTime.split(":")[1]
        d.setHours(Number.parseInt(h))
        d.setMinutes(Number.parseInt(m))
        if (currentDate.getTime() >= d.getTime()) {
          sessions[i]["show"] = false;
        }
        if (currentDate.getDate() != d.getDate()) {
          sessions[i]["disable"] = true;
        }
        else {
          sessions[i]["disable"] = false;
        }
      }
    }
    }
    this.sLength=this.displaySessions.length
  }
}
