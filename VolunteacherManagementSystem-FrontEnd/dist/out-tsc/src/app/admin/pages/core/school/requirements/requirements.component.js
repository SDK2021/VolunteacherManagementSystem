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
var RequirementsComponent = /** @class */ (function () {
    function RequirementsComponent() {
        this.isShow = false;
        this.requirements = [
            { requirementid: "1", requirement: "New Lab" },
            { requirementid: "2", requirement: "Bathroom cleaning" },
            { requirementid: "3", requirement: "Room Color" },
            { requirementid: "4", requirement: "Books distribution to students" },
            { requirementid: "5", requirement: "Bags distribute" }
        ];
    }
    RequirementsComponent.prototype.ngOnInit = function () {
    };
    RequirementsComponent.prototype.show = function () {
        this.isShow = !this.isShow;
    };
    RequirementsComponent = __decorate([
        Component({
            selector: 'app-requirements',
            templateUrl: './requirements.component.html',
            styleUrls: ['./requirements.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], RequirementsComponent);
    return RequirementsComponent;
}());
export { RequirementsComponent };
//# sourceMappingURL=requirements.component.js.map