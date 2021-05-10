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
//import { AttendanceService } from 'src/app/services/attendance.service';
import { CoreService } from 'src/app/core/services/core.service';
var SessionsComponent = /** @class */ (function () {
    function SessionsComponent(_sharedservice) {
        this._sharedservice = _sharedservice;
    }
    SessionsComponent.prototype.ngOnInit = function () {
        this.sessions = [];
        this.getAllSessions();
    };
    SessionsComponent.prototype.getAllSessions = function () {
        var _this = this;
        this._sharedservice.getSessions().subscribe(function (data) {
            _this.sessions = data;
            console.log(data);
            for (var i = 0; i < _this.sessions.length; i++) {
                //if(curre==truw)
                _this.sessions[i]["show"] = true;
                //else
            }
        });
    };
    SessionsComponent = __decorate([
        Component({
            selector: 'app-sessions',
            templateUrl: './sessions.component.html',
            styleUrls: ['./sessions.component.css']
        }),
        __metadata("design:paramtypes", [CoreService])
    ], SessionsComponent);
    return SessionsComponent;
}());
export { SessionsComponent };
//# sourceMappingURL=sessions.component.js.map