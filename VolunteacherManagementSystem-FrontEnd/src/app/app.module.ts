import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminModule } from './admin/admin.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './core/components/components.module';
import { AdminLayoutComponent } from './core/layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './core/layouts/auth-layout/auth-layout.component';
import { UserLayoutComponent } from './core/layouts/user-layout/user-layout/user-layout.component'
import { HomeModule } from './home/home.module';
import { httpinterceptor } from './httpinterceptor';
import { KidsModule } from './kids/kids.module';
import { AttendanceModule } from './kids/pages/attendance/attendance.module';
import { SharedModule } from './shared/shared.module';
import { UserModule } from './user/user.module';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { RoleCanActivateGuard } from './core/guards/role-can-activate.guard';
@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
    AppRoutingModule,
    AttendanceModule,
    SharedModule,
    HomeModule,
    KidsModule,
    CommonModule,
    MatButtonModule,
    ReactiveFormsModule,
    AdminModule,
    UserModule,
    InfiniteScrollModule
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
    UserLayoutComponent,
  ],
  providers: [
  {
    provide:HTTP_INTERCEPTORS,
    useClass:httpinterceptor,
    multi:true
  },RoleCanActivateGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
