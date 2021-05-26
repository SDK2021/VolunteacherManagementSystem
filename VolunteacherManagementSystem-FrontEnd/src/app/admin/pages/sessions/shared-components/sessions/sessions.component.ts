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
            console.log(data)
            this.user = data
            this.usertype = this.user.type.type;
            console.log(this.usertype);
            this.sessionService.getNotifications(this.page,this.currentMonth,this.currentYear,this.usertype).subscribe(data =>{
              for (const noti of data['content']) {
              if(noti.session != null)
              {
                if(Array(noti.session.users).length > 0)
                {
                  console.log("inside session")
                  noti.session.users.forEach(user => {
                    console.log(user.userId + ":" + this.user.userId)
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
                  console.log("inside session")
                  noti.session.users.forEach(user => {
                    console.log(user.userId + ":" + this.user.userId)
                    if(user.userId == this.user.userId)
                    {
                      this.sessions.push(noti) 
                    }
                  });
                }   
              }    
            }
            this.getFilteredSessions(this.sessions)
            console.log(this.displaySessions);
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
            console.log(this.sessions);
          });
          },
          (error) => console.log(error)
          ); 
          // this.getFilteredSessions(this.sessions)
          // console.log(this.displaySessions);
          
        }
  }


    //console.log(this.displaySessions)

  getFilteredSessions(sessions: Array<Notification>) {
   
    
    let currentDate: Date = new Date()
    console.log(currentDate)
    for (let i = 0; i < sessions.length; i++) {
      if(sessions[i]["session"]!=null)
      {
      sessions[i]["show"] = true;
      let sDate: string = sessions[i]["session"]["sessionDate"]
      let sTime: string = sessions[i]["session"]["startingTime"]
      let h: string = sTime.split(":")[0]
      let m: string = sTime.split(":")[1]
      //let s: string = sTime.split(":")[2]
      //console.log(h+":"+m+":"+s)

      let d = new Date(sDate)
      d.setHours(Number.parseInt(h))
      d.setMinutes(Number.parseInt(m))
//d.setSeconds(Number.parseInt(s))
      //console.log("After setting hrs:"+d)

      //console.log("and the date is:"+d)
      //console.log(sDate)
      if (currentDate.getTime() >= d.getTime()) {
        console.log("less:" + d)
        //this.sessions[i]["show"] = false;
        //console.log("second date is less")
        //this.sessions[i]["show"] = true;
        this.displaySessions.push(sessions[i])
        //console.log("time"+d)
        let sEndingTime: string = sessions[i]["session"]["endingTime"]
        console.log("Ending time" + sEndingTime)
        let h: string = sEndingTime.split(":")[0]
        let m: string = sEndingTime.split(":")[1]
        //let s: string = sEndingTime.split(":")[2]
        d.setHours(Number.parseInt(h))
        d.setMinutes(Number.parseInt(m))
       // d.setSeconds(Number.parseInt(s))
        if (currentDate.getTime() >= d.getTime()) {
          sessions[i]["show"] = false;
        }
        if (currentDate.getDate() != d.getDate()) {
          //console.log(currentDate.getDate()+"in if"+d.getDate())
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
