import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCarouselModule } from '@ngbmodule/material-carousel';
import { NotificationComponent } from './pages/notification/notification.component';
import { AppHomeComponent } from './pages/app-home/app-home.component';
import { SessionReportingComponent } from './pages/session-reporting/session-reporting.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { DonationComponent } from './pages/donation/donation.component';

import { MatInputModule } from '@angular/material/input';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { MatProgressBarModule } from '@angular/material/progress-bar';



@NgModule({
  declarations: [
    NotificationComponent,
    AppHomeComponent,
    SessionReportingComponent,
    ResetPasswordComponent,
    DonationComponent,
  ],
  imports: [
    CommonModule,
    MatCarouselModule,
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    MatProgressSpinnerModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    InfiniteScrollModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
   
  ]
})
export class UserModule { }
