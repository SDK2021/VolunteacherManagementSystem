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
import { KidsService } from '../../shared-services/kids.service';
import { MatSnackBar, } from '@angular/material/snack-bar';
import { CoreService } from 'src/app/core/services/core.service';
var KidsAttendanceComponent = /** @class */ (function () {
    function KidsAttendanceComponent(_sharedservice2, _sharedservice, _auth, router, _snackBar) {
        this._sharedservice2 = _sharedservice2;
        this._sharedservice = _sharedservice;
        this._auth = _auth;
        this.router = router;
        this._snackBar = _snackBar;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.villages = ["Timba", "Paldi"];
    }
    KidsAttendanceComponent.prototype.ngOnInit = function () {
        this.getkidsgroup();
        this.getkids();
    };
    KidsAttendanceComponent.prototype.getkids = function () {
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
    KidsAttendanceComponent.prototype.openSnackBar = function () {
        this._snackBar.open('Attendance is taken successfully..', 'close', {
            duration: 2000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
    };
    KidsAttendanceComponent.prototype.getkidsgroup = function () {
        var _this = this;
        this._sharedservice2.getkidsgrouplist().subscribe(function (data) {
            _this.groups = data;
        });
    };
    KidsAttendanceComponent.prototype.trackGroupsById = function (index, g) {
        return g.groupId;
    };
    KidsAttendanceComponent = __decorate([
        Component({
            selector: 'app-kids-attendance',
            templateUrl: './kids-attendance.component.html',
            styleUrls: ['./kids-attendance.component.css']
        }),
        __metadata("design:paramtypes", [CoreService, KidsService, authentication, Router, MatSnackBar])
    ], KidsAttendanceComponent);
    return KidsAttendanceComponent;
}());
export { KidsAttendanceComponent };
//# sourceMappingURL=kids-attendance.component.js.map