import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { PdfViewerComponent } from 'ng2-pdf-viewer';

@Component({
  selector: 'app-pdfs-viewer',
  templateUrl: './pdfs-viewer.component.html',
  styleUrls: ['./pdfs-viewer.component.css']
})
export class PdfsViewerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  title = 'angular-pdf-viewer-app';

  @Input() pdfSrc : string 

  @ViewChild(PdfViewerComponent) private pdfComponent: PdfViewerComponent;

  originalSize = false;
  fitToPage = true;
  showAll = true;
  autoresize = true;
  zoom = 1;
  pdfQuery = '';
  totalPages: number;
  showBorders = true;
  renderTextModes = [0, 1, 2];
  renderTextMode = 1;

  zoomIn() {
    this.zoom += 0.05;
  }

  zoomOut() {
    if (this.zoom > 0.05)
      this.zoom -= 0.05;
  }


  // Event for search operation
  searchQueryChanged(newQuery: string) {
    if (newQuery !== this.pdfQuery) {
      this.pdfQuery = newQuery;
      this.pdfComponent.pdfFindController.executeCommand('find', {
        query: this.pdfQuery,
        highlightAll: true
      });
    } else {
      this.pdfComponent.pdfFindController.executeCommand('findagain', {
        query: this.pdfQuery,
        highlightAll: true
      });
    }
  }

  // Event handler when new PDF file is selected
  onFileSelected() {
    const $pdf: any = document.querySelector('#file');

    if (typeof FileReader !== 'undefined') {
      const reader = new FileReader();

      reader.onload = (e: any) => {
        this.pdfSrc = e.target.result;
      };

      reader.readAsArrayBuffer($pdf.files[0]);
    }
  }

  callBackFn(event) {
    console.log('callBackFn', event);
    // Setting total number of pages
    this.totalPages = event._pdfInfo.numPages
  }
  pageRendered(event) {
    console.log('pageRendered', event);
  }
  textLayerRendered(event) {
    console.log('textLayerRendered', event);
  }
  onError(event) {
    console.error('onError', event);
  }
  onProgress(event) {
    console.log('onProgress', event);
  }
}
