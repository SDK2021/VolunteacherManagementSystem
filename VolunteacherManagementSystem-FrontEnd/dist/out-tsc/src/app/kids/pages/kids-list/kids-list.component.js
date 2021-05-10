var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { KidsService } from '../../shared-services/kids.service';
var KidsListComponent = /** @class */ (function () {
    function KidsListComponent(_sharedservice, _auth, router) {
        this._sharedservice = _sharedservice;
        this._auth = _auth;
        this.router = router;
        this.villages = [
            "Timba",
            "Paldi",
            "Limdi",
            "Pratij"
        ];
        this.groups = [
            "Group1",
            "Group2",
            "Group3",
            "Group4"
        ];
    }
    KidsListComponent.prototype.ngOnInit = function () {
        this.getkids();
    };
    KidsListComponent.prototype.getkids = function () {
        var _this = this;
        this._sharedservice.getkidslist().subscribe(function (data) {
            _this.kidslist = data;
            var i = 3;
            for (var _i = 0, _a = _this.kidslist; _i < _a.length; _i++) {
                var k = _a[_i];
                k.age = i;
                i += 1;
                //console.log(k.dob);
            }
        });
    };
    __decorate([
        Input(),
        __metadata("design:type", String)
    ], KidsListComponent.prototype, "label", void 0);
    KidsListComponent = __decorate([
        Component({
            selector: 'app-kids-list',
            templateUrl: './kids-list.component.html',
            styleUrls: ['./kids-list.component.css']
        }),
        __metadata("design:paramtypes", [KidsService, authentication, Router])
    ], KidsListComponent);
    return KidsListComponent;
}());
export { KidsListComponent };
//# sourceMappingURL=kids-list.component.js.map