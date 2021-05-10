var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppHomeComponent } from 'src/app/user/pages/app-home/app-home.component';
import { AttendanceComponent } from 'src/app/kids/pages/attendance/attendance/attendance.component';
import { GroupListComponent } from 'src/app/admin/pages/core/groups/shared-components/group-list/group-list.component';
import { KidsListComponent } from 'src/app/kids/pages/kids-list/kids-list.component';
import { KidsReportComponent } from 'src/app/kids/pages/kids-report/kids-report.component';
import { SessionReportingComponent } from 'src/app/user/pages/session-reporting/session-reporting.component';
import { TimeLineComponent } from 'src/app/shared/pages/time-line/time-line.component';
import { ProfileComponent } from 'src/app/shared/pages/profile/profile/profile.component';
import { KidsHomeComponent } from 'src/app/kids/pages/kids-home/kids-home.component';
import { AddKidsComponent } from 'src/app/kids/pages/add-kids/add-kids.component';
import { AddParticipantsComponent } from 'src/app/kids/pages/add-participants/add-participants.component';
import { UploadImgComponent } from '../../components/upload-img/upload-img.component';
import { CreatePostComponent } from 'src/app/shared/pages/create-post/create-post.component';
import { PdfsViewerComponent } from '../../components/pdfs-viewer/pdfs-viewer.component';
import { ContentComponent } from 'src/app/admin/pages/core/content/shared-components/content/content.component';
import { CreateReportComponent } from 'src/app/kids/pages/create-report/create-report.component';
import { SetProfileComponent } from 'src/app/shared/pages/profile/set-profile/set-profile.component';
import { KidsComponent } from 'src/app/kids/pages/kids/kids.component';
import { NotificationComponent } from 'src/app/user/pages/notification/notification.component';
import { SetKidsPhotoComponent } from 'src/app/kids/pages/set-kids-photo/set-kids-photo.component';
export var routes1 = [
    { path: 'home', component: AppHomeComponent },
    { path: 'profile', component: ProfileComponent,
        children: [
            {
                path: '',
                loadChildren: 'src/app/shared/pages/profile/profile.module#ProfileModule'
            }
        ]
    },
    { path: 'posts', component: TimeLineComponent },
    { path: 'sessions', component: AttendanceComponent,
        children: [
            {
                path: '',
                loadChildren: 'src/app/kids/pages/attendance/attendance.module#AttendanceModule'
            }
        ]
    },
    { path: 'kids', component: KidsHomeComponent },
    { path: 'kids/kids-list', component: KidsListComponent },
    { path: 'kids/kids-list/:id/kids-report', component: KidsReportComponent },
    { path: 'groups', component: GroupListComponent },
    { path: 'session-report', component: SessionReportingComponent },
    { path: 'kids/add-kid', component: AddKidsComponent },
    { path: 'kids/event/add-participants', component: AddParticipantsComponent },
    { path: 'upload', component: UploadImgComponent },
    { path: 'post/createPost', component: CreatePostComponent },
    { path: 'pdf', component: PdfsViewerComponent },
    { path: 'content', component: ContentComponent },
    { path: 'kids/create-report/kids-list', component: KidsComponent },
    { path: 'notifications', component: NotificationComponent },
    { path: 'kids/create-report/kids-list/:id/create', component: CreateReportComponent },
    { path: 'profile/set-profile', component: SetProfileComponent },
    { path: 'kids/set-photo', component: SetKidsPhotoComponent },
];
var UserLayoutRoutingModule = /** @class */ (function () {
    function UserLayoutRoutingModule() {
    }
    UserLayoutRoutingModule = __decorate([
        NgModule({
            imports: [RouterModule.forChild(routes1)],
            exports: [RouterModule, routes1]
        })
    ], UserLayoutRoutingModule);
    return UserLayoutRoutingModule;
}());
export { UserLayoutRoutingModule };
//# sourceMappingURL=user-layout-routing.module.js.map