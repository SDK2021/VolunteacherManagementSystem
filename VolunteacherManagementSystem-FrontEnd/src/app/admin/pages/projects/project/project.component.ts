import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { KidService } from 'src/app/admin/shared-services/kid.service';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { UploadImgComponent } from 'src/app/core/components/upload-img/upload-img.component';
import { FileUpload } from 'src/app/core/model/file-upload';

import { Kid } from 'src/app/core/model/kid';
import { Project } from 'src/app/core/model/project';

import { Volunteacher } from 'src/app/core/model/volunteacher';
import { FileUploadService } from 'src/app/core/services/file-upload.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  tab1: boolean
  tab2: boolean = true

  isShow: boolean = false;

  disabled: boolean = null

  showSpinner: boolean = false
  noProjects: boolean = false
  pLength: number

  kPage: number = 0

  isProjectCreated: boolean = false

  showProgressbar: boolean = false
  edit: boolean = false
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  projects: Array<Project> = new Array()

  page: number = 0
  vPage: number = 0
  volunteachers: Array<Volunteacher> = []
  kids: Kid[] = []
  selectedVolunteacher: Array<Number> = []
  selectedKids: Array<Number> = []
  project: Project = new Project()

  baseUrl: string = "/vms/projects"
  imageURL: string = null;

  message: boolean = false
  showForm: boolean = false

  showImageSpinner: boolean = true

  croppedImage: any = ''
  percentage: number = 0
  @ViewChild(UploadImgComponent) uploadImageComponent: UploadImgComponent


  constructor(private fileService: FileUploadService, private dialog: MatDialog, private router: Router, private kidsService: KidService, private vtService: VolunteachersService, private _snackBar: MatSnackBar, private projectService: ProjectsService) {


  }

  ngOnInit(): void {

    this.kPage = 0
    this.vPage = 0
    this.getAllProjects()
    this.getAllVolunteacher(this.vPage);
    this.getAllKids(this.kPage);
    this.selectedVolunteacher = []
    this.selectedKids = []

  }

  invalidEndingDate: boolean = false
  validateDate(startingdate, endingDate) {
    let array: string[] = startingdate.split("-")
    let date = array[1] + "-" + array[2] + "-" + array[0]
    let projectStartingDate = new Date(date)

    let array2: string[] = endingDate.split("-")
    let date2 = array2[1] + "-" + array2[2] + "-" + array2[0]
    let projectEndingDate = new Date(date2)

    if (projectEndingDate < projectStartingDate)
      this.invalidEndingDate = true
    else
      this.invalidEndingDate = false

  }


  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else if (error.status === 400) {
      this.message = true
      this.showProgressbar = false
    }
    else {
      this.router.navigate(['error-page'])
    }
  }

  showTab1(show: boolean) {
    console.log("tab1" + show)
    this.tab1 = show
  }
  showTab2(show: boolean) {
    console.log("tab2" + show)
    this.tab2 = show
    this.tab1 = false
    this.edit = false
   
  }

  vtTab: boolean = true
  kidTab: boolean = false

  tab1Class: boolean = true
  tab2Class: boolean = false

  showVtTab() {
    this.vtTab = true
    this.tab2Class = false
    this.tab1Class = true
    this.kidTab = false
  }
  showKidTab() {
    this.tab1Class = false
    this.tab2Class = true
    this.vtTab = false
    this.kidTab = true
  }

  load() {
    this.showImageSpinner = false
  }

  openAddSnackBar() {
    this._snackBar.open('Added successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }


  onSubmit(form: NgForm) {
    console.log(this.project);
  }

  show(isShow): void {
    this.showImageSpinner = true
    this.showForm = isShow
  }

  getAllProjects() {
    this.showSpinner = true
    this.projectService.getAllProjects().subscribe(data => {
      this.projects = data
      this.showSpinner = false
      console.log(data);

      if (data != null) {
        this.pLength = this.projects.length
        this.noProjects = false
      }
      //this.pLength=0
      if (this.pLength == 0) {
        this.noProjects = true
      }
      console.log(this.projects);
      console.log(this.pLength);

    }, error => {
      this.handleError(error)
    })
  }

  isEdit() {
    this.tab1 = true
    this.tab2 = false
    this.edit = true
  }

  getAllKids(page: number) {
    this.kidsService.getAllKids(page).subscribe(data => {
      this.kids = data['content']
      console.log(this.kids);

    }, error => {
      this.handleError(error)
    })
  }

  getAllVolunteacher(page: number) {
    this.vtService.getAllVolunteachers(page).subscribe(data => {
      this.volunteachers = data['content']
      console.log(this.volunteachers)
    }, error => {
      this.handleError(error)
    })
  }

  addProject(form: NgForm) {
    this.disabled = true
    this.showProgressbar = true
    if (this.message == true) {
      this.showProgressbar = true
      let today = new Date()
      let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
      this.project.creationTime = time
      this.project.photo = this.imageURL
      // alert(this.project.photo)
      let startdate: string = this.project.startingDate
      let sdate: string[] = startdate.split("-")
      let startingdate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
      this.project.startingDate = startingdate

      let enddate: string = this.project.endingDate
      if (enddate != null) {
        let edate: string[] = enddate.split("-")
        let endingdate = edate[1] + "-" + edate[2] + "-" + edate[0]
        this.project.endingDate = endingdate
      }
      else {
        this.project.endingDate = null
      }
      console.log(this.selectedKids)
      this.projectService.addProject(this.project, this.selectedVolunteacher, this.selectedKids).subscribe(data => {
        console.log(data)

        form.resetForm()
        // this.isProjectCreated=true
        this.showImageSpinner = true
        setTimeout(() => {
          this.showProgressbar = false
          this.imageURL = null
          this.openAddSnackBar()
          this.disabled = false
          this.getAllProjects()
          this.showImageSpinner=true
          this.showTab2(true)
        }, 1500);

        //this.getAllProjects()
      }, error => {
        this.disabled = false
        this.handleError(error)
      })
    }
    else {
      const file = this.uploadImageComponent.image;
      this.fileService.pushFileToStorage(new FileUpload(file), this.baseUrl).subscribe(
        percentage => {
          this.percentage = Math.round(percentage);

          if (this.percentage == 100) {


            this.fileService.imageUrl.subscribe(data => {
              this.imageURL = data

              if (this.imageURL != null && this.isProjectCreated == false) {
                this.showProgressbar = true
                let today = new Date()
                let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
                this.project.creationTime = time
                this.project.photo = this.imageURL
                // alert(this.project.photo)
                let startdate: string = this.project.startingDate
                let sdate: string[] = startdate.split("-")
                let startingdate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
                this.project.startingDate = startingdate

                let enddate: string = this.project.endingDate
                if (enddate != null) {
                  let edate: string[] = enddate.split("-")
                  let endingdate = edate[1] + "-" + edate[2] + "-" + edate[0]
                  this.project.endingDate = endingdate
                }
                else {
                  this.project.endingDate = null
                }
                console.log(this.selectedKids)
                this.projectService.addProject(this.project, this.selectedVolunteacher, this.selectedKids).subscribe(data => {
                  console.log(data)

                  form.resetForm()
                  // this.isProjectCreated=true
                  this.showImageSpinner = true
                  setTimeout(() => {
                    this.showProgressbar = false
                    this.imageURL = null
                    this.openAddSnackBar()
                    this.disabled = false
                    this.getAllProjects()
                    this.showTab2(true)
                    this.showImageSpinner=true
                  }, 1500);

                  //this.getAllProjects()
                }, error => {
                  this.disabled = false
                  this.handleError(error)
                })
                this.isProjectCreated = true
              }
            })
          }
        }, error => {
          this.disabled = false
          this.handleError(error)
        }
      ), error => {
        this.disabled = false
        this.handleError(error)
      }
    }

  }

  selectVolunteacher(event) {
    if (event.target.checked) {
      this.selectedVolunteacher.push(event.target.value)
    }
    else {
      let index: number = this.selectedVolunteacher.indexOf(event.target.value)
      this.selectedVolunteacher.splice(index, 1)
    }
    console.log(this.selectedVolunteacher)
  }

  selectKids(event) {
    if (event.target.checked) {
      this.selectedKids.push(event.target.value)
    }
    else {
      let index: number = this.selectedKids.indexOf(event.target.value)
      this.selectedKids.splice(index, 1)
    }
    console.log(this.selectedKids)
  }

  deleteProject(id: number, image: string) {
    this.showProgressbar = true

    this.projectService.deleteProject(id).subscribe(data => {
      console.log(data);
      this.fileService.delete(image)
      this.openDeleteSnackBar()
      setTimeout(() => {
        this.getAllProjects()
        this.showProgressbar = false
      }, 2000);
    }, error => {
      this.showProgressbar = false
      this.handleError(error)
    })
  }

  delete(id: number, image: string) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.deleteProject(id, image)
      }
    })
  }

  getCroppedImage(image) {
    this.croppedImage = image
  }



}
