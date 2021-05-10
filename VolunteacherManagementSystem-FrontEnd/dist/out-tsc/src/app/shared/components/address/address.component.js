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
var AddressComponent = /** @class */ (function () {
    function AddressComponent(_sharedservice) {
        this._sharedservice = _sharedservice;
        this.countries = ["India", "Canada", "Norway"];
        this.states = ["Gujarat", "Maharashtra", "Madhyapradesh"];
        this.districts = ["Ahmdedabad", "Rajkot", "Jamnagar"];
        this.talukas = ["Daskroi", "Dholka", "Bopal"];
        this.villages = ["Timba", "Miroli", "Pirana"];
        this.areas = ["Thakorvas", "Nagri", "Chunaravas"];
    }
    AddressComponent.prototype.ngOnInit = function () {
        this.getkidsgroup();
    };
    AddressComponent.prototype.getkidsgroup = function () {
        var _this = this;
        this._sharedservice.getkidsgrouplist().subscribe(function (data) {
            _this.groups = data;
        });
    };
    AddressComponent.prototype.trackGroupsById = function (index, g) {
        return g.groupId;
    };
    AddressComponent.prototype.trackCountriesById = function (index, c) {
        return c.countryId;
    };
    AddressComponent.prototype.trackStatesById = function (index, s) {
        return s.stateId;
    };
    AddressComponent.prototype.trackDistrictsById = function (index, d) {
        return d.districtId;
    };
    AddressComponent.prototype.trackTalukasById = function (index, t) {
        return t.talukaId;
    };
    AddressComponent.prototype.trackVillagesById = function (index, v) {
        return v.villageId;
    };
    AddressComponent.prototype.trackAreaById = function (index, a) {
        return a.areaId;
    };
    AddressComponent = __decorate([
        Component({
            selector: 'app-address',
            templateUrl: './address.component.html',
            styleUrls: ['./address.component.css']
        }),
        __metadata("design:paramtypes", [CoreService])
    ], AddressComponent);
    return AddressComponent;
}());
export { AddressComponent };
//# sourceMappingURL=address.component.js.map