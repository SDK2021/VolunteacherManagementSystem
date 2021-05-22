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
  showProgressbar:boolean=false
  showForm:boolean=true
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
    this.showProgressbar=true
    let dob:string = this.eventParticipant.dob
    let pdob:string[] = dob.split("-")
    this.eventParticipant.dob = pdob[1] +"-" + pdob[2] + "-" + pdob[0]
    console.log(this.eventParticipant);
    let eventid:number  = this.route.snapshot.params['id'];
    this.eventService.getEventById(eventid).pipe(finalize(()=>{
          this.eventService.addParticipant(this.eventParticipant).subscribe(data=>{
            console.log(data)
            this.showProgressbar=false
            this.showForm=false
          },error=>{
            this.handleError(error)
          })

    })).subscribe(data=>{
    let userType = new Usertype()
    userType.typeId = 4
    userType.type = "OTHER"
    this.eventParticipant.type = userType
      this.eventParticipant.event = data
    },error=>{
      this.handleError(error)
    })

  }

}
