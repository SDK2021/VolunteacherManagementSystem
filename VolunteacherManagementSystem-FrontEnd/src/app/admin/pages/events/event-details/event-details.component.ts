import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Event } from 'src/app/core/model/event';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  constructor(private router:Router,private route:ActivatedRoute, private eventService:EventsService) { }

  event:Event=new Event()
  showSpinner:boolean=false
  totalParticipant:number

  showImageSpinner:boolean=true


  colors:string[]=['bg-lightpink','bg-lightblue','bg-lightyellow']
  ngOnInit(): void {
      let eventId:number=this.route.snapshot.params['id']
      console.log(eventId);
      this.getEvent(eventId)
      this.getParticipants(eventId)
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

  load()
  {
    this.showImageSpinner=false
  }
  
  getEvent(id:number)
  {
    this.showSpinner=true
    this.eventService.getEvent(id).subscribe(data=>{
      this.event=data
      this.showSpinner=false
      console.log(this.event);    
    }, error => {
      this.handleError(error)
    })
  }

  getParticipants(id:number)
  {
    this.eventService.getAllParticipantsByEvent(id).subscribe(data=>{
      console.log(data);
      this.totalParticipant = data
    }, error => {
      this.handleError(error)
    })
  }
}
