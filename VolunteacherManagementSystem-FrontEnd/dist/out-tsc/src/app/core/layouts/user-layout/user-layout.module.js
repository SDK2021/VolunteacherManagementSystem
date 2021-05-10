var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ClipboardModule } from 'ngx-clipboard';
import { UserLayoutRoutingModule } from './user-layout-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { routes1 } from './user-layout-routing.module';
var UserLayoutModule = /** @class */ (function () {
    function UserLayoutModule() {
    }
    UserLayoutModule = __decorate([
        NgModule({
            declarations: [],
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
    ], UserLayoutModule);
    return UserLayoutModule;
}());
export { UserLayoutModule };
//# sourceMappingURL=user-layout.module.js.map