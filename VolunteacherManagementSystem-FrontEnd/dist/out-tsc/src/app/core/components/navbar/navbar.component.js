var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
var NavbarComponent = /** @class */ (function () {
    function NavbarComponent(location, element, router, _auth) {
        this.element = element;
        this.router = router;
        this._auth = _auth;
        this.location = location;
    }
    NavbarComponent.prototype.ngOnInit = function () {
        this.listTitles = ROUTES.filter(function (listTitle) { return listTitle; });
    };
    NavbarComponent.prototype.getTitle = function () {
        return "Home";
        // var titlee = this.location.prepareExternalUrl(this.location.path());
        // if(titlee.charAt(0) === '#'){
        //     titlee = titlee.slice( 1 );
        // }
        // for(var item = 0; item < this.listTitles.length; item++){
        //     if(this.listTitles[item].path === titlee){
        //         return this.listTitles[item].title;
        //     }
        // }
        // return 'TimeLine';
    };
    NavbarComponent.prototype.logout = function () {
        this._auth.logout();
    };
    NavbarComponent = __decorate([
        Component({
            selector: 'app-navbar',
            templateUrl: './navbar.component.html',
            styleUrls: ['./navbar.component.scss']
        }),
        __metadata("design:paramtypes", [Location, ElementRef, Router, authentication])
    ], NavbarComponent);
    return NavbarComponent;
}());
export { NavbarComponent };
//# sourceMappingURL=navbar.component.js.map