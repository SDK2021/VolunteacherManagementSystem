var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
export var ROUTES = [
    { path: 'home', title: 'Home', icon: 'ni ni-shop text-primary', class: '' },
    { path: 'posts', title: 'Timeline', icon: 'fas fa-images text-danger', class: '' },
    { path: 'content', title: 'Content', icon: ' ni ni-ruler-pencil text-info', class: '' },
    { path: 'sessions/sessions-list', title: 'Sessions', icon: 'fas fa-book-reader text-primary', class: '' },
    { path: 'kids', title: 'Kids', icon: 'fas fa-users text-yellow', class: '' },
    { path: '/donate', title: 'Donate', icon: 'ni ni-money-coins text-green', class: '' },
    { path: 'profile/posts', title: 'User profile', icon: 'ni ni-single-02 text-pink', class: '' },
];
var SidebarComponent = /** @class */ (function () {
    function SidebarComponent(router, _auth) {
        this.router = router;
        this._auth = _auth;
        this.isCollapsed = true;
    }
    SidebarComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.menuItems = ROUTES.filter(function (menuItem) { return menuItem; });
        this.router.events.subscribe(function (event) {
            _this.isCollapsed = true;
        });
    };
    SidebarComponent.prototype.logout = function () {
        this._auth.logout();
    };
    SidebarComponent = __decorate([
        Component({
            selector: 'app-sidebar',
            templateUrl: './sidebar.component.html',
            styleUrls: ['./sidebar.component.scss']
        }),
        __metadata("design:paramtypes", [Router, authentication])
    ], SidebarComponent);
    return SidebarComponent;
}());
export { SidebarComponent };
//# sourceMappingURL=sidebar.component.js.map