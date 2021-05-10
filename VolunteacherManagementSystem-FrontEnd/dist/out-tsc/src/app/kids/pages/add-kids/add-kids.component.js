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
import { Kid } from 'src/app/core/model/kid';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { MatSnackBar, } from '@angular/material/snack-bar';
var AddKidsComponent = /** @class */ (function () {
    function AddKidsComponent(_auth, router, _snackBar) {
        this._auth = _auth;
        this.router = router;
        this._snackBar = _snackBar;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.isShow = false;
        this.showForm = false;
        this.namePattern = "[a-zA-Z]{3,20}";
        this.kid = new Kid();
    }
    AddKidsComponent.prototype.ngOnInit = function () {
        this.area = [
            "Thakor vas",
            "Paldi",
            "Limdi",
            "Pratij"
        ];
        this.levels = [
            "AatmaSiksha", "AatmaShodh", "AatmaVishesh"
        ];
    };
    AddKidsComponent.prototype.show = function (isShow) {
        this.showForm = isShow;
    };
    AddKidsComponent.prototype.onSubmit = function (form) {
        console.log(form);
    };
    AddKidsComponent.prototype.openSnackBar = function () {
        this._snackBar.open('Kid is added successfully..', 'close', {
            duration: 5000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
    };
    AddKidsComponent = __decorate([
        Component({
            selector: 'app-add-kids',
            templateUrl: './add-kids.component.html',
            styleUrls: ['./add-kids.component.css']
        }),
        __metadata("design:paramtypes", [authentication, Router, MatSnackBar])
    ], AddKidsComponent);
    return AddKidsComponent;
}());
export { AddKidsComponent };
//# sourceMappingURL=add-kids.component.js.map