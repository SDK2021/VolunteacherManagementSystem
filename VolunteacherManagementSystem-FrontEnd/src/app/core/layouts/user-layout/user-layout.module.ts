import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { HttpClientModule } from '@angular/common/http';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';



import { UserLayoutRoutingModule } from './user-layout-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { routes1 } from './user-layout-routing.module';

@NgModule({
  declarations: [
    ],
    
  imports: [
    CommonModule,
    UserLayoutRoutingModule,
    RouterModule.forChild(routes1),
    NgbModule,
    FormsModule,
    HttpClientModule,
    ClipboardModule
  ]
})
export class UserLayoutModule { }
