import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddKidsComponent } from './pages/add-kids/add-kids.component';
import { KidsHomeComponent } from './pages/kids-home/kids-home.component';
import { AddParticipantsComponent } from './pages/add-participants/add-participants.component';
import { CreateReportComponent } from './pages/create-report/create-report.component';
import { MatSliderModule } from '@angular/material/slider';
import {ComponentsModule} from '../core/components/components.module'
import { RouterModule } from '@angular/router';
import {SharedModule } from '../shared/shared.module'
import {KidsListComponent} from './pages/kids-list/kids-list.component'
import {KidsAttendanceComponent} from './pages/kids-attendance/kids-attendance.component'
import { KidsReportComponent } from './pages/kids-report/kids-report.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SetKidsPhotoComponent } from './pages/set-kids-photo/set-kids-photo.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { EditKidComponent } from './pages/edit-kid/edit-kid.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { AdminModule } from '../admin/admin.module';

@NgModule({
  declarations: [KidsReportComponent,KidsAttendanceComponent,AddKidsComponent, KidsHomeComponent, AddParticipantsComponent, CreateReportComponent,KidsListComponent, SetKidsPhotoComponent, EditKidComponent],
  imports: [
    CommonModule,
    MatSliderModule,
    ComponentsModule,
    RouterModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    InfiniteScrollModule,
    AdminModule
  ],
  exports:[]
})
export class KidsModule { }
