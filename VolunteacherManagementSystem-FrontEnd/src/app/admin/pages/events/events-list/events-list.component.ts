import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { EventsService } from 'src/app/admin/shared-services/events/events.service';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { Event } from 'src/app/core/model/event';
import { Project } from 'src/app/core/model/project';
import { Village } from 'src/app/core/model/village';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})
export class EventsListComponent implements OnInit {

  search: string = ''

  events: Array<Event>
  showSpinner: boolean = false
  noEvents: boolean = false
  eLength: number
  showProgressbar: boolean = false

  page: number = 0
  previousDisabled: boolean = true
  nextDisabled: boolean = false
  totalEventsPages: number

  currentYear: number = new Date().getFullYear()
  currentMonth: number = new Date().getMonth()

  projects: Project[] = new Array()
  villages: Village[] = new Array()
  months: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
  years: number[] = [2018]

  monthSelected: number = new Date().getMonth() + 1
  yearSelected = new Date().getFullYear()
  projectSelected: number = null
  villageSelected: number = null

  filter: number = null


  showProjects: boolean = false
  showVillages: boolean = false
  showTime: boolean = false

  disabled: boolean = null;

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private addressService: AddressService, private projectService: ProjectsService, private fileService: FileUploadService, private router: Router, private _snackBar: MatSnackBar, private dialog: MatDialog, private eventService: EventsService) { }

  ngOnInit(): void {
    this.page = 0
    this.getAllEvents(this.page)

    this.getProjects()
    this.getAllVillages()

    //add year

    let today: Date = new Date()
    let year = today.getFullYear()
    this.years.push(today.getFullYear())
    for (let i = 0; i < year - 2018; i++) {
      year -= 1
      this.years.push(year)
    }
    this.years = this.years.sort()
  }

  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
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

  openDialog() {
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

  getAllEvents(page: number) {
    this.showSpinner = true
    this.eventService.getAllEvents(page).subscribe(data => {
      this.events = data['content']
      this.totalEventsPages = data['totalPages']
      if (this.totalEventsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.eLength = this.events.length
        this.noEvents = false
      }
      //this.eLength=0
      if (this.eLength == 0) {
        this.noEvents = true
      }
    }, error => {
      this.handleError(error)
    })
  }

  trackById(index: number, event: Event) {
    return event.eventId
  }

  deleteEvent(id: number, image: string) {
    this.disabled = true
    this.showProgressbar = true
    this.eventService.deleteEvent(id).subscribe(data => {
      this.fileService.delete(image)

      setTimeout(() => {
        this.getAllEvents(this.page)
        this.showProgressbar = false
        this.openDeleteSnackBar()
        this.disabled = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })
  }

  delete(id: number, image: string) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      if (data.delete) {
        this.deleteEvent(id, image)
      }
    })
  }

  blob: Blob = new Blob()
  download() {
    this.eventService.downloadEvents().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Events.xlsx";
      link.click();
    })
  }

  nextPage() {

    if (this.page < this.totalEventsPages - 1) {
      this.page += 1
      if (this.filter == 1)
        this.getPageableEventsByProject(this.page, this.projectSelected)
      else if (this.filter == 2)
        this.getPageableEventsByVillage(this.page, this.villageSelected)
      else if (this.filter == 3)
        this.getPageableEventsByTime(this.page, this.monthSelected, this.yearSelected)
      else
        this.getPageableEvents(this.page);
      this.previousDisabled = false
    }
    if (this.page == this.totalEventsPages - 1) {
      this.nextDisabled = true
    }
  }

  previousPage() {
    if (this.page > -1) {
      this.page -= 1
      if (this.filter == 1)
        this.getPageableEventsByProject(this.page, this.projectSelected)
      else if (this.filter == 2)
        this.getPageableEventsByVillage(this.page, this.villageSelected)
      else if (this.filter == 3)
        this.getPageableEventsByTime(this.page, this.monthSelected, this.yearSelected)
      else
        this.getPageableEvents(this.page);
      this.nextDisabled = false
    }
    if (this.page == 0) {
      this.previousDisabled = true
    }
  }

  //getting pageable data

  getPageableEventsByProject(page: number, pId: number) {
    this.showSpinner = true
    this.eventService.getEventsByProject(page, pId).subscribe(data => {
      this.events = data['content']
      this.showSpinner = false
    })
  }

  getPageableEventsByVillage(page: number, vId: number) {
    this.showSpinner = true
    this.eventService.getEventsByVillage(page, vId).subscribe(data => {
      this.events = data['content']
      this.showSpinner = false
    })
  }

  getPageableEventsByTime(page: number, month: number, year: number) {
    this.showSpinner = true
    this.eventService.getEventsByTime(page, month, year).subscribe(data => {
      this.events = data['content']
      this.showSpinner = false
    })
  }

  getPageableEvents(page: number) {
    this.showSpinner = true
    this.eventService.getAllEvents(page).subscribe(data => {
      this.events = data['content']
      this.showSpinner = false
    })
  }



  //filters

  getProjects() {
    this.projectService.getAllProjects().pipe(finalize(() => {
      this.projectSelected = this.projects[0].projectId
    })).subscribe(data => {
      this.projects = data
    }, error => {
      this.handleError(error)
    })
  }

  getAllVillages() {
    this.addressService.getVillages(35).pipe(finalize(() => {
      this.villageSelected = this.villages[0].villageId
    })).subscribe(data => {
      this.villages = data
    }, error => {
      this.handleError(error)
    })
  }


  //get Filtered Sessions

  getEventsByProject(page: number, pId: number) {
    this.showSpinner = true
    this.eventService.getEventsByProject(page, pId).subscribe(data => {
      this.events = data['content']
      this.totalEventsPages = data['totalPages']
      if (this.totalEventsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.eLength = this.events.length
        this.noEvents = false
      }
      //this.eLength=0
      if (this.eLength == 0) {
        this.noEvents = true
      }
    }, error => {
      this.handleError(error)
    })
  }

  getEventsByVillage(page: number, vId: number) {
    this.showSpinner = true
    this.eventService.getEventsByVillage(page, vId).subscribe(data => {
      this.events = data['content']
      this.totalEventsPages = data['totalPages']
      if (this.totalEventsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.eLength = this.events.length
        this.noEvents = false
      }
      //this.eLength=0
      if (this.eLength == 0) {
        this.noEvents = true
      }
    }, error => {
      this.handleError(error)
    })
  }

  getEventsByTime(page: number, month: number, year: number) {
    this.showSpinner = true
    this.eventService.getEventsByTime(page, month, year).subscribe(data => {
      this.events = data['content']
      this.totalEventsPages = data['totalPages']
      if (this.totalEventsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.eLength = this.events.length
        this.noEvents = false
      }
      //this.eLength=0
      if (this.eLength == 0) {
        this.noEvents = true
      }
    }, error => {
      this.handleError(error)
    })
  }


  //Get Filtered Sessions on change events

  selectedProject(event) {
    this.projectSelected = event.target.value
    this.previousDisabled = true
    this.nextDisabled = false
    this.getEventsByProject(0, event.target.value)
  }

  selectedVillage(event) {
    this.previousDisabled = true
    this.nextDisabled = false
    this.villageSelected = event.target.value
    this.getEventsByVillage(0, event.target.value)
  }

  selectedYear(event) {
    this.previousDisabled = true
    this.nextDisabled = false
    this.yearSelected = event.target.value
    this.getEventsByTime(0, this.monthSelected, this.yearSelected)
  }

  selectedMonth(event) {
    this.previousDisabled = true
    this.nextDisabled = false
    this.monthSelected = event.target.value
    this.getEventsByTime(0, this.monthSelected, this.yearSelected)
  }

  //Selecting main filter

  selectedFilter(event) {
    if (event.target.value === '1') {
      this.previousDisabled = true
      this.nextDisabled = false
      this.page = 0
      this.showVillages = false
      this.showTime = false
      this.showProjects = true
      this.getEventsByProject(0, this.projects[0].projectId)
    }
    else if (event.target.value === '2') {
      this.previousDisabled = true
      this.nextDisabled = false
      this.page = 0
      this.showTime = false
      this.showProjects = false
      this.showVillages = true
      this.getEventsByVillage(0, this.villages[0].villageId)
    }
    else if (event.target.value === '3') {
      this.previousDisabled = true
      this.nextDisabled = false
      this.page = 0
      this.showProjects = false
      this.showVillages = false
      this.showTime = true
      let today = new Date()
      this.getEventsByTime(0, today.getMonth() + 1, today.getFullYear())
    }
    else {
      this.previousDisabled = true
      this.nextDisabled = false
      this.showProjects = false
      this.showVillages = false
      this.showTime = false
      this.getAllEvents(0)
    }
  }

}
