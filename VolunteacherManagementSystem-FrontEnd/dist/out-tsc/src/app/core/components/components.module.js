var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UploadImgComponent } from './upload-img/upload-img.component';
import { ImageCropperModule } from 'ngx-image-cropper';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { PdfsViewerComponent } from './pdfs-viewer/pdfs-viewer.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminSidebarComponent } from './admin-sidebar/admin-sidebar.component';
var ComponentsModule = /** @class */ (function () {
    function ComponentsModule() {
    }
    ComponentsModule = __decorate([
        NgModule({
            imports: [
                CommonModule,
                RouterModule,
                NgbModule,
                ImageCropperModule,
                PdfViewerModule
            ],
            declarations: [
                FooterComponent,
                NavbarComponent,
                SidebarComponent,
                UploadImgComponent,
                PdfsViewerComponent,
                PageNotFoundComponent,
                AdminSidebarComponent
            ],
            exports: [
                FooterComponent,
                NavbarComponent,
                SidebarComponent,
                UploadImgComponent,
                PdfsViewerComponent,
                AdminSidebarComponent
            ]
        })
    ], ComponentsModule);
    return ComponentsModule;
}());
export { ComponentsModule };
//# sourceMappingURL=components.module.js.map