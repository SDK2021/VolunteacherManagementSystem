import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { Applicantrequest } from 'src/app/core/model/applicantrequest';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
@Component({
  selector: 'app-applicant-request',
  templateUrl: './applicant-request.component.html',
  styleUrls: ['./applicant-request.component.css']
})
export class ApplicantRequestComponent implements OnInit {

  applicantRequests: Array<Applicantrequest>
  count: number = 0
  totalPages: number
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showProgressbar: boolean = false

  showSpinner: boolean = false
  noRequests: boolean = false
  rLength: number

  showButtons: boolean = true
  page: number = 0

  disable: boolean = null

  constructor(private router: Router, private volunteacherService: VolunteachersService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.page = 0
    this.getAllApplicantrequests(this.page);

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
  openAcceptSnackBar() {
    this._snackBar.open('Accepted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  openDeniedSnackBar() {
    this._snackBar.open('Denied successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  onScroll() {
    if (this.page < this.totalPages) {
      this.page += 1
      this.getPageableEvent(this.page);
    }
  }
  getPageableEvent(page: number) {
    this.volunteacherService.getApplicantrequests(page).subscribe(data => {
      data['content'].forEach(request => {
        this.applicantRequests.push(request)
      });
    })
  }

  accept(value) {
    this.disable = true
    this.showProgressbar = true
    this.volunteacherService.acceptRequest(value).subscribe(data => {
      setTimeout(() => {
        this.openAcceptSnackBar()
        this.getAllApplicantrequests(0)
        this.showProgressbar = false
        this.disable = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })

  }

  denied(value) {
    this.disable = true
    this.showProgressbar = true
    this.volunteacherService.deniedRequest(value).subscribe(data => {
      setTimeout(() => {
        this.openDeniedSnackBar()
        this.showProgressbar = false
        this.disable = false
        this.getAllApplicantrequests(0)
      }, 2000);
    }, error => {
      this.handleError(error)
    })

  }

  getAllApplicantrequests(page: number) {
    this.showSpinner = true
    this.volunteacherService.getApplicantrequests(page).subscribe(data => {
      this.applicantRequests = data['content'];
      this.totalPages = data['totalPages']
      this.showSpinner = false
      if (data != null) {
        this.rLength = this.applicantRequests.length
        this.noRequests = false
      }
      //this.rLength=0
      if (this.rLength == 0) {
        this.noRequests = true
      }
    }, error => {
      this.handleError(error)
    });

    this.rLength = 0

  }

  getAcceptedRequests() {
    this.showSpinner = true
    this.volunteacherService.getAllAcceptedrequests().subscribe(data => {
      this.applicantRequests = data;
      this.showSpinner = false
      if (data != null) {
        this.rLength = this.applicantRequests.length
        this.noRequests = false
      }
      //this.rLength=0
      if (this.rLength == 0) {
        this.noRequests = true
      }
    }, error => {
      this.handleError(error)
    });
    this.rLength = 0
  }

  getRejectedRequests() {
    this.showSpinner = true
    this.volunteacherService.getAllRejectedrequests().subscribe(data => {
      this.applicantRequests = data
      this.showSpinner = false
      if (data != null) {
        this.rLength = this.applicantRequests.length
        this.noRequests = false
      }
      //this.rLength=0
      if (this.rLength == 0) {
        this.noRequests = true
      }
    }, error => {
      this.handleError(error)
    });
    this.rLength = 0
  }

  myFun(index, a: Applicantrequest) {
    this.count = 1;
  }

  selectedCategory(event) {

    if (event.target.value === '1') {
      this.showButtons = true
      this.getAllApplicantrequests(0)
    }
    else if (event.target.value === '2') {
      this.showButtons = false
      this.getAcceptedRequests()
    }
    else {
      this.showButtons = false
      this.getRejectedRequests()
    }


  }



}
