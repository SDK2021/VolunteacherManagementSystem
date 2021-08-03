
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Session } from 'src/app/core/model/session';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { Project } from 'src/app/core/model/project';
import { Village } from 'src/app/core/model/village';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { finalize } from 'rxjs/operators';
@Component({
  selector: 'app-all-sessions',
  templateUrl: './all-sessions.component.html',
  styleUrls: ['./all-sessions.component.css']
})
export class AllSessionsComponent implements OnInit {


  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  search: string = ''
  sessions: Array<Session> = new Array()

  showSpinner: boolean = false
  noSessions: boolean = false
  sLength: number

  page: number = 0
  totalSessionsPages: number
  previousDisabled: boolean = true
  nextDisabled: boolean = false
  showProgressbar: boolean = false

  disabled: boolean = null

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

  constructor(private addressService: AddressService, private projectService: ProjectsService, private router: Router, private sessionService: SessionsService, private _snackBar: MatSnackBar, private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.page = 0
    this.getAllSessions(this.page)
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

  blob: Blob = new Blob()
  download() {
    this.sessionService.downloadSessions().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Sessions.xlsx";
      link.click();
    })
  }

  getAllSessions(page: number) {
    this.showSpinner = true
    this.sessionService.getAllSessions(page).subscribe(data => {
      this.sessions = data['content']
      this.totalSessionsPages = data['totalPages']
      if (this.totalSessionsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.sLength = this.sessions.length
        this.noSessions = false
      }
      //this.sLength=0
      if (this.sLength == 0) {
        this.noSessions = true
      }

    }, error => {
      this.handleError(error)
    })
  }

  deleteSession(id: number) {
    this.disabled = true
    this.showProgressbar = true
    this.sessionService.deleteSession(id).subscribe(data => {
      setTimeout(() => {
        this.getAllSessions(this.page)
        this.showProgressbar = false
        this.openDeleteSnackBar()
        this.disabled = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })
  }

  delete(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      if (data.delete) {
        this.deleteSession(id)
      }
    })
  }


  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  nextPage() {

    if (this.page < this.totalSessionsPages - 1) {
      this.page += 1
      if (this.filter == 1)
        this.getPageableSessionsByProject(this.page, this.projectSelected)
      else if (this.filter == 2)
        this.getPageableSessionsByVillage(this.page, this.villageSelected)
      else if (this.filter == 3)
        this.getPageableSessionsByTime(this.page, this.monthSelected, this.yearSelected)
      else
        this.getPageableSessions(this.page);

      this.previousDisabled = false
    }
    if (this.page == this.totalSessionsPages - 1) {
      this.nextDisabled = true
    }
  }

  previousPage() {
    if (this.page > -1) {
      this.page -= 1
      if (this.filter == 1)
        this.getPageableSessionsByProject(this.page, this.projectSelected)
      else if (this.filter == 2)
        this.getPageableSessionsByVillage(this.page, this.villageSelected)
      else if (this.filter == 3)
        this.getPageableSessionsByTime(this.page, this.monthSelected, this.yearSelected)
      else
        this.getPageableSessions(this.page);

      this.nextDisabled = false
    }
    if (this.page == 0) {
      this.previousDisabled = true
    }
  }

  getPageableSessions(page: number) {
    this.showSpinner = true
    this.sessionService.getAllSessions(page).subscribe(data => {
      this.sessions = data['content']
      this.showSpinner = false
    })
  }

  getPageableSessionsByProject(page: number, pId: number) {
    this.showSpinner = true
    this.sessionService.getSessionsByProject(page, pId).subscribe(data => {
      this.sessions = data['content']
      this.showSpinner = false
    })
  }

  getPageableSessionsByVillage(page: number, vId: number) {
    this.showSpinner = true
    this.sessionService.getSessionsByVillage(page, vId).subscribe(data => {
      this.sessions = data['content']
      this.showSpinner = false
    })
  }

  getPageableSessionsByTime(page: number, month: number, year: number) {
    this.showSpinner = true
    this.sessionService.getSessionsByMonthAndYear(page, month, year).subscribe(data => {
      this.sessions = data['content']
      this.showSpinner = false
    })
  }

  trackById(index, s: Session) {
    return s.sessionId
  }

  //Getting projects and villages for filters
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

  getSessionsByProject(page: number, pId: number) {
    this.showSpinner = true
    this.sessionService.getSessionsByProject(page, pId).subscribe(data => {
      this.sessions = data['content']
      this.totalSessionsPages = data['totalPages']
      if (this.totalSessionsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.sLength = this.sessions.length
        this.noSessions = false
      }
      //this.sLength=0
      if (this.sLength == 0) {
        this.noSessions = true
      }

    }, error => {
      this.handleError(error)
    })
  }

  getSessionsByVillage(page: number, vId: number) {
    this.showSpinner = true
    this.sessionService.getSessionsByVillage(page, vId).subscribe(data => {
      this.sessions = data['content']
      this.totalSessionsPages = data['totalPages']
      if (this.totalSessionsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.sLength = this.sessions.length
        this.noSessions = false
      }
      //this.sLength=0
      if (this.sLength == 0) {
        this.noSessions = true
      }

    }, error => {
      this.handleError(error)
    })
  }

  getSessionsByTime(page: number, month: number, year: number) {
    this.showSpinner = true
    this.sessionService.getSessionsByMonthAndYear(page, month, year).subscribe(data => {
      this.sessions = data['content']
      this.totalSessionsPages = data['totalPages']
      if (this.totalSessionsPages == 1) {
        this.nextDisabled = true
      }
      this.showSpinner = false
      if (data != null) {
        this.sLength = this.sessions.length
        this.noSessions = false
      }
      //this.sLength=0
      if (this.sLength == 0) {
        this.noSessions = true
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
    this.getSessionsByProject(0, event.target.value)
  }

  selectedVillage(event) {
    this.previousDisabled = true
    this.nextDisabled = false
    this.villageSelected = event.target.value
    this.getSessionsByVillage(0, event.target.value)
  }

  selectedYear(event) {
    this.previousDisabled = true
    this.nextDisabled = false
    this.yearSelected = event.target.value
    this.getSessionsByTime(0, this.monthSelected, this.yearSelected)
  }

  selectedMonth(event) {
    this.previousDisabled = true
    this.nextDisabled = false
    this.monthSelected = event.target.value
    this.getSessionsByTime(0, this.monthSelected, this.yearSelected)
  }

  //Selecting main filter

  selectedFilter(event) {
    if (event.target.value === '1') {
      this.previousDisabled = true
      this.nextDisabled = false
      this.filter = 1
      this.page = 0
      this.showVillages = false
      this.showTime = false
      this.showProjects = true
      this.getSessionsByProject(0, this.projects[0].projectId)
    }
    else if (event.target.value === '2') {
      this.previousDisabled = true
      this.nextDisabled = false
      this.filter = 2
      this.page = 0
      this.showTime = false
      this.showProjects = false
      this.showVillages = true
      this.getSessionsByVillage(0, this.villages[0].villageId)
    }
    else if (event.target.value === '3') {
      this.previousDisabled = true
      this.nextDisabled = false
      this.filter = 3
      this.page = 0
      this.showProjects = false
      this.showVillages = false
      this.showTime = true
      let today = new Date()
      this.getSessionsByTime(0, today.getMonth() + 1, today.getFullYear())
    }
    else {
      this.previousDisabled = true
      this.nextDisabled = false
      this.filter = 4
      this.page = 0
      this.showProjects = false
      this.showVillages = false
      this.showTime = false
      this.getAllSessions(0)
    }
  }



}
