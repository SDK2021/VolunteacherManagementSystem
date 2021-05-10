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
var SchoolsComponent = /** @class */ (function () {
    function SchoolsComponent() {
        this.isShow = false;
        this.stream = [
            "commerce",
            "Science",
            "Girls school",
            "1 to 12 std"
        ],
            this.schools = [
                { schoolid: "1", name: "sukoon girls school", phone: 46543432, pincode: 380001, startingdate: "22-7-2018", stream: "commerce", totallabs: 3, totalstudents: 350, status: "good" },
                { schoolid: "2", name: "anupam vidhya vihar", phone: 46543432, pincode: 380001, startingdate: "22-7-2018", stream: "commerce", totallabs: 5, totalstudents: 650, status: "good" },
                { schoolid: "3", name: "J.L high school", phone: 46543432, pincode: 380001, startingdate: "22-7-2018", stream: "1 To 12", totallabs: 2, totalstudents: 850, status: "good" },
                { schoolid: "4", name: "sukoon girls school", phone: 46543432, pincode: 380001, startingdate: "22-7-2018", stream: "Science", totallabs: 1, totalstudents: 500, status: "better" },
                { schoolid: "5", name: "sukoon girls school", phone: 46543432, pincode: 380001, startingdate: "22-7-2018", stream: "commerce", totallabs: 3, totalstudents: 50, status: "best" }
            ];
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
    SchoolsComponent.prototype.ngOnInit = function () {
    };
    SchoolsComponent.prototype.show = function () {
        this.isShow = !this.isShow;
    };
    SchoolsComponent.prototype.showTab1 = function (show) {
        console.log("tab1" + show);
        this.tab1 = show;
    };
    SchoolsComponent.prototype.showTab2 = function (show) {
        console.log("tab2" + show);
        this.tab2 = show;
    };
    SchoolsComponent = __decorate([
        Component({
            selector: 'app-schools',
            templateUrl: './schools.component.html',
            styleUrls: ['./schools.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], SchoolsComponent);
    return SchoolsComponent;
}());
export { SchoolsComponent };
//# sourceMappingURL=schools.component.js.map