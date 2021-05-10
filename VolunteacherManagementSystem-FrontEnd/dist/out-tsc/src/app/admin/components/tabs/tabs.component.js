var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Output, EventEmitter } from '@angular/core';
var TabsComponent = /** @class */ (function () {
    function TabsComponent() {
        this.tab1Class = false;
        this.tab2Class = false;
        this.showTab_1 = new EventEmitter();
        this.showTab_2 = new EventEmitter();
    }
    TabsComponent.prototype.ngOnInit = function () {
    };
    TabsComponent.prototype.showTab1 = function () {
        this.tab2Class = false;
        this.tab1Class = true;
        this.showTab_2.emit(false);
        this.showTab_1.emit(true);
    };
    TabsComponent.prototype.showTab2 = function () {
        this.tab1Class = false;
        this.tab2Class = true;
        this.showTab_1.emit(false);
        this.showTab_2.emit(true);
    };
    __decorate([
        Output(),
        __metadata("design:type", Object)
    ], TabsComponent.prototype, "showTab_1", void 0);
    __decorate([
        Output(),
        __metadata("design:type", Object)
    ], TabsComponent.prototype, "showTab_2", void 0);
    TabsComponent = __decorate([
        Component({
            selector: 'app-tabs',
            templateUrl: './tabs.component.html',
            styleUrls: ['./tabs.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], TabsComponent);
    return TabsComponent;
}());
export { TabsComponent };
//# sourceMappingURL=tabs.component.js.map