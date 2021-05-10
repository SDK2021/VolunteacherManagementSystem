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
var ProjectsComponent = /** @class */ (function () {
    function ProjectsComponent() {
        this.projects = [
            {
                projectName: "My Nation My Responsibility",
                imageUrl: "mnmr.jpg",
                vts: 200,
                kids: 1500,
                sessions: 200,
                data: "Teachers help students and other people learn and progress their life. Teachers feel honor and pride to direct their students towards the right path of life. Every student is equal in the teacher's eyes; they never do partiality."
            },
            {
                projectName: "Ek Din Desh k Liye",
                imageUrl: "ekdin.jpg",
                vts: 200,
                kids: 1500,
                sessions: 200,
                data: "Teachers help students and other people learn and progress their life. Teachers feel honor and pride to direct their students towards the right path of life. Every student is equal in the teacher's eyes; they never do partiality."
            },
            {
                projectName: "Avasath",
                imageUrl: "tecno.jpg",
                vts: 200,
                kids: 1500,
                sessions: 200,
                data: "Teachers help students and other people learn and progress their life. Teachers feel honor and pride to direct their students towards the right path of life. Every student is equal in the teacher's eyes; they never do partiality."
            }
        ];
    }
    ProjectsComponent.prototype.ngOnInit = function () {
    };
    ProjectsComponent = __decorate([
        Component({
            selector: 'app-projects',
            templateUrl: './projects.component.html',
            styleUrls: ['./projects.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], ProjectsComponent);
    return ProjectsComponent;
}());
export { ProjectsComponent };
//# sourceMappingURL=projects.component.js.map