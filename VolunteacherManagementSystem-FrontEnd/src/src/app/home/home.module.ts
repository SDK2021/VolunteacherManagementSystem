import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DonateComponent } from './pages/donate/donate.component';
import { EventParticipationComponent } from './pages/event-participation/event-participation.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { RouterModule } from '@angular/router';
import {authentication} from './shared-services/authentication.service'
import {MatInputModule} from '@angular/material/input';
import { VolunteacherFormComponent } from './pages/volunteacher-form/volunteacher-form.component'
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
@NgModule({
  declarations: [DonateComponent, EventParticipationComponent,LoginComponent,RegisterComponent,HomeComponent, VolunteacherFormComponent],
  imports: [
    CommonModule,

    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    MatInputModule,
    MatProgressBarModule,
    MatProgressSpinnerModule
  ],
  providers:[
    authentication
  ]
})
export class HomeModule { }
