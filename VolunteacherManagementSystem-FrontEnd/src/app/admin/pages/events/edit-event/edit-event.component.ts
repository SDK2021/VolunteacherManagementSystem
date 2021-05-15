import { Component, OnInit } from '@angular/core';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { Activity } from 'src/app/core/model/activity';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { Event } from 'src/app/core/model/event';
import { Project } from 'src/app/core/model/project';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css']
})
export class EditEventComponent implements OnInit {

  event:Event=new Event()
  isEdit: boolean = false
  showForm:boolean=true
  imageURL:string
  showProgressbar: boolean = false

  oldImage:string=null

  baseUrl:string="/vms/events"
  Show:boolean = true;

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  villages:Array<Village>

  stateSelected:number;
  districtSelected:number;
  talukaSelected:number;
  villageSelected:number;
  projectSelected:number;
  activities:Array<Activity>
  selectedActivities:Array<number> = new Array()
  projects:Array<Project> = []

  isEventEdited:boolean=false


  hover:boolean=false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private projectService:ProjectsService,private addressService:AddressService,private fileService:FileUploadService,private _snackBar: MatSnackBar,private route:ActivatedRoute,private router:Router,private eventService:EventsService) { }

  ngOnInit(): void {

    this.imageURL = localStorage.getItem("imageURL")
   
    if(this.imageURL!=null)
    {
      this.fileService.delete(this.imageURL)
      console.log("deleted");
      localStorage.removeItem("imageURL")
      
    }
    this.event.village=new Village()
    this.event.project=new Project()
    let date:Date = new Date()
    console.log(date)
    this.getAllActivities()
    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.getAllVillages();
    this.getProjects();
    this.getEvent(this.route.snapshot.params['id'])
  }

  ngOnDestroy()
  {
    if(this.isEventEdited==false)
    {
      if(this.imageURL!=null)
      {
        this.fileService.delete(this.imageURL)
        localStorage.removeItem("imageURL")
      }
       
      console.log("Bye Bye");
      
    }
  }

  edit() {
      this.isEdit=true
      this.showForm=false
  }

  show(isShow):void
  {
    this.showForm=isShow
    this.imageURL = localStorage.getItem("imageURL")
    this.hover=false
  }

  mouseEvent()
  {
    this.hover=!this.hover
  }

  handleError(error)
  {
    alert("hello")
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

  getEvent(id: number) {
   
    this.eventService.getEventById(id).subscribe(data => {
      this.event = data
      this.imageURL=this.event.photo
      console.log(this.event);
    }, error => {
      this.handleError(error)
    })
  }

  openEditSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  
  getProjects()
  {
    this.projectService.getAllProjects().subscribe(data=>{
      this.projects = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedProject(event)
  {
    this.projectSelected = event.target.value;
    console.log(event.target.value);
  }
    
  getAllCountries()
  {
    this.addressService.getCountries().subscribe(data=>{
      this.countries = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedCountry(event)
  {
    this.addressService.getStates(event.target.value).subscribe(data=>{
      this.states = data
    })
  }

  getAllStates() 
  {
    this.addressService.getStates(8).subscribe(data=>{
      this.states = data;
    }, error => {
      this.handleError(error)
    })
  }

  selectedState(event)
  {
    this.stateSelected = event.target.value;
    this.addressService.getDistricts(event.target.value).subscribe(data=>{
    this.Show = false
    this.districts = data
    this.talukas = []
    this.villages = []
    })
  }

  getAllDistricts() 
  {
    this.addressService.getDistricts(7).subscribe(data=>{
    this.districts = data;
    }, error => {
      this.handleError(error)
    })
  }

  selectedDistrict(event)
  {
    this.districtSelected = event.target.value;
    this.addressService.getTalukas(event.target.value).subscribe(data=>{
    this.talukas = data
    this.villages = []
    })
  }

  getAllTalukas() 
  {
    this.addressService.getTalukas(141).subscribe(data=>{
    this.talukas = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedTaluka(event)
  {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if(event.target.value != 0)
    {
          this.addressService.getVillages(event.target.value).subscribe(data=>{
          this.villages = data
        })
    }
  }

  getAllVillages() 
  {
    this.addressService.getVillages(35).subscribe(data=>{
    this.villages = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedVillage(event)
  {
    this.villageSelected = event.target.value;
    console.log(event.target.value);
  }

  getAllActivities()
  {
    this.eventService.getActivities().subscribe(data=>{
      this.activities=data
      console.log(this.activities);
      
    }, error => {
      this.handleError(error)
    })
  }
  
  saveEvent()
  {
    if(this.imageURL!=null)
    {
        this.oldImage=this.event.photo
        this.event.photo=this.imageURL
    }
    if(this.villageSelected == null)
    {
      this.villageSelected = this.event.village.villageId
    }
    if(this.projectSelected == null)
    {
      this.projectSelected = this.event.project.projectId
    }
    this.showProgressbar=true
    if(this.selectedActivities.length < 1)
    {
      for(let activity of this.event.activities)
      {
        this.selectedActivities.push(activity.activityId)
      }
    }
    console.log(this.selectedActivities);
    
    this.event.photo =this.imageURL
    let eventdate:string = this.event.eventDate
    console.log(eventdate);
    
    let sdate:string[] = eventdate.split("-")
    let eventDate = sdate[0] + "-" +  sdate[1] + "-" + sdate[2]
    this.event.eventDate = eventDate
    this.event.notified = false
    console.log( this.event.eventDate);

    this.event.eventStartingTime = this.event.eventStartingTime + ":00"
    this.event.eventEndingTime = this.event.eventEndingTime + ":00"

    this.projectService.getProject(this.projectSelected).pipe(finalize(()=>{
      this.addressService.getVillageByid(this.villageSelected).pipe(finalize(()=>{
        this.eventService.editEvent(this.event.eventId,this.event,this.selectedActivities).subscribe(data=>{
          console.log(data)
          this.isEventEdited=true
          if(this.oldImage!=null)
          {
            this.fileService.delete(this.oldImage)
            localStorage.removeItem("imageURL")
          }
          
          this.showProgressbar=false
          this.openEditSnackBar()
          setTimeout(()=>{
            this.router.navigate(['/admin/events'])
          },2000)
        }, error => {
          this.handleError(error)
        })
      })).subscribe(data=>{
        this.event.village = data
      },error=>{
       console.log(error);
      })
    })).subscribe(data=>{
      this.event.project = data
    },error=>{
      console.log(error);
    })
  }



}
