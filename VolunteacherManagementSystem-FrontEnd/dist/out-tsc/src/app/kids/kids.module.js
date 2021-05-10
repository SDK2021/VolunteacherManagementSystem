var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddKidsComponent } from './pages/add-kids/add-kids.component';
import { KidsHomeComponent } from './pages/kids-home/kids-home.component';
import { AddParticipantsComponent } from './pages/add-participants/add-participants.component';
import { CreateReportComponent } from './pages/create-report/create-report.component';
import { MatSliderModule } from '@angular/material/slider';
import { ComponentsModule } from '../core/components/components.module';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { KidsListComponent } from './pages/kids-list/kids-list.component';
import { KidsAttendanceComponent } from './pages/kids-attendance/kids-attendance.component';
import { KidsReportComponent } from './pages/kids-report/kids-report.component';
import { KidsComponent } from './pages/kids/kids.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SetKidsPhotoComponent } from './pages/set-kids-photo/set-kids-photo.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
var KidsModule = /** @class */ (function () {
    function KidsModule() {
    }
    KidsModule = __decorate([
        NgModule({
            declarations: [KidsReportComponent, KidsAttendanceComponent, AddKidsComponent, KidsHomeComponent, AddParticipantsComponent, CreateReportComponent, KidsListComponent, KidsComponent, SetKidsPhotoComponent],
            imports: [
                CommonModule,
                MatSliderModule,
                ComponentsModule,
                RouterModule,
                SharedModule,
                FormsModule,
                ReactiveFormsModule,
                MatSnackBarModule
            ],
            exports: []
        })
    ], KidsModule);
    return KidsModule;
}());
export { KidsModule };
//# sourceMappingURL=kids.module.js.map