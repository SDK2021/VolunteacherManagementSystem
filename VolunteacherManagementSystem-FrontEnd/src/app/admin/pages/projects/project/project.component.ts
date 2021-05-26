import { Component, OnInit } from '@angular/core';
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


  
  showSpinner:boolean=false
  noProjects:boolean=false
  pLength:number

  kPage:number=0

  isProjectCreated:boolean=null

  showProgressbar: boolean = false
  edit: boolean = false
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  projects: Array<Project> = new Array()

  page:number=0
  vPage:number=0
  volunteachers: Array<Volunteacher> = []
  kids: Kid[] = []
  selectedVolunteacher: Array<Number> = []
  selectedKids: Array<Number> = []
  project: Project = new Project()

  baseUrl:string="/vms/projects"
  imageURL:string;

  message:boolean=false
  showForm:boolean=false

  showImageSpinner:boolean=true

  //remove this


  constructor(private fileService:FileUploadService,private dialog:MatDialog,private router:Router, private kidsService: KidService, private vtService: VolunteachersService, private _snackBar: MatSnackBar, private projectService: ProjectsService) {


  }

  ngOnInit(): void {
    // this.imageURL = localStorage.getItem("imageURL")
   
    // if(this.imageURL!=null)
    // {
    //   this.fileService.delete(this.imageURL)
    //   console.log("deleted");
    //   localStorage.removeItem("imageURL")
      
    // }
    this.kPage=0
    this.vPage=0
    this.getAllProjects()
    this.getAllVolunteacher(this.vPage);
    this.getAllKids(this.kPage);
    this.selectedVolunteacher = []
    this.selectedKids = []

  }

  // ngOnDestroy()
  // {
  //   if(this.isProjectCreated==false)
  //   {
  //     if(this.imageURL!=null)
  //     {
  //       this.fileService.delete(this.imageURL)
  //       localStorage.removeItem("imageURL")
  //     }
       
  //     console.log("Bye Bye");
      
  //   }
  // }

  load()
  {
    this.showImageSpinner=false
  }

  
  // show() {
  //   this.isShow = !this.isShow;
  // }
  handleError(error)
  {
    console.log(error);
    console.log(error.status);
    
    if(error.status===500)
    {
      this.router.navigate(['internal-server-error'])
    }
    else if(error.status===400)
    {
       this.message=true
       this.showProgressbar=false
    }
    else
    {
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

  vtTab:boolean=true
  kidTab:boolean=false

  tab1Class:boolean=true
  tab2Class:boolean=false

  showVtTab()
  {
    this.vtTab=true
    this.tab2Class=false
    this.tab1Class=true
    this.kidTab=false
  }
  showKidTab()
  {
    this.tab1Class=false
    this.tab2Class=true
    this.vtTab=false
    this.kidTab=true
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

  show(isShow):void
  {
    this.showImageSpinner=true
    this.showForm=isShow
    console.log(this.imageURL);
    
    this.imageURL = localStorage.getItem("imageURL")
    localStorage.removeItem("imageURL")
    
  }

  getAllProjects() {
    this.showSpinner=true
    this.projectService.getAllProjects().subscribe(data => {
      this.projects = data
      this.showSpinner=false
      console.log(data);
      
      if (data != null) {
        this.pLength = this.projects.length
        this.noProjects=false
      }
      //this.pLength=0
      if(this.pLength==0)
      {
        this.noProjects=true
      }
      console.log(this.projects);
      console.log(this.pLength);
      
    },error=>{
      this.handleError(error)
    })
  }

  isEdit() {
    this.tab1 = true
    this.tab2 = false
    this.edit = true
  }

  getAllKids(page:number) {
    this.kidsService.getAllKids(page).subscribe(data => {
      this.kids = data['content']
      console.log(this.kids);
      
    },error=>{
      this.handleError(error)
    })
  }

  getAllVolunteacher(page:number) {
    this.vtService.getAllVolunteachers(page).subscribe(data => {
      this.volunteachers = data['content']
      console.log(this.volunteachers)
    },error=>{
      this.handleError(error)
    })
  }

  addProject(form:NgForm) {
    this.showProgressbar = true
    let today = new Date()
    let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
    this.project.creationTime = time
    this.project.photo = this.imageURL
    // this.project.startingDate = value.startdate.tostring
    // this.project.endingDate = this.datepipe.transform(statingDate,'dd-MM-yyyy')
    // this.project.startingDate = statingDate
    // this.project.endingDate = endingDate
    let startdate: string = this.project.startingDate
    let sdate: string[] = startdate.split("-")
    let startingdate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
    this.project.startingDate = startingdate

    let enddate: string = this.project.endingDate
    let edate: string[] = enddate.split("-")
    let endingdate = edate[1] + "-" + edate[2] + "-" + edate[0]
    this.project.endingDate = endingdate
    console.log(this.selectedKids)
    this.projectService.addProject(this.project, this.selectedVolunteacher, this.selectedKids).subscribe(data => {
      console.log(data)
      this.openAddSnackBar()
      form.resetForm()
     // this.isProjectCreated=true
     setTimeout(() => {
      this.showProgressbar = false
      this.getAllProjects()
      this.showTab2(true)
     }, 1500);

      //this.getAllProjects()
    },error=>{
      this.handleError(error)
    })

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

  deleteProject(id:number,image:string)
  {
    this.showProgressbar=true
    
     this.projectService.deleteProject(id).subscribe(data=>{
       console.log(data);  
       this.fileService.delete(image)
       this.openDeleteSnackBar()  
       setTimeout(() => {
        this.getAllProjects()
        this.showProgressbar=false
       }, 2000);
     },error=>{
        this.showProgressbar=false
        this.handleError(error)
      })
  }

  delete(id:number,image:string)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteProject(id,image)
      }
    })
  }
}
