import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { Event } from 'src/app/core/model/event';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})
export class EventsListComponent implements OnInit {

  search:string=''

  events: Array<Event>
  showSpinner:boolean=false
  noEvents:boolean=false
  eLength:number
  showProgressbar: boolean = false

  page:number=0
  previousDisabled:boolean = true
  nextDisabled:boolean = false
  totalEventsPages:number
  
  disabled:boolean=null;

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private fileService:FileUploadService,private router:Router,private _snackBar: MatSnackBar,private dialog:MatDialog, private eventService:EventsService) { }

  ngOnInit(): void {
    this.page=0
    this.getAllEvents(this.page)
  }

  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
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

  openDialog()
  {
    this.dialog.open(DialogBoxComponent)
    this.openSnackBar()
  }

  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  
  getAllEvents(page:number)
  {
    this.showSpinner=true
    this.eventService.getAllEvents(page).subscribe(data=>{
      this.events=data['content']
      this.totalEventsPages = data['totalPages']
      if(this.totalEventsPages == 1)
      {
        this.nextDisabled = true
      }
      console.log(this.events);
      this.showSpinner=false
      if (data != null) {
        this.eLength = this.events.length
        this.noEvents=false
      }
      //this.eLength=0
      if(this.eLength==0)
      {
        this.noEvents=true
      }
    }, error => {
      this.handleError(error)
    })
  }

  trackById(index:number,event:Event)
  {
    return event.eventId
  }

  deleteEvent(id:number,image:string)
  {
    this.disabled=true
    this.showProgressbar=true
     this.eventService.deleteEvent(id).subscribe(data=>{
       console.log(data); 
       this.fileService.delete(image)
       
       setTimeout(() => {
        this.getAllEvents(this.page)
        this.showProgressbar=false
        this.openDeleteSnackBar() 
        this.disabled=false
       }, 2000);
     }, error => {
      this.handleError(error)
    })
  }

  delete(id:number,image:string)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteEvent(id,image)
      }
    })
  }

  blob:Blob = new Blob()
  download()
  {
    this.eventService.downloadEvents().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Events.xlsx";
      link.click();
  })
  }

  nextPage()
  {
    console.log("Hello");
    console.log(this.totalEventsPages);
    
    if(this.page < this.totalEventsPages - 1)
    {
      this.page +=1
      this.getPageableEvents(this.page);
      this.previousDisabled = false
    }
    if(this.page == this.totalEventsPages - 1)
    {
      this.nextDisabled = true
    }
  }

  previousPage()
  {
    if(this.page > -1)
    {
      this.page -=1
      this.getPageableEvents(this.page);
      this.nextDisabled = false
    }
    if(this.page ==0){
      this.previousDisabled = true
    }
  }
  getPageableEvents(page: number) {
    this.showSpinner=true
    this.eventService.getAllEvents(page).subscribe(data=>{
      this.events=data['content']
      this.showSpinner=false
    })
  }

}
