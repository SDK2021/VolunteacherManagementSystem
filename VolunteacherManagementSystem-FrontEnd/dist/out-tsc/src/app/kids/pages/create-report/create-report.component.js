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
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { MatSnackBar, } from '@angular/material/snack-bar';
var CreateReportComponent = /** @class */ (function () {
    function CreateReportComponent(fb, _auth, router, _snackBar) {
        this.fb = fb;
        this._auth = _auth;
        this.router = router;
        this._snackBar = _snackBar;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.kidsReport = this.fb.group({
            interestArea: [''],
            futureExpectations: [''],
            remarks: [''],
            subjects: this.fb.group({
                gujarati: ['', Validators.required],
                english: ['', Validators.required],
                maths: ['', Validators.required],
            }),
            personality: this.fb.group({
                discipline: ['', Validators.required],
                prayer: [''],
                goshthi: [''],
                games: [''],
                abhivyakti: [''],
                volunteaching: [''],
                sports: [''],
                art: [''],
                literature: [''],
            }),
        });
    }
    CreateReportComponent.prototype.formatLabel = function (value) {
        if (value >= 1000) {
            return Math.round(value / 1000) + 'k';
        }
        return value;
    };
    CreateReportComponent.prototype.ngOnInit = function () {
    };
    CreateReportComponent.prototype.onSubmit = function () {
        // TODO: Use EventEmitter with form value
        console.warn(this.kidsReport.value);
    };
    CreateReportComponent.prototype.openSnackBar = function () {
        this._snackBar.open('Report created successfully..', 'close', {
            duration: 2000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
    };
    CreateReportComponent = __decorate([
        Component({
            selector: 'app-create-report',
            templateUrl: './create-report.component.html',
            styleUrls: ['./create-report.component.css']
        }),
        __metadata("design:paramtypes", [FormBuilder, authentication, Router, MatSnackBar])
    ], CreateReportComponent);
    return CreateReportComponent;
}());
export { CreateReportComponent };
//# sourceMappingURL=create-report.component.js.map