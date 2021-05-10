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
import { ActivatedRoute } from '@angular/router';
var GroupListComponent = /** @class */ (function () {
    function GroupListComponent(_sharedservice, route) {
        this._sharedservice = _sharedservice;
        this.route = route;
        this.colors = ["bg-danger", "bg-info", "bg-yellow", "bg-warning"];
    }
    GroupListComponent.prototype.ngOnInit = function () {
        this.getkidsgroup();
    };
    GroupListComponent.prototype.getkidsgroup = function () {
        var _this = this;
        this.sessionId = this.route.snapshot.params['id'];
        this._sharedservice.getkidsgrouplist().subscribe(function (data) {
            _this.groups = data;
        });
    };
    GroupListComponent = __decorate([
        Component({
            selector: 'app-group-list',
            templateUrl: './group-list.component.html',
            styleUrls: ['./group-list.component.css']
        }),
        __metadata("design:paramtypes", [CoreService, ActivatedRoute])
    ], GroupListComponent);
    return GroupListComponent;
}());
export { GroupListComponent };
//# sourceMappingURL=group-list.component.js.map