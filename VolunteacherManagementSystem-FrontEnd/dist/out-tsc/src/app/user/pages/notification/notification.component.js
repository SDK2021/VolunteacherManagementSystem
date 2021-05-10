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
import { CoreService } from 'src/app/core/services/core.service';
var NotificationComponent = /** @class */ (function () {
    function NotificationComponent(_sharedservice) {
        this._sharedservice = _sharedservice;
    }
    NotificationComponent.prototype.ngOnInit = function () {
        this.getAllSessions();
    };
    NotificationComponent.prototype.getAllSessions = function () {
        var _this = this;
        this._sharedservice.getSessions().subscribe(function (data) {
            _this.sessions = data;
            console.log(data);
        });
    };
    NotificationComponent = __decorate([
        Component({
            selector: 'app-notification',
            templateUrl: './notification.component.html',
            styleUrls: ['./notification.component.css']
        }),
        __metadata("design:paramtypes", [CoreService])
    ], NotificationComponent);
    return NotificationComponent;
}());
export { NotificationComponent };
//# sourceMappingURL=notification.component.js.map