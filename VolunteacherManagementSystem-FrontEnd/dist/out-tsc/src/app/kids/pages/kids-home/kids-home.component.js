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
var KidsHomeComponent = /** @class */ (function () {
    function KidsHomeComponent(_auth, router) {
        this._auth = _auth;
        this.router = router;
    }
    KidsHomeComponent.prototype.ngOnInit = function () {
    };
    KidsHomeComponent = __decorate([
        Component({
            selector: 'app-kids-home',
            templateUrl: './kids-home.component.html',
            styleUrls: ['./kids-home.component.css']
        }),
        __metadata("design:paramtypes", [authentication, Router])
    ], KidsHomeComponent);
    return KidsHomeComponent;
}());
export { KidsHomeComponent };
//# sourceMappingURL=kids-home.component.js.map