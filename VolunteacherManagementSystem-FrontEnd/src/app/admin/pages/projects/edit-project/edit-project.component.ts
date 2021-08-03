import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { Project } from 'src/app/core/model/project';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { Kid } from 'src/app/core/model/kid';
import { User } from 'src/app/core/model/user';
import { NgForm } from '@angular/forms';
import { UploadImgComponent } from 'src/app/core/components/upload-img/upload-img.component';
import { FileUpload } from 'src/app/core/model/file-upload';
@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {

  project: Project = new Project()
  isEdit: boolean = false
  showForm: boolean = true
  imageURL: string = null
  showProgressbar: boolean = false
  users: User[] = new Array()
  kids: Kid[] = new Array()
  selectedVolunteacher: Array<Number> = []
  selectedKids: Array<Number> = []
  projectStartingDate: string
  projectEndingDate: string

  baseUrl: string = "/vms/projects"
  oldImage: string = null
  isProjectEdited: boolean = false

  croppedImage: any = null
  percentage: number = 0
  @ViewChild(UploadImgComponent) uploadImageComponent: UploadImgComponent

  hover: boolean = false

  disabled:boolean=null

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private volunteacherService: VolunteachersService, private fileService: FileUploadService, private _snackBar: MatSnackBar, private route: ActivatedRoute, private router: Router, private projectService: ProjectsService) { }

  ngOnInit(): void {
    this.getProject(this.route.snapshot.params['id'])
    this.getVolunteachers()
    this.getKids()
  }

  invalidEndingDate: boolean = false
  validateDate(startingdate, endingDate) {
    let projectStartingDate = new Date(startingdate)

    let array2: string[] = endingDate.split("-")
    let date2 = array2[1] + "-" + array2[2] + "-" + array2[0]
    let projectEndingDate = new Date(date2)

    if (projectEndingDate < projectStartingDate)
      this.invalidEndingDate = true
    else
      this.invalidEndingDate = false

  }

  edit() {
    if (this.isEdit == false) {
      this.isEdit = true
      this.showForm = false
    }
    else {
      this.showForm = true
    }

  }

  mouseEvent() {
    if (this.isEdit) {
      this.hover = false
    }
    else {
      this.hover = !this.hover
    }
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

  getVolunteachers() {
    this.volunteacherService.getRemainingVolunteachers(this.route.snapshot.params['id']).subscribe(data => {
   
      this.users = data
    })
  }

  getKids() {
    this.volunteacherService.getRemainingKids(this.route.snapshot.params['id']).subscribe(data => {
 
      this.kids = data
    })
  }
  getProject(id: number) {

    this.projectService.getProject(id).subscribe(data => {
      this.project = data
      this.projectStartingDate = this.project.startingDate
      this.projectEndingDate = this.project.endingDate

      for (let user of this.project.users) {
        this.selectedVolunteacher.push(user.userId)
      }

      for (const kid of this.project.kids) {
        this.selectedKids.push(kid.kidId)
      }


    }, error => {
      this.handleError(error)
    })
  }
  show(isShow): void {
    this.showForm = isShow
    this.hover = false
  }
  openEditSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  selectVolunteacher(event) {
    if (event.target.checked) {
      this.selectedVolunteacher.push(event.target.value)
    }
    else {
      let index: number = this.selectedVolunteacher.indexOf(event.target.value)
      this.selectedVolunteacher.splice(index, 1)
    }
  }

  selectKids(event) {
    if (event.target.checked) {
      this.selectedKids.push(event.target.value)
    }
    else {
      let index: number = this.selectedKids.indexOf(event.target.value)
      this.selectedKids.splice(index, 1)
    }
  }

  saveProject(form: NgForm) {
    this.disabled=true
    this.showProgressbar = true
    if (this.croppedImage != null) {
      const file = this.uploadImageComponent.image;
      this.fileService.pushFileToStorage(new FileUpload(file), this.baseUrl).subscribe(
        percentage => {
          this.percentage = Math.round(percentage);

          if (this.percentage == 100) {


            this.fileService.imageUrl.subscribe(data => {
              this.imageURL = data
              //alert("in component..." + this.imageURL)
              if (this.imageURL != null && this.isProjectEdited == false) {
                let today = new Date()
                let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
                this.project.creationTime = time
                this.project.photo = this.imageURL
                if (!(this.project.startingDate === this.projectStartingDate)) {
                  let startdate: string = this.project.startingDate
                  let sdate: string[] = startdate.split("-")
                  let startingdate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
                  this.project.startingDate = startingdate
                }

                if (!(this.project.endingDate === this.projectEndingDate)) {

                  let enddate: string = this.project.endingDate
                  let edate: string[] = enddate.split("-")
                  let endingdate = edate[1] + "-" + edate[2] + "-" + edate[0]
                  this.project.endingDate = endingdate
                }
                this.projectService.editProject(this.route.snapshot.params['id'], this.project, this.selectedVolunteacher, this.selectedKids).subscribe(data => {
                 

                  this.showProgressbar = false
                  this.openEditSnackBar()
                  this.disabled=false
                  this.router.navigate(['/admin/projects'])
                }, error => {
                  this.handleError(error)
                })
                this.isProjectEdited = true
              }
            })
          }
        }, error => {
          this.handleError(error)
        }
      ), error => {
        this.handleError(error)
      }
    }else
    {
      let today = new Date()
      let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
      this.project.creationTime = time
      if (!(this.project.startingDate === this.projectStartingDate)) {
        let startdate: string = this.project.startingDate
        let sdate: string[] = startdate.split("-")
        let startingdate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
        this.project.startingDate = startingdate
      }

      if (!(this.project.endingDate === this.projectEndingDate)) {

        let enddate: string = this.project.endingDate
        let edate: string[] = enddate.split("-")
        let endingdate = edate[1] + "-" + edate[2] + "-" + edate[0]
        this.project.endingDate = endingdate
      }
      this.projectService.editProject(this.route.snapshot.params['id'], this.project, this.selectedVolunteacher, this.selectedKids).subscribe(data => {
        

        this.showProgressbar = false
        this.openEditSnackBar()
        this.disabled=false
        this.router.navigate(['/admin/projects'])
      }, error => {
        this.handleError(error)
      })
    }

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

  getCroppedImage(image) {
    this.croppedImage = image
  }

}
