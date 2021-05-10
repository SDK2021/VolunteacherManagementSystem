var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, ViewChild, Input } from '@angular/core';
import { PdfViewerComponent } from 'ng2-pdf-viewer';
var PdfsViewerComponent = /** @class */ (function () {
    function PdfsViewerComponent() {
        this.title = 'angular-pdf-viewer-app';
        this.originalSize = false;
        this.fitToPage = true;
        this.showAll = true;
        this.autoresize = true;
        this.zoom = 1;
        this.pdfQuery = '';
    }
    PdfsViewerComponent.prototype.ngOnInit = function () {
    };
    PdfsViewerComponent.prototype.zoomIn = function () {
        this.zoom += 0.05;
    };
    PdfsViewerComponent.prototype.zoomOut = function () {
        if (this.zoom > 0.05)
            this.zoom -= 0.05;
    };
    // Event for search operation
    PdfsViewerComponent.prototype.searchQueryChanged = function (newQuery) {
        if (newQuery !== this.pdfQuery) {
            this.pdfQuery = newQuery;
            this.pdfComponent.pdfFindController.executeCommand('find', {
                query: this.pdfQuery,
                highlightAll: true
            });
        }
        else {
            this.pdfComponent.pdfFindController.executeCommand('findagain', {
                query: this.pdfQuery,
                highlightAll: true
            });
        }
    };
    // Event handler when new PDF file is selected
    PdfsViewerComponent.prototype.onFileSelected = function () {
        var _this = this;
        var $pdf = document.querySelector('#file');
        if (typeof FileReader !== 'undefined') {
            var reader = new FileReader();
            reader.onload = function (e) {
                _this.pdfSrc = e.target.result;
            };
            reader.readAsArrayBuffer($pdf.files[0]);
        }
    };
    PdfsViewerComponent.prototype.callBackFn = function (event) {
        console.log('callBackFn', event);
        // Setting total number of pages
        this.totalPages = event._pdfInfo.numPages;
    };
    PdfsViewerComponent.prototype.pageRendered = function (event) {
        console.log('pageRendered', event);
    };
    PdfsViewerComponent.prototype.textLayerRendered = function (event) {
        console.log('textLayerRendered', event);
    };
    PdfsViewerComponent.prototype.onError = function (event) {
        console.error('onError', event);
    };
    PdfsViewerComponent.prototype.onProgress = function (event) {
        console.log('onProgress', event);
    };
    __decorate([
        Input(),
        __metadata("design:type", String)
    ], PdfsViewerComponent.prototype, "pdfSrc", void 0);
    __decorate([
        ViewChild(PdfViewerComponent),
        __metadata("design:type", PdfViewerComponent)
    ], PdfsViewerComponent.prototype, "pdfComponent", void 0);
    PdfsViewerComponent = __decorate([
        Component({
            selector: 'app-pdfs-viewer',
            templateUrl: './pdfs-viewer.component.html',
            styleUrls: ['./pdfs-viewer.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], PdfsViewerComponent);
    return PdfsViewerComponent;
}());
export { PdfsViewerComponent };
//# sourceMappingURL=pdfs-viewer.component.js.map