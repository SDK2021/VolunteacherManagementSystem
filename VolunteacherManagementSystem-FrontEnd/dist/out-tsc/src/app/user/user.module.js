var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCarouselModule } from '@ngbmodule/material-carousel';
import { NotificationComponent } from './pages/notification/notification.component';
import { AppHomeComponent } from './pages/app-home/app-home.component';
import { SessionReportingComponent } from './pages/session-reporting/session-reporting.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
var UserModule = /** @class */ (function () {
    function UserModule() {
    }
    UserModule = __decorate([
        NgModule({
            declarations: [
                NotificationComponent,
                AppHomeComponent,
                SessionReportingComponent,
            ],
            imports: [
                CommonModule,
                MatCarouselModule,
                BrowserModule,
                BrowserAnimationsModule,
                RouterModule,
            ]
        })
    ], UserModule);
    return UserModule;
}());
export { UserModule };
//# sourceMappingURL=user.module.js.map