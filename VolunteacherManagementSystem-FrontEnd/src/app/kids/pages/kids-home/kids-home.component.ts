import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Event } from 'src/app/core/model/event';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { AppHomeService } from 'src/app/user/services/app-home.service';

@Component({
  selector: 'app-kids-home',
  templateUrl: './kids-home.component.html',
  styleUrls: ['./kids-home.component.css']
})
export class KidsHomeComponent implements OnInit {

  events:Array<Event>=new Array()
  showSpinner:boolean=false
  noEvents:boolean=false
  eLength:number

  disabled:boolean=false

  colors=['bg-primary','yellow','bg-dribble']
  textColors=["white",'dark','white']

  page:number=0

  constructor(private appHomeService:AppHomeService,private _auth:authentication,private router:Router,private eventService:EventsService) {}

  ngOnInit() {
    this.page=0
    this.getEvents(this.page)
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

  getEvents(page:number)
  {
    this.showSpinner=true
    this.appHomeService.getEvents(page).subscribe(data=>{
        this.events=data['content']
        this.showSpinner=false
        if(data!=null)
        {
          this.noEvents=false
          this.eLength=this.events.length
        }
        //this.eLength=0
        if(this.eLength==0)
        {
          this.noEvents=true
        }
      

    },error=>{
      this.handleError(error)
    })
  }

 


}
