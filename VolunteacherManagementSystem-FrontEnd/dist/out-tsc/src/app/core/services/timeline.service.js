var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
var TimelineService = /** @class */ (function () {
    function TimelineService(_httpclient) {
        this._httpclient = _httpclient;
    }
    TimelineService.prototype.handleError = function (error) {
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
    TimelineService.prototype.gettimelinepostlist = function () {
        return this._httpclient.get("" + "http://localhost:9090/vms/posts?page=0")
            .pipe(retry(3), catchError(this.handleError));
    };
    TimelineService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], TimelineService);
    return TimelineService;
}());
export { TimelineService };
//# sourceMappingURL=timeline.service.js.map