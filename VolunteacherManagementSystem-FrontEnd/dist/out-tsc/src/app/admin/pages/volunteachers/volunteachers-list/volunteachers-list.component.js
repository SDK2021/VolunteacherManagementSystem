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
var VolunteachersListComponent = /** @class */ (function () {
    function VolunteachersListComponent() {
        this.array = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    }
    VolunteachersListComponent.prototype.ngOnInit = function () {
    };
    VolunteachersListComponent = __decorate([
        Component({
            selector: 'app-volunteachers-list',
            templateUrl: './volunteachers-list.component.html',
            styleUrls: ['./volunteachers-list.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], VolunteachersListComponent);
    return VolunteachersListComponent;
}());
export { VolunteachersListComponent };
//# sourceMappingURL=volunteachers-list.component.js.map