import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { Project } from 'src/app/core/model/project';
import { User } from 'src/app/core/model/user';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  projects:Project[] = []
  user:User

  showSpinner:boolean=false
  pLength:number
  noProjects:boolean=false

  showImageSpinner:boolean=true
  constructor(private router:Router,private route : ActivatedRoute,private authService:authentication,private userService:UsersService,private profileService:ProfileService) {
   
   }

   ngOnInit(): void {

    console.log(this.router.url.split('/'));
    let array:Array<string>=this.router.url.split('/')
    console.log(array);
    if(array[1]==="admin"&&array[2]==='volunteachers')
    {
      this.getVolunteachersProjects(this.route.snapshot.params['id']) 
    }
    else
        this.getAllProjectList();
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

  load()
  {
    this.showImageSpinner=false
  }
  getAllProjectList() 
  {
    this.showSpinner=true
      let username:string;
      let authuser:string[];
      let userId:number;
      //  this.projects = data
      authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
      username = atob(authuser[0]);

      this.userService.getUserByEmail(username).subscribe(data=>{
        this.user = data;
        userId = this.user.userId;
        this.profileService.getAllProjectNumberByUser(userId).subscribe(projectnum=>{        
            console.log(data)
            
            for(var num of projectnum)
            {
              this.profileService.getProjectById(num).pipe(finalize(()=>{
                this.showSpinner=false
            
            if (projectnum.length != 0) { 
              this.pLength = this.projects.length
              this.noProjects=false
            }
            //this.pLength=0
            if(this.pLength==0)
            {
              this.noProjects=true
            }
              })).subscribe(prodata=>{
                this.projects.push(prodata)
               
              },error=>{
                this.handleError(error)
               })
            } 
            
      },error=>{
        this.handleError(error)
       })
  })
}

    getVolunteachersProjects(id:number) 
    {
          this.profileService.getAllProjectNumberByUser(id).subscribe(projectnum=>{        
              for(var num of projectnum)
              {
                this.profileService.getProjectById(num).subscribe(prodata=>{
                  this.projects.push(prodata)
                
                },error=>{
                  this.handleError(error)
                 })
              } 
        },error=>{
          this.handleError(error)
         })
  
    }

}
