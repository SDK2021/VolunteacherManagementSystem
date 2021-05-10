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
import { FormBuilder } from '@angular/forms';
import { MatSnackBar, } from '@angular/material/snack-bar';
var EditProfileComponent = /** @class */ (function () {
    function EditProfileComponent(fb, _snackBar) {
        this.fb = fb;
        this._snackBar = _snackBar;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.editProfile = this.fb.group({
            userInfo: this.fb.group({
                fname: [''],
                lname: [''],
                gender: [''],
                dob: [''],
            }),
            contactInfo: this.fb.group({
                email: [''],
                phone: [''],
                pincode: [''],
                village: [''],
                city: [''],
            }),
            otherInfo: this.fb.group({
                education: [''],
                employer: [''],
                schoolName: [''],
            }),
        });
        this.cities = ["Ahmedabad", "Rajkot", "Baroda", "Jamnagar"];
        this.village = [
            "Timba",
            "Paldi",
            "Limdi",
            "Pratij"
        ];
    }
    EditProfileComponent.prototype.onSubmit = function () {
        // TODO: Use EventEmitter with form value
        console.warn(this.editProfile.value);
    };
    EditProfileComponent.prototype.ngOnInit = function () {
    };
    EditProfileComponent.prototype.openSnackBar = function () {
        this._snackBar.open('Profile edited successfully..', 'close', {
            duration: 2000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
    };
    EditProfileComponent = __decorate([
        Component({
            selector: 'app-edit-profile',
            templateUrl: './edit-profile.component.html',
            styleUrls: ['./edit-profile.component.css']
        }),
        __metadata("design:paramtypes", [FormBuilder, MatSnackBar])
    ], EditProfileComponent);
    return EditProfileComponent;
}());
export { EditProfileComponent };
//# sourceMappingURL=edit-profile.component.js.map