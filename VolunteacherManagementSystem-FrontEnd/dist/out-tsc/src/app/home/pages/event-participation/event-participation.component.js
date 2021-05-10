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
import { Participant } from 'src/app/core/model/participant';
var EventParticipationComponent = /** @class */ (function () {
    function EventParticipationComponent() {
        this.eventParticipant = new Participant();
    }
    EventParticipationComponent.prototype.ngOnInit = function () {
    };
    EventParticipationComponent.prototype.onSubmit = function () {
        console.log(this.eventParticipant);
    };
    EventParticipationComponent = __decorate([
        Component({
            selector: 'app-event-participation',
            templateUrl: './event-participation.component.html',
            styleUrls: ['./event-participation.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], EventParticipationComponent);
    return EventParticipationComponent;
}());
export { EventParticipationComponent };
//# sourceMappingURL=event-participation.component.js.map