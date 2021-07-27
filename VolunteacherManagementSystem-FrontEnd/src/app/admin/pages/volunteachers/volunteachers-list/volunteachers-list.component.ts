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
import { VolunteacherPipe } from 'src/app/admin/filters/volunteacher.pipe';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { Project } from 'src/app/core/model/project';
import { State } from 'src/app/core/model/state';
import { Village } from 'src/app/core/model/village';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { AddressService } from 'src/app/shared/shared-services/address.service';

@Component({
  selector: 'app-volunteachers-list',
  templateUrl: './volunteachers-list.component.html',
  styleUrls: ['./volunteachers-list.component.css']
})
export class VolunteachersListComponent implements OnInit {


  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showProgressbar: boolean = false
  showSpinner: boolean = false

  page: number = 0
  vLength: number
  totalVTPages: number

  filter: number = null

  projectSelected: number = null
  villageSelected: number = null
  statusSelected: number = 0
  typeSelected: number = 2

  volunteachers: Array<Volunteacher> = new Array()

  disabled: boolean = null

  showImageSpinner: boolean = true

  projects: Project[] = new Array()
  villages: Village[] = new Array()

  showProjects: boolean = false
  showVillages: boolean = false
  showStatus: boolean = false
  showTypes: boolean = false

  constructor(private addressService: AddressService, private projectService: ProjectsService, private router: Router, private dialog: MatDialog, private _snackBar: MatSnackBar, private sharedService: VolunteachersService) {


  }

  ngOnInit(): void {
    this.page = 0
    this.getProjects()
    this.getAllVillages()
    this.getAllVoluntecahers(this.page)

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


  load() {
    this.showImageSpinner = false
  }

  blob: Blob = new Blob()
  download() {
    this.sharedService.downloadVolunteachers().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Volunteachers.xlsx";
      link.click();
    })
  }

  search: string = ''
  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getAllVoluntecahers(page: number) {

    this.showSpinner = true
    this.sharedService.getAllVolunteachers(page).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      this.volunteachers = data['content']
      this.totalVTPages = data['totalPages']

      console.log(this.volunteachers);
      this.vLength = this.volunteachers.length
      this.showSpinner = false


    }, error => {
      this.handleError(error)
    })
  }

  delete(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.deleteVolunteacher(id)
      }
    })

  }
  trackById(index, v: Volunteacher) {
    return v.volunteacherId
  }

  deleteVolunteacher(id: number) {
    this.disabled = true
    this.showProgressbar = true
    this.sharedService.deleteVolunteacher(id).subscribe(data => {
      console.log(data);

      setTimeout(() => {
        this.getAllVoluntecahers(this.page)
        this.openSnackBar()
        this.disabled = false
        this.showProgressbar = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })
  }



  onScroll() {
    if (this.page < this.totalVTPages - 1) {
      this.page += 1

      if (this.filter == 1)
        this.getPageableVtsByProject(this.page, this.projectSelected)
      else if (this.filter == 2)
        this.getPageableVtsByVillage(this.page, this.villageSelected)
      else if (this.filter == 3)
        this.getPageableVtsByStatus(this.page, this.statusSelected)
      else if (this.filter == 4)
        this.getPageableVtsByUserType(this.page, this.typeSelected)
      else
        this.getPageableVolunteachers(this.page);
    }
  }
  getPageableVolunteachers(page: number) {
    this.showSpinner=true
    this.sharedService.getAllVolunteachers(page).subscribe(data => {
      data['content'].forEach(vt => {
        this.volunteachers.push(vt)
        this.showSpinner=false
        console.log(this.volunteachers);
      });
    }, error => {
      this.handleError(error)
    })
  }

  getPageableVtsByProject(page: number, pId: number) {
    this.showSpinner=true
    this.sharedService.getVolunteachersByProject(page, pId).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      data['content'].forEach(vt => {
        this.volunteachers.push(vt)
        this.showSpinner=false
        console.log(this.volunteachers);
      });
    }, error => {
      this.handleError(error)
    })
  }

  getPageableVtsByVillage(page: number, vId: number) {
    this.showSpinner=true
    this.sharedService.getVolunteachersByVillage(page, vId).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      data['content'].forEach(vt => {
        this.volunteachers.push(vt)
        this.showSpinner=false
        console.log(this.volunteachers);

      });
    }, error => {
      this.handleError(error)
    })
  }

  getPageableVtsByStatus(page: number, status: number) {
    this.showSpinner=true
    this.sharedService.getVolunteachersByStatus(page, status).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      data['content'].forEach(vt => {
        this.volunteachers.push(vt)
        this.showSpinner=false
        console.log(this.volunteachers);

      });
    }, error => {
      this.handleError(error)
    })
  }

  getPageableVtsByUserType(page: number, type: number) {
    this.showSpinner=true
    this.sharedService.getVolunteachersByUserType(page, type).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      data['content'].forEach(vt => {
        this.volunteachers.push(vt)
        this.showSpinner=false
        console.log(this.volunteachers);
      });
    }, error => {
      this.handleError(error)
    })
  }

  //getting villages and Projects

  getProjects() {
    this.projectService.getAllProjects().pipe(finalize(()=>{
      this.projectSelected=this.projects[0].projectId
    })).subscribe(data => {
      this.projects = data
    }, error => {
      this.handleError(error)
    })
  }

  getAllVillages() {
    this.addressService.getVillages(35).pipe(finalize(()=>{
      this.villageSelected=this.villages[0].villageId
    })).subscribe(data => {
      this.villages = data
    }, error => {
      this.handleError(error)
    })
  }


  //getting vts

  getVtsByProject(page: number, pId: number) {
    this.showSpinner = true
    this.sharedService.getVolunteachersByProject(page, pId).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      this.volunteachers = data['content']
      this.totalVTPages = data['totalPages']

      console.log(this.volunteachers);
      this.vLength = this.volunteachers.length
      this.showSpinner = false


    }, error => {
      this.handleError(error)
    })
  }

  getVtsByVillage(page: number, vId: number) {
    this.showSpinner = true
    this.sharedService.getVolunteachersByVillage(page, vId).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      this.volunteachers = data['content']
      this.totalVTPages = data['totalPages']

      console.log(this.volunteachers);
      this.vLength = this.volunteachers.length
      this.showSpinner = false


    }, error => {
      this.handleError(error)
    })
  }

  getVtsByStatus(page: number, status: number) {
    this.showSpinner = true
    this.sharedService.getVolunteachersByStatus(page, status).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      this.volunteachers = data['content']
      this.totalVTPages = data['totalPages']

      console.log(this.volunteachers);
      this.vLength = this.volunteachers.length
      this.showSpinner = false


    }, error => {
      this.handleError(error)
    })
  }

  getVtsByUserType(page: number, type: number) {
    this.showSpinner = true
    this.sharedService.getVolunteachersByUserType(page, type).pipe(finalize(() => {
      for (let vt of this.volunteachers) {
        this.sharedService.getStatusVolunteachers(vt.volunteacherId).subscribe(data => {
          vt.status = data
        })
      }
    })).subscribe(data => {
      this.volunteachers = data['content']
      this.totalVTPages = data['totalPages']

      console.log(this.volunteachers);
      this.vLength = this.volunteachers.length
      this.showSpinner = false


    }, error => {
      this.handleError(error)
    })
  }
  //selecte on change


  selectedProject(event) {
    this.projectSelected = event.target.value
    this.getVtsByProject(0, event.target.value)
  }

  selectedVillage(event) {
    this.villageSelected = event.target.value
    this.getVtsByVillage(0, event.target.value)
  }

  selectedType(event) {
    this.typeSelected = event.target.value
    this.getVtsByUserType(0, event.target.value)
  }

  selectedStatus(event) {
    this.statusSelected = event.target.value
    this.getVtsByStatus(0, event.target.value)
  }

  selectedFilter(event) {
    if (event.target.value === '1') {
      this.filter = 1
      this.page = 0
      this.showVillages = false
      this.showStatus = false
      this.showTypes = false
      this.showProjects = true
      this.getVtsByProject(0, this.projects[0].projectId)
    }
    else if (event.target.value === '2') {
      this.page = 0
      this.filter = 2
      this.showStatus = false
      this.showTypes = false
      this.showProjects = false
      this.showVillages = true
      this.getVtsByVillage(0, this.villages[0].villageId)
    }
    else if (event.target.value === '3') {
      this.page = 0
      this.filter = 3
      this.showProjects = false
      this.showVillages = false
      this.showTypes = false
      this.showStatus = true
      let today = new Date()
      this.getVtsByStatus(0, 0)
    }
    else if (event.target.value === '4') {
      this.filter = 4
      this.showProjects = false
      this.showVillages = false
      this.showStatus = false
      this.showTypes = true
      this.getVtsByUserType(0, 2)
    }
    else {
      this.filter=5
      this.showProjects = false
      this.showVillages = false
      this.showStatus = false
      this.showTypes = false
      this.getAllVoluntecahers(0)
    }
  }
}


