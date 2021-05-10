var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input, EventEmitter, Output } from '@angular/core';
import { MatSnackBar, } from '@angular/material/snack-bar';
var UploadImgComponent = /** @class */ (function () {
    function UploadImgComponent(_snackBar) {
        this._snackBar = _snackBar;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.title = 'angular-image-uploader';
        this.scale = 1;
        this.transform = {};
        this.imageChangedEvent = '';
        this.croppedImage = '';
        this.show = new EventEmitter();
    }
    UploadImgComponent.prototype.ngOnInit = function () {
        if (this.isPost)
            this.x = 6;
        else
            this.x = 4;
        this.show.emit(false);
        this.isShow = true;
    };
    UploadImgComponent.prototype.hide = function () {
        this._snackBar.open('Image uploaded successfully..', 'close', {
            duration: 2000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
        this.isShow = false;
        this.show.emit(true);
    };
    UploadImgComponent.prototype.fileChangeEvent = function (event) {
        this.imageChangedEvent = event;
    };
    UploadImgComponent.prototype.imageCropped = function (event) {
        this.croppedImage = event.base64;
    };
    UploadImgComponent.prototype.imageLoaded = function () {
        // show cropper
    };
    UploadImgComponent.prototype.cropperReady = function () {
        // cropper ready
    };
    UploadImgComponent.prototype.loadImageFailed = function () {
        // show message
    };
    UploadImgComponent.prototype.zoomOut = function () {
        this.scale -= .1;
        this.transform = __assign(__assign({}, this.transform), { scale: this.scale });
    };
    UploadImgComponent.prototype.zoomIn = function () {
        this.scale += .1;
        this.transform = __assign(__assign({}, this.transform), { scale: this.scale });
    };
    __decorate([
        Input(),
        __metadata("design:type", Boolean)
    ], UploadImgComponent.prototype, "isPost", void 0);
    __decorate([
        Output(),
        __metadata("design:type", Object)
    ], UploadImgComponent.prototype, "show", void 0);
    UploadImgComponent = __decorate([
        Component({
            selector: 'app-upload-img',
            templateUrl: './upload-img.component.html',
            styleUrls: ['./upload-img.component.css']
        }),
        __metadata("design:paramtypes", [MatSnackBar])
    ], UploadImgComponent);
    return UploadImgComponent;
}());
export { UploadImgComponent };
//# sourceMappingURL=upload-img.component.js.map