import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreatePostComponent } from './pages/create-post/create-post.component';
import { ComponentsModule } from '../core/components/components.module';
import { AddressComponent } from './components/address/address.component';
import { TimeLineComponent } from './pages/time-line/time-line.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ProfileModule } from './pages/profile/profile.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner'
import { InfiniteScrollModule } from 'ngx-infinite-scroll';

@NgModule({
  declarations: [CreatePostComponent, AddressComponent, TimeLineComponent,],
  imports: [
    CommonModule,
    ComponentsModule,
    ProfileModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    InfiniteScrollModule
  ],
  exports:[
    AddressComponent
  ]
})
export class SharedModule { }
