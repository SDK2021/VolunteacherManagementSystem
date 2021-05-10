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
import { MatSnackBar, } from '@angular/material/snack-bar';
import { Timelinepost } from 'src/app/core/model/timelinepost';
var CreatePostComponent = /** @class */ (function () {
    function CreatePostComponent(_snackBar) {
        this._snackBar = _snackBar;
        this.post = new Timelinepost();
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.isShow = false;
        this.showForm = false;
    }
    CreatePostComponent.prototype.ngOnInit = function () {
    };
    CreatePostComponent.prototype.show = function (isShow) {
        this.showForm = isShow;
    };
    CreatePostComponent.prototype.openSnackBar = function () {
        this._snackBar.open('Posted successfully..', 'close', {
            duration: 2000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
    };
    CreatePostComponent = __decorate([
        Component({
            selector: 'app-create-post',
            templateUrl: './create-post.component.html',
            styleUrls: ['./create-post.component.css']
        }),
        __metadata("design:paramtypes", [MatSnackBar])
    ], CreatePostComponent);
    return CreatePostComponent;
}());
export { CreatePostComponent };
//# sourceMappingURL=create-post.component.js.map