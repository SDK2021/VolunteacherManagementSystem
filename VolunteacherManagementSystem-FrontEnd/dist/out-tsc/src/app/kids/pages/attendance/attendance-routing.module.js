var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GroupListComponent } from 'src/app/admin/pages/core/groups/shared-components/group-list/group-list.component';
import { KidsAttendanceComponent } from 'src/app/kids/pages/kids-attendance/kids-attendance.component';
import { SessionsComponent } from 'src/app/admin/pages/sessions/shared-components/sessions/sessions.component';
var routes = [
    { path: 'sessions-list', component: SessionsComponent },
    { path: 'sessions-list/:id/groups', component: GroupListComponent },
    { path: 'sessions-list/:id/groups/:id/kids', component: KidsAttendanceComponent }
];
var AttendanceRoutingModule = /** @class */ (function () {
    function AttendanceRoutingModule() {
    }
    AttendanceRoutingModule = __decorate([
        NgModule({
            imports: [RouterModule.forChild(routes)],
            exports: [RouterModule]
        })
    ], AttendanceRoutingModule);
    return AttendanceRoutingModule;
}());
export { AttendanceRoutingModule };
//# sourceMappingURL=attendance-routing.module.js.map