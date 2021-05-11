import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Event } from 'src/app/core/model/event';
import { Participant } from 'src/app/core/model/participant';
import { Usertype } from 'src/app/core/model/usertype';
import { UsersService } from 'src/app/user/services/users.service';
import { authentication } from '../../shared-services/authentication.service';

@Component({
  selector: 'app-event-participation',
  templateUrl: './event-participation.component.html',
  styleUrls: ['./event-participation.component.css']
})
export class EventParticipationComponent implements OnInit {
  eventParticipant:Participant=new Participant();
  event:Event;
  constructor(private router:Router,private route:ActivatedRoute, private eventService:EventsService, private userService:UsersService,private authService:authentication) { }

  ngOnInit(): void {
  
    this.getEvent(this.route.snapshot.params['id'])
  }
  getEvent(eventId:number) {
    this.event = new Event()
    this.eventService.getEventById(eventId).subscribe(data=>{
      this.event = data
    },error=>{
      this.handleError(error)
    })
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


  onSubmit()
  {
    console.log(this.eventParticipant);
    let eventid:number  = this.route.snapshot.params['id'];
    this.eventService.getEventById(eventid).pipe(finalize(()=>{
      console.log(this.authService.isUserLogin())
      if(this.authService.isUserLogin())
      {
        let username:string;
        let authuser:string[];
  
        authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
        username = atob(authuser[0]);
        this.userService.getUserByEmail(username).pipe(finalize(()=>{
          this.eventService.addParticipant(this.eventParticipant).subscribe(data=>{
            console.log(data)
          },error=>{
            this.handleError(error)
          })
        })).subscribe(data=>{
          this.eventParticipant.type = data.type
        })
      }
      else
      {
        let userType:Usertype = new Usertype()
        userType.type = "OTHER";
        userType.typeId = 4
        this.eventParticipant.type = userType
      }

      console.log(this.eventParticipant)
    })).subscribe(data=>{
      this.eventParticipant.event = data
    },error=>{
      this.handleError(error)
    })

  }

}
