import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';
import { UsersService } from 'src/app/user/services/users.service';
import { User } from '../../model/user';


declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
  children:Array<any>;
  showChild:boolean
  adjustment:string
}

export const ROUTES: RouteInfo[] = [
  { path: '', title: 'Dashboard',  icon: 'ni ni-shop text-primary', class: '' ,children:[], showChild:false,adjustment:''},
  { path: 'post', title: 'Timeline',  icon: 'fas fa-images text-danger', class: '' ,children:[],showChild:false,adjustment:''},
  { path: 'volunteachers', title: 'Volunteachers',  icon:'fas fa-users text-yellow', class: '',children:[],showChild:false ,adjustment:''},
  { path: 'kids', title: 'Kids',  icon:'fas fa-child text-info', class: '' ,children:[

    { path: 'groups', title: 'Groups',  icon:'ni ni-single-02 text-pink', class: '' ,children:[]},

  ],showChild:false,adjustment:'ml-8 pl-1'},
  { path: 'projects', title: 'Projects',  icon:'fab fa-leanpub text-danger', class: 'fas fa-chevron-right text-muted',children:[

    { path: 'villages', title: 'Villages',  icon:'ni ni-single-02 text-pink', class: '',children:[] },

  ],showChild:false ,adjustment:'ml-7 pl-2'},
  { path: 'sessions', title: 'Sessions',  icon:'fas fa-book-reader text-primary', class: 'fas fa-chevron-right text-muted',children:[

    { path: 'sessions/all', title: 'All Sessions',  icon:'ni ni-single-02 text-pink', class: '' ,children:[]},
    { path: 'content', title: 'Content',  icon:'ni ni-single-02 text-pink', class: '' ,children:[]},
    
  ],showChild:false ,adjustment:'ml-7 pl-1'},
  { path: 'events', title: 'Events',  icon:'fas fa-calendar-plus text-warning', class: 'fas fa-chevron-right text-muted',children:[

    { path: 'events/all', title: 'All Events',  icon:'ni ni-single-02 text-pink', class: '' ,children:[]}, 
    { path: 'activities', title: 'Activities',  icon:'ni ni-single-02 text-pink', class: '' ,children:[]},

  ] ,showChild:false,adjustment:'ml-7 pl-3 '},
  { path: 'schools', title: 'Schools',  icon:'fas fa-school text-info', class: '' ,children:[],showChild:false,adjustment:'ml-7 pl-2 '},
  // { path: 'donation', title: 'Donation',  icon:'fas fa-hand-holding-usd text-success', class: '',children:[


  // ] ,showChild:false,adjustment:''},
  { path: 'report', title: 'Report',  icon:'fas fa-file-invoice text-primary', class: '',children:[

    
  ] ,showChild:false,adjustment:''},


  // { path: 'villages', title: 'Villages',  icon:'ni ni-single-02 text-pink', class: '',children:[],showChild:false },
  
 
 
 
  // { path: 'donors', title: 'Donors',  icon:'ni ni-single-02 text-pink', class: '',children:[] ,showChild:false},
  
 
  // { path: 'attendance', title: 'Attendance',  icon:'ni ni-single-02 text-pink', class: '',children:[] ,showChild:false},
  
 
  // { path: 'feedback', title: 'Feedback',  icon:'ni ni-single-02 text-pink', class: '',children:[] ,showChild:false },
  // { path: 'areas', title: 'Areas',  icon:'ni ni-single-02 text-pink', class: '' ,children:[],showChild:false},
  
  
  { path: 'requests', title: 'Requests',  icon:'ni ni-single-02 text-danger', class: '' ,children:[],showChild:false,
adjustment:''},
  // { path: 'event-details', title: 'Event Details',  icon:'ni ni-single-02 text-pink', class: '' ,children:[],showChild:false},
  // { path: 'project-details', title: 'Project Details',  icon:'ni ni-single-02 text-pink', class: '' ,children:[],showChild:false},
  // { path: 'reports-list', title: 'Reports List',  icon:'ni ni-single-02 text-pink', class: '' ,children:[],showChild:false},
  // { path: 'report', title: 'Report',  icon:'ni ni-single-02 text-pink', class: '' ,children:[],showChild:false},
];
@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrls: ['./admin-sidebar.component.css']
})
export class AdminSidebarComponent implements OnInit {
  
  public menuItems: any[];
  public isCollapsed = true;

  user:User=new User()
  userType:string=''

  constructor(private profileService:ProfileService,private userService:UsersService,private authService:authentication,private router: Router) {
   // this.showChildren(3)
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
  ngOnInit(): void {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });

   let user:Array<string>
   user=localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')

   this.userService.getUserByEmail(atob(user[0])).subscribe(data=>{
     this.user=data
     if(data.type.typeId==1)
      {
        this.userType='admin'
        this.profileService.adminProfileImage.subscribe(data=>{
          if(data!=null)
            this.user.photo=data
        })
      }    
      else{
        this.userType='user'
        this.profileService.userProfileImage.subscribe(data=>{
          if(data!=null)
            this.user.photo=data
        })
      }  
       
        
   },error=>{
     this.handleError(error)
   })
  }

  showChildren(index:number)
  {
    
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.menuItems[index]["showChild"]=!this.menuItems[index]["showChild"]  
    if( this.menuItems[index]["class"]==="fas fa-chevron-down text-primary")
    {     
      this.menuItems[index]["class"]="fas fa-chevron-right text-muted"
    }
    else if( this.menuItems[index]["class"]==="fas fa-chevron-right text-muted")
    {
      this.menuItems[index]["class"]="fas fa-chevron-down text-primary"
    }
   
  }

}
