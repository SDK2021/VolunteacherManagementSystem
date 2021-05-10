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
var VillagesComponent = /** @class */ (function () {
    function VillagesComponent() {
        this.isShow = false;
        this.countries = ["India", "Canada", "Norway"];
        this.states = ["Gujarat", "Maharashtra", "Madhyapradesh"];
        this.districts = ["Ahmdedabad", "Rajkot", "Jamnagar"];
        this.talukas = ["Daskroi", "Dholka", "Bopal"];
        this.villages = [
            { villageid: "1", name: "Timba" },
            { villageid: "2", name: "Paldi" },
            { villageid: "3", name: "Raykhad" },
            { villageid: "4", name: "Naroda" },
            { villageid: "5", name: "sanand" }
        ];
    }
    VillagesComponent.prototype.ngOnInit = function () {
    };
    VillagesComponent.prototype.show = function () {
        this.isShow = !this.isShow;
    };
    VillagesComponent.prototype.showTab1 = function (show) {
        console.log("tab1" + show);
        this.tab1 = show;
    };
    VillagesComponent.prototype.showTab2 = function (show) {
        console.log("tab2" + show);
        this.tab2 = show;
    };
    VillagesComponent = __decorate([
        Component({
            selector: 'app-villages',
            templateUrl: './villages.component.html',
            styleUrls: ['./villages.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], VillagesComponent);
    return VillagesComponent;
}());
export { VillagesComponent };
//# sourceMappingURL=villages.component.js.map