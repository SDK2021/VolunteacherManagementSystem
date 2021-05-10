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
import { authentication } from '../../shared-services/authentication.service';
var LoginComponent = /** @class */ (function () {
    function LoginComponent(_auth, router) {
        this._auth = _auth;
        this.router = router;
        this.errorMessage = "Invalid Username or Password";
        this.successMessage = "Login Successfully";
        this.invalidLogin = false;
        this.successLogin = false;
    }
    LoginComponent.prototype.ngOnInit = function () {
        if (this._auth.isUserLogin()) {
            this.router.navigate(['user/home']);
        }
        else {
            this.router.navigate(['login']);
        }
    };
    LoginComponent.prototype.ngOnDestroy = function () {
    };
    LoginComponent.prototype.loginAuth = function (value) {
        var _this = this;
        this._auth.loginAuthentication(value.username, value.password).subscribe(function (data) {
            console.log("success" + data);
            _this.invalidLogin = false;
            _this.successLogin = true;
            _this.router.navigate(['user/home']);
        }, function (error) {
            console.log(error);
            _this.invalidLogin = true;
            _this.successLogin = false;
        });
        console.log(value);
    };
    LoginComponent = __decorate([
        Component({
            selector: 'app-login',
            templateUrl: './login.component.html',
            styleUrls: ['./login.component.scss']
        }),
        __metadata("design:paramtypes", [authentication, Router])
    ], LoginComponent);
    return LoginComponent;
}());
export { LoginComponent };
//# sourceMappingURL=login.component.js.map