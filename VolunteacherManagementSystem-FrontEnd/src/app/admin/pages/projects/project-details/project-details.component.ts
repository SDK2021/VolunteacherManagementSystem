import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { Project } from 'src/app/core/model/project';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  project: Project=new Project()
  totalVolunteachers: number=0
  totalEvents: number = 0
  totalKids: number=0
  totalSessions: number=0

  showSpinner: boolean = false
  constructor(private router: Router, private projectService: ProjectsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let projectId: number = this.route.snapshot.params['id']
    this.getPorject(projectId)
    this.getTotalKids(projectId)
    this.getTotalSessions(projectId)
    this.getTotalVolunteachers(projectId)
    this.getTotalEvents(projectId)
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

  
  getPorject(id: number) {
    this.showSpinner = true
    this.projectService.getProject(id).subscribe(data => {
      this.project = data
      this.showSpinner = false

    }, error => {
      this.handleError(error)
    })
  }

  getTotalVolunteachers(projectId) {
    this.projectService.getTotalVolunteachersByProject(projectId).subscribe(data => {
      this.totalVolunteachers = data
    },error=>{
     this.handleError(error)
    })
  }

  getTotalEvents(projectId) {
    this.projectService.getTotalEventsByProject(projectId).subscribe(data => {
      this.totalEvents = data
    },error=>{
     this.handleError(error)
    })
  }

  getTotalKids(projectId:number) {
    this.projectService.getTotalKidsByProject(projectId).subscribe(data => {
      this.totalKids = data
    },error=>{
     this.handleError(error)
    })
  }

  getTotalSessions(projectId:number) {
      this.projectService.getTotalSessionsByProject(projectId).subscribe(data => {
      this.totalSessions = data
    },error=>{
     this.handleError(error)
    })
  }
}

