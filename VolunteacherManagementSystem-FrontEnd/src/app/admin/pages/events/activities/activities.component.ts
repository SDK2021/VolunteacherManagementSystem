import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/core/model/activity';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {
  isShow:boolean=false

  activities:Array<Activity>

  showProgressbar: boolean = false

  page:number=0

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  activity: Activity=new Activity()
  constructor(private router:Router,private dialog:MatDialog, private _snackBar: MatSnackBar, private eventService:EventsService) { 
  }

  ngOnInit(): void {
    this.getAllActivities(this.page)
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

  show()
  {
    this.isShow=!this.isShow
  }

  openDialog()
  {
    this.dialog.open(DialogBoxComponent)
    this.openDeletedSnackBar()
  }

  openDeletedSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  openAddedSnackBar() {
    this._snackBar.open('Added successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  onSubmit()
  {
    console.log(this.activity);
    this.show()
  }

  getAllActivities(page:number)
  {
    this.eventService.getAllActivities(page).subscribe(data=>{
      this.activities=data['content']
      console.log(this.activities);
      
    },error=>{
      this.handleError(error)
    })
  }

  addActivity(form:NgForm)
  {
    this.showProgressbar=true
    this.eventService.addActivity(this.activity).subscribe(data=>{
      console.log(data)
      this.openAddedSnackBar()
      form.reset()
      setTimeout(()=>{
        this.getAllActivities(this.page)
        this.showProgressbar=false
      },2000)
    },error=>{
      this.handleError(error)
    })
  }

  trackById(index :number,activity:Activity)
  {
      return activity.activityId
  }

  deleteActivity(id:number)
  {
    this.showProgressbar=true
     this.eventService.deleteActivity(id).subscribe(data=>{
       console.log(data);  
       this.openDeletedSnackBar()  
       setTimeout(() => {
        this.getAllActivities(this.page)
        this.showProgressbar=false
       }, 2000);
     },error=>{
      this.handleError(error)
    })
  }

  delete(id:number)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteActivity(id)
      }
    })
  }
}
