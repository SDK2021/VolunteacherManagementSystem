import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { PostsComponent } from './posts/posts.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ProjectsComponent } from './projects/projects.component';
import { SetProfileComponent } from './set-profile/set-profile.component';
import { ComponentsModule } from '../../../core/components/components.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';

@NgModule({
  declarations: [ProfileComponent,ChangePasswordComponent, PostsComponent, EditProfileComponent, ProjectsComponent, SetProfileComponent],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    ComponentsModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    InfiniteScrollModule,
    MatProgressSpinnerModule,
    MatProgressBarModule
  ],
  exports:[
    ProfileComponent
  ]
})
export class ProfileModule { }
