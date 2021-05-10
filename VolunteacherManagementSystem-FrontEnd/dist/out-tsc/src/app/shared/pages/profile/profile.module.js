var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { PostsComponent } from './posts/posts.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ProjectsComponent } from './projects/projects.component';
import { VillagesComponent } from './villages/villages.component';
import { SetProfileComponent } from './set-profile/set-profile.component';
import { ComponentsModule } from '../../../core/components/components.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
var ProfileModule = /** @class */ (function () {
    function ProfileModule() {
    }
    ProfileModule = __decorate([
        NgModule({
            declarations: [ProfileComponent, ChangePasswordComponent, PostsComponent, EditProfileComponent, ProjectsComponent, VillagesComponent, SetProfileComponent],
            imports: [
                CommonModule,
                ProfileRoutingModule,
                ComponentsModule,
                NgbModule,
                FormsModule,
                ReactiveFormsModule,
                MatSnackBarModule
            ]
        })
    ], ProfileModule);
    return ProfileModule;
}());
export { ProfileModule };
//# sourceMappingURL=profile.module.js.map