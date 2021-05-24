import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { NavbarComponent } from 'src/app/core/components/navbar/navbar.component';
import { SidebarComponent } from 'src/app/core/components/sidebar/sidebar.component';
import { AuthLayoutComponent } from 'src/app/core/layouts/auth-layout/auth-layout.component';
import { User } from 'src/app/core/model/user';
import { Usertype } from 'src/app/core/model/usertype';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  

  profileImg:string="default-profile.jpg"
  cities: Array<string>
  user:User=new User();
  
  totalProject:number;
  totalSessions:number;
  totalPosts:number;
  volunteacher:Volunteacher=new Volunteacher()

  editProfile:boolean=true;
  subHeading:boolean=true
  heading:boolean=false

  userType:string=''

  isVt:boolean=false
  show:boolean=true

  showImageSpinner:boolean=true
  constructor(private navbar:NavbarComponent,private vtService:VolunteachersService,private route:ActivatedRoute,private authService:authentication,private userService:UsersService,private profileService:ProfileService,private router:Router) {
   
   }

  ngOnInit(): void {
    this.volunteacher.user=new User()
    this.volunteacher.user.type=new Usertype()
    console.log(this.router.url.split('/'));
    let array:Array<string>=this.router.url.split('/')
    console.log(array);
    // this.router.navigate([''])
    setTimeout(() => {
      this.navbar.ngOnInit()
    }, 2000); 
   
   
    console.log("ng on init call");
    
  
    if(array[1]==="admin")
    {
      this.isVt=false
     
      if(array[2]==='volunteachers')
      {
        this.isVt=true
          this.editProfile=false
          this.subHeading=false
          // this.getVolunteacher(this.route.snapshot.params['id'])
          // console.log(this.volunteacher);
          
           this.getVolunteacherInfo(this.route.snapshot.params['id'])
           
           this.heading=true
      }
      else
      {
        // this.isVt=true
        this.show=false
        this.totalProject = 0;
        this.totalSessions = 0;
        this.totalPosts = 0;
          let username:string;
          let authuser:string[];
          
      
          authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
          username = atob(authuser[0]);
          this.userService.getUserByEmail(username).subscribe(data=>{
          this.user=data
            if(data.type.typeId==1)
              this.userType='admin'
            else
              this.userType='user'
            this.getTotalPosts(this.user.userId)
          })
        // this.getTotalProjectByUser();
        this.profileImg="team-4-800x800.jpg"

      }
        
    }
    else
    {
     
      this.show=true
      this.totalProject = 0;
      this.totalSessions = 0;
      this.totalPosts = 0;
      this.profileImg="team-4-800x800.jpg"
      this.getTotalProjectByUser();
      this.profileImg="team-4-800x800.jpg"
    }
    
  }

  load()
  {
    this.showImageSpinner=false
  }
  
 handleError(error)
  {
    console.log(error);
    console.log(error.status);
    
    // if(error.status===500)
    // {
    //   this.router.navigate(['internal-server-error'])
    // }
    // else
    // {
    //   this.router.navigate(['error-page'])
    // }
  }

  getTotalProjectByUser() {
    let username:string;
    let authuser:string[];
    let userId:number;

    authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
    username = atob(authuser[0]);
    this.userService.getUserByEmail(username).pipe(finalize(()=>{
      this.profileService.getTotalProject(userId).pipe(finalize(()=>{
        this.getTotalPosts(userId)
        this.getTotalSessions(userId)
        this.getVolunteacher(userId)
      })).subscribe(data=>{
        console.log(data)
        this.totalProject = data
      },error=>{
      this.handleError(error)
    })
    })).subscribe(data=>{
      this.user = data;
      userId = this.user.userId; 
    })
  }

  getTotalSessions(userId:number)
  {
      this.profileService.getTotalSessions(userId).subscribe(data=>{
        console.log(data)
        this.totalSessions = data
      },error=>{
        this.handleError(error)
      })
  }

  getTotalPosts(userId:number)
  {
      this.profileService.getTotalPosts(userId).subscribe(data=>{
        console.log(data)
        this.totalPosts = data
      },error=>{
        this.handleError(error)
      })
  }

  getVolunteacher(userId:number)
  {
    this.profileService.getVolunteacherByUser(userId).subscribe(data=>
      {
        this.volunteacher = data
        this.volunteacher.experience=this.calculateExperience(new Date(this.volunteacher.joiningDate))
        console.log(this.volunteacher)
      },error=>{
        this.handleError(error)
      })
  }

  calculateExperience(jDate:Date):string
  {
      
      let joiningDate:Date=new Date(jDate)
      console.log(joiningDate);
      let currentMonth=new Date().getMonth()
      let currentDate=new Date().getDate()
      let currentYear=new Date().getFullYear()

      if(joiningDate.getFullYear()==currentYear)
      {

        if(joiningDate.getMonth()==currentMonth)
        {
            return "Joioned this month";          
        }
        else
        {
            return "Working since "+ (currentMonth-joiningDate.getMonth())+" month";
        }
        
      }
      else
      {
        return "Working since "+(currentYear-joiningDate.getFullYear())+" year";
        
      }
      
  }

  getVolunteacherInfo(id:number) {
    
      this.profileService.getTotalProject(id).pipe(finalize(()=>{
        this.getTotalPosts(id)
        this.getTotalSessions(id)
        this.getVolunteacher(id)
      })).subscribe(data=>{
        console.log(data)
        this.totalProject = data
      },error=>{
        this.handleError(error)
      })
   
  }

  getVolunteacherById(id:number)
  {
    this.vtService.getVoluntecherByid(id).subscribe(data=>{
      this.volunteacher=data
    },error=>{
      this.handleError(error)
    })
  }

}
