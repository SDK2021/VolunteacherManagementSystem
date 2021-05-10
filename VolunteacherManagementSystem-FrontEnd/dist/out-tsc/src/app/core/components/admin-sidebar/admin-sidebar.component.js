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
export var ROUTES = [
    { path: 'dashboard', title: 'Dashboard', icon: 'ni ni-shop text-primary', class: '' },
    { path: 'posts', title: 'Timeline', icon: 'fas fa-images text-danger', class: '' },
    // { path: 'content', title: 'Content',  icon: ' ni ni-ruler-pencil text-info', class: '' },
    // { path: 'sessions/sessions-list', title: 'Sessions',  icon: 'fas fa-book-reader text-primary', class: '' },
    //{ path: 'kids', title: 'Kids',  icon: 'fas fa-users text-yellow', class: '' },
    //{ path: '/donate', title: 'Donate',  icon: 'ni ni-money-coins text-green', class: '' },
    //{ path: 'profile/posts', title: 'User profile',  icon:'ni ni-single-02 text-pink', class: '' },
    { path: 'volunteachers', title: 'Volunteachers', icon: 'ni ni-single-02 text-pink', class: '' },
    { path: 'villages', title: 'Villages', icon: 'ni ni-single-02 text-pink', class: '' },
    { path: 'activities', title: 'Activities', icon: 'ni ni-single-02 text-pink', class: '' },
    { path: 'requirements', title: 'Requirements', icon: 'ni ni-single-02 text-pink', class: '' },
    { path: 'schools', title: 'Schools', icon: 'ni ni-single-02 text-pink', class: '' },
    { path: 'projects', title: 'Projects', icon: 'ni ni-single-02 text-pink', class: '' },
];
var AdminSidebarComponent = /** @class */ (function () {
    function AdminSidebarComponent(router) {
        this.router = router;
        this.isCollapsed = true;
    }
    AdminSidebarComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.menuItems = ROUTES.filter(function (menuItem) { return menuItem; });
        this.router.events.subscribe(function (event) {
            _this.isCollapsed = true;
        });
    };
    AdminSidebarComponent = __decorate([
        Component({
            selector: 'app-admin-sidebar',
            templateUrl: './admin-sidebar.component.html',
            styleUrls: ['./admin-sidebar.component.css']
        }),
        __metadata("design:paramtypes", [Router])
    ], AdminSidebarComponent);
    return AdminSidebarComponent;
}());
export { AdminSidebarComponent };
//# sourceMappingURL=admin-sidebar.component.js.map