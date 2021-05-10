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
var ActivitiesComponent = /** @class */ (function () {
    function ActivitiesComponent() {
        this.isShow = false;
        this.activities = [
            {
                activityId: 1,
                activityName: "Drawing",
                desc: "In this You can share basic knowledge of drawing",
                participants: [],
                events: [],
            },
            {
                activityId: 2,
                activityName: "Painting",
                desc: "In this You can share basic knowledge of drawing",
                participants: [],
                events: [],
            },
            {
                activityId: 3,
                activityName: "Sculpting",
                desc: "In this You can share basic knowledge of drawing",
                participants: [],
                events: [],
            },
            {
                activityId: 4,
                activityName: "Drawing",
                desc: "In this You can share basic knowledge of drawing",
                participants: [],
                events: [],
            }
        ];
    }
    ActivitiesComponent.prototype.ngOnInit = function () {
    };
    ActivitiesComponent.prototype.show = function () {
        this.isShow = !this.isShow;
    };
    ActivitiesComponent = __decorate([
        Component({
            selector: 'app-activities',
            templateUrl: './activities.component.html',
            styleUrls: ['./activities.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], ActivitiesComponent);
    return ActivitiesComponent;
}());
export { ActivitiesComponent };
//# sourceMappingURL=activities.component.js.map