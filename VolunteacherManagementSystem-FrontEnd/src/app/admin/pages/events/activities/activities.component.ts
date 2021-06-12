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
  isShow: boolean = false

  activities: Array<Activity>

  showProgressbar: boolean = false

  page: number = 0

  disabled: boolean = null

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  isEdit: boolean = false
  activity: Activity = new Activity()
  constructor(private router: Router, private dialog: MatDialog, private _snackBar: MatSnackBar, private eventService: EventsService) {
  }

  ngOnInit(): void {
    this.getAllActivities()
  }

  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else {
      this.router.navigate(['error-page'])
    }
  }

  show() {
    this.isShow = !this.isShow
  }

  openDialog() {
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

  openEditSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  onSubmit() {
    console.log(this.activity);
    this.show()
  }

  getAllActivities() {
    this.eventService.getActivities().subscribe(data => {
      this.activities = data
      console.log(this.activities);

    }, error => {
      this.handleError(error)
    })
  }

  addActivity(form: NgForm) {
    this.disabled = true
    this.showProgressbar = true
    this.eventService.addActivity(this.activity).subscribe(data => {
      console.log(data)

      this.show()
      form.reset()
      setTimeout(() => {
        this.getAllActivities()
        this.showProgressbar = false
        this.openAddedSnackBar()
        this.disabled = false
      }, 2000)
    }, error => {
      this.handleError(error)
    })
  }

  trackById(index: number, activity: Activity) {
    return activity.activityId
  }

  deleteActivity(id: number) {
    this.disabled = true
    this.showProgressbar = true
    this.eventService.deleteActivity(id).subscribe(data => {
      console.log(data);

      setTimeout(() => {
        this.getAllActivities()
        this.showProgressbar = false
        this.openDeletedSnackBar()
        this.disabled = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })
  }

  delete(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.deleteActivity(id)
      }
    })
  }

  edit(id: number) {
    this.isEdit = true
    this.eventService.getActivityById(id).subscribe(data => {
      this.activity = data
      console.log(data)
    })
  }

  saveActivity() {
    this.disabled = true
    this.eventService.updateActivity(this.activity.activityId, this.activity).subscribe(data => {
      console.log(data);
      this.disabled = false
      this.isEdit = false
    }, error => {
      this.handleError(error)
    })
  }
}
