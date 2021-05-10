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
import { TimelineService } from 'src/app/core/services/timeline.service';
var PostsComponent = /** @class */ (function () {
    function PostsComponent(_sharedservice) {
        this._sharedservice = _sharedservice;
        this.isLiked = false;
    }
    PostsComponent.prototype.ngOnInit = function () {
        this.getposts();
    };
    PostsComponent.prototype.getposts = function () {
        var _this = this;
        this._sharedservice.gettimelinepostlist().subscribe(function (data) {
            _this.posts = data;
            console.log(_this.posts);
        });
    };
    PostsComponent.prototype.like = function () {
        if (this.isLiked == false) {
            this.liked = "text-danger";
            this.isLiked = true;
        }
        else {
            this.liked = "text-dark";
            this.isLiked = false;
        }
    };
    PostsComponent = __decorate([
        Component({
            selector: 'app-posts',
            templateUrl: './posts.component.html',
            styleUrls: ['./posts.component.css']
        }),
        __metadata("design:paramtypes", [TimelineService])
    ], PostsComponent);
    return PostsComponent;
}());
export { PostsComponent };
//# sourceMappingURL=posts.component.js.map