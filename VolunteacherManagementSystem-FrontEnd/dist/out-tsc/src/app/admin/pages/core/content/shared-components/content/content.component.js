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
var ContentComponent = /** @class */ (function () {
    function ContentComponent(_sharedservice) {
        this._sharedservice = _sharedservice;
        this.pdfSource = "https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf";
        // this.groups=[
        //   "Group1","Group2","Group3","Group4"
        // ]
    }
    ContentComponent.prototype.ngOnInit = function () {
        this.getkidsgroup();
    };
    ContentComponent.prototype.getkidsgroup = function () {
        var _this = this;
        this._sharedservice.getkidsgrouplist().subscribe(function (data) {
            _this.groups = data;
        });
    };
    ContentComponent = __decorate([
        Component({
            selector: 'app-content',
            templateUrl: './content.component.html',
            styleUrls: ['./content.component.css']
        }),
        __metadata("design:paramtypes", [CoreService])
    ], ContentComponent);
    return ContentComponent;
}());
export { ContentComponent };
//# sourceMappingURL=content.component.js.map