import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Event } from 'src/app/core/model/event';
import { AppHomeService } from 'src/app/user/services/app-home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  events:Array<Event> = []
  year:number

  page:number=0

  colors:string[]=['bg-lightpink','bg-lightyellow','bg-lightblue']
  constructor(private router:Router,private appHomeService:AppHomeService) { }

  ngOnInit(): void {
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
    this.year = new Date().getFullYear()
    this.appHomeService.getEvents(page).subscribe(data =>{
      this.events=data['content'];
     
    },error=>{
      this.handleError(error)
    });
  }

}
