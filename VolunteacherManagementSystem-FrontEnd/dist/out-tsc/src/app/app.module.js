var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
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
import { UserLayoutComponent } from './core/layouts/user-layout/user-layout/user-layout.component';
import { AttendanceService } from './core/services/attendance.service';
import { CoreService } from './core/services/core.service';
import { TimelineService } from './core/services/timeline.service';
import { HomeModule } from './home/home.module';
import { httpinterceptor } from './httpinterceptor';
import { KidsModule } from './kids/kids.module';
import { AttendanceModule } from './kids/pages/attendance/attendance.module';
import { SharedModule } from './shared/shared.module';
import { UserModule } from './user/user.module';
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        NgModule({
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
                UserModule
            ],
            declarations: [
                AppComponent,
                AdminLayoutComponent,
                AuthLayoutComponent,
                UserLayoutComponent,
            ],
            providers: [
                TimelineService, AttendanceService, CoreService,
                {
                    provide: HTTP_INTERCEPTORS,
                    useClass: httpinterceptor,
                    multi: true
                }
            ],
            bootstrap: [AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
export { AppModule };
//# sourceMappingURL=app.module.js.map