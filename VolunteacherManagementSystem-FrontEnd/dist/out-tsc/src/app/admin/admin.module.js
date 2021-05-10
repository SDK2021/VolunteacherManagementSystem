var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentsModule } from '../core/components/components.module';
import { ActivitiesComponent } from './pages/events/activities/activities.component';
import { SessionsComponent } from './pages/sessions/shared-components/sessions/sessions.component';
import { GroupListComponent } from './pages/core/groups/shared-components/group-list/group-list.component';
import { ContentComponent } from './pages/core/content/shared-components/content/content.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/core/dashboard/dashboard.component';
import { VolunteachersListComponent } from './pages/volunteachers/volunteachers-list/volunteachers-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VillagesComponent } from './pages/core/villages/villages.component';
import { TabsComponent } from './components/tabs/tabs.component';
import { RequirementsComponent } from './pages/core/school/requirements/requirements.component';
import { SchoolsComponent } from './pages/core/school/schools/schools.component';
import { AreasComponent } from './pages/core/areas/areas.component';
import { ProjectComponent } from './pages/projects/project/project.component';
var AdminModule = /** @class */ (function () {
    function AdminModule() {
    }
    AdminModule = __decorate([
        NgModule({
            declarations: [ActivitiesComponent,
                SessionsComponent,
                GroupListComponent,
                ContentComponent,
                DashboardComponent,
                VolunteachersListComponent,
                VillagesComponent,
                TabsComponent,
                RequirementsComponent,
                SchoolsComponent,
                AreasComponent,
                ProjectComponent
            ],
            imports: [
                CommonModule,
                ComponentsModule,
                BrowserAnimationsModule,
                BrowserModule,
                RouterModule,
                NgbModule
            ]
        })
    ], AdminModule);
    return AdminModule;
}());
export { AdminModule };
//# sourceMappingURL=admin.module.js.map