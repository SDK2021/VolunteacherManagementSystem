import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './core/model/user';
import { authentication } from './home/shared-services/authentication.service';
import { UsersService } from './user/services/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'argon-dashboard-angular';

  constructor(private authService:authentication,private userService:UsersService,private router:Router ){}

  ngOnInit() {
    
  
  }
}
