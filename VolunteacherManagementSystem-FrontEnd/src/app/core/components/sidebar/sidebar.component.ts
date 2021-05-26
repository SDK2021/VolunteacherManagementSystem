import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';
import { User } from '../../model/user';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
    children:Array<any>;
    showChild:boolean
}
export const ROUTES: RouteInfo[] = [
    { path: '', title: 'Home',  icon: 'ni ni-shop text-primary', class: '',children:[],showChild:false },
    { path: 'posts', title: 'Timeline',  icon: 'fas fa-images text-danger', class: '' ,children:[],showChild:false},
    { path: 'content', title: 'Content',  icon: ' ni ni-ruler-pencil text-info', class: '' ,children:[],showChild:false},
    { path: 'sessions/sessions-list', title: 'Sessions',  icon: 'fas fa-book-reader text-primary', class: '' ,children:[],showChild:false},
    { path: 'kids/add-kid', title: 'Kids',  icon: 'fas fa-users text-yellow', class: 'ni ni-bold-right text-muted' ,children:[

      { path: 'kids/create-report/kids-list', title: 'Create Report',  icon: ' fas fa-plus text-info', class: ''},
      { path: 'kids/kids-list', title: 'View Reports',  icon: 'fas fa-plus text-danger', class: ''},
      { path: 'kids/event-participation', title: 'Event participation',  icon: ' fas fa-plus text-yellow', class: ''},
      { path: 'kids/edit-kids/kids-list', title: 'Edit Kid',  icon: 'fas fa-plus text-primary', class: ''},
    

    ],showChild:false},
    { path: 'donation', title: 'Donate',  icon: 'ni ni-money-coins text-green', class: '' ,children:[],showChild:false},
    { path: 'profile/posts', title: 'Profile',  icon:'ni ni-single-02 text-pink', class: '',children:[],showChild:false },

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})

@Injectable({
  providedIn:'root'
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;

  showImageSpinner:boolean=true

  user:User=new User()
  userType:string=''
  constructor(private userService:UsersService,private router: Router,private _auth:authentication) { }

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
  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
   
   let user:Array<string>
   user=localStorage.getItem(this._auth.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(' ')

   this.userService.getUserByEmail(atob(user[0])).subscribe(data=>{
     this.user=data
     if(data.type.typeId==1)
       this.userType='admin'
     else
       this.userType='user'
       
     console.log(this.user);
        
   },error=>{
     this.handleError(error)
   })
  }

  logout()
  {
    this._auth.logout();
    this.router.navigate([''])
  }

  
  load()
  {
    this.showImageSpinner=false
  }
  
  showChildren(index:number)
  {
    console.log("Show children called..");
    
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    console.log(this.menuItems[index]["showChild"]=!this.menuItems[index]["showChild"]);  
    if( this.menuItems[index]["class"]==="ni ni-bold-down text-primary")
    {     
      this.menuItems[index]["class"]="ni ni-bold-right text-muted"
    }
    else if( this.menuItems[index]["class"]==="ni ni-bold-right text-muted")
    {
      this.menuItems[index]["class"]="ni ni-bold-down text-primary"
    }
   
  }
}
