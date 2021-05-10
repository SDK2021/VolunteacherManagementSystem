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
import { MatSnackBar, } from '@angular/material/snack-bar';
import { CoreService } from 'src/app/core/services/core.service';
var AddParticipantsComponent = /** @class */ (function () {
    function AddParticipantsComponent(_sharedservice, _auth, router, _snackBar) {
        this._sharedservice = _sharedservice;
        this._auth = _auth;
        this.router = router;
        this._snackBar = _snackBar;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.villages = ["Timba", "Miroli"];
        this.areas = ["Thakorvas", "Nagri"];
        // this.groups=["Group1","Group2"]
        this.kids = [
            {
                kname: "Sanjana Chunara"
            },
            {
                kname: "Honey Takor"
            },
            {
                kname: "Sanjana Chunara"
            },
            {
                kname: "Sanjana Chunara"
            },
            {
                kname: "Sanjana Chunara"
            }
        ];
    }
    AddParticipantsComponent.prototype.ngOnInit = function () {
        this.getkidsgroup();
    };
    AddParticipantsComponent.prototype.openSnackBar = function () {
        this._snackBar.open('Participants are added successfully..', 'close', {
            duration: 2000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
    };
    AddParticipantsComponent.prototype.getkidsgroup = function () {
        var _this = this;
        this._sharedservice.getkidsgrouplist().subscribe(function (data) {
            _this.groups = data;
        });
    };
    AddParticipantsComponent.prototype.trackGroupsById = function (index, g) {
        return g.groupId;
    };
    AddParticipantsComponent = __decorate([
        Component({
            selector: 'app-add-participants',
            templateUrl: './add-participants.component.html',
            styleUrls: ['./add-participants.component.css']
        }),
        __metadata("design:paramtypes", [CoreService, authentication, Router, MatSnackBar])
    ], AddParticipantsComponent);
    return AddParticipantsComponent;
}());
export { AddParticipantsComponent };
//# sourceMappingURL=add-participants.component.js.map