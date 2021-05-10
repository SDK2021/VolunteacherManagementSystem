var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError, map, retry } from 'rxjs/operators';
var authentication = /** @class */ (function () {
    function authentication(http) {
        this.http = http;
        this.LOCAL_STORAGE_ATTRIBUTE_USERNAME = "VMSAuthorizationUser";
    }
    authentication.prototype.handleError = function (error) {
        var errorMessage = '';
        if (error.error instanceof ErrorEvent) {
            // client-side error
            errorMessage = "Client side Error: " + error.error.message;
        }
        else {
            // server-side error
            errorMessage = "Server side : Error Code: " + error.status + "\nMessage: " + error.message;
        }
        window.alert(errorMessage);
        return throwError(errorMessage);
    };
    authentication.prototype.loginAuthentication = function (username, password) {
        var _this = this;
        var header = { headers: new HttpHeaders({
                //  'Content-Type':"application/json",
                'authorization': 'Basic ' + btoa(username + ":" + password)
            })
        };
        return this.http.get("" + "http://localhost:9090/vms/events?page=0", header).pipe(map(function (res) {
            console.log(res);
            _this.successfullyLogin(username, password);
        })).pipe(retry(1), catchError(this.handleError));
    };
    authentication.prototype.createToken = function (username, password) {
        return 'Basic ' + btoa(username + ":" + password);
    };
    authentication.prototype.successfullyLogin = function (username, password) {
        localStorage.setItem(this.LOCAL_STORAGE_ATTRIBUTE_USERNAME, btoa(username) + " " + btoa(password));
    };
    authentication.prototype.isUserLogin = function () {
        console.log(this.username);
        console.log(this.passsword);
        var user = localStorage.getItem(this.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
        if (user == null) {
            return false;
        }
        return true;
    };
    authentication.prototype.logout = function () {
        this.username = null;
        this.passsword = null;
        localStorage.removeItem(this.LOCAL_STORAGE_ATTRIBUTE_USERNAME);
    };
    authentication = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], authentication);
    return authentication;
}());
export { authentication };
//# sourceMappingURL=authentication.service.js.map