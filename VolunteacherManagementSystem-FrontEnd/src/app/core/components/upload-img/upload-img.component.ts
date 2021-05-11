import { Component, OnInit, Input, EventEmitter, Output, Injectable } from '@angular/core';
import { ImageCroppedEvent, ImageTransform } from 'ngx-image-cropper';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { FileUpload } from '../../model/file-upload';
import { FileUploadService } from '../../services/file-upload.service';
import { CreatePostComponent } from 'src/app/shared/pages/create-post/create-post.component';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { UsersService } from 'src/app/user/services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload-img',
  templateUrl: './upload-img.component.html',
  styleUrls: ['./upload-img.component.css']
})


export class UploadImgComponent implements OnInit {
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  @Input() isPost: boolean;
  @Input() baseUrl: string

  x: number;
  y: number;
  isShow: boolean;
  selectedFileName: string = "";

  showProgressbar:boolean=false

  private userId: number

  title = 'angular-image-uploader';
  scale = 1;
  transform: ImageTransform = {};
  imageChangedEvent: any = '';
  croppedImage: any = '';
  selectedFiles: FileList;
  currentFileUpload: FileUpload;
  percentage: number;
  image: File

  showError:boolean=true
  @Output() show = new EventEmitter<boolean>();
  constructor(private router: Router,private _snackBar: MatSnackBar, private uploadService: FileUploadService, private authService: authentication, private userService: UsersService) { }

  ngOnInit(): void {
    this.selectedFileName = null;

    if (this.isPost) {
      this.x = 8
      this.y = 5
    }
    else {
      this.x = 4
      this.y = 4
    }
    this.show.emit(false)
    this.isShow = true

  }

  handleError(error)
  {
    console.log(error);
    console.log(error.status);
    
    if(error.status===500)
    {
      this.router.navigate(['internal-server-error'])
    }
    else
    {
      this.router.navigate(['error-page'])
    }
  }


  hide(): void {

    this.isShow = false
    this.show.emit(true)
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
    let user: Array<string>
    let imageName: string
       
    this.croppedImage = event.base64;
    //console.log(this.croppedImage);
    let randomString = this.randomString(8, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
    this.selectedFileName += randomString
    this.image = this.dataURLtoFile(this.croppedImage, this.selectedFileName);
    console.log(this.image);

  }

  randomString(length, chars) 
  {
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
    return result;
  }

  imageLoaded() {
    // show cropper
  }
  cropperReady() {
    // cropper ready
  }
  loadImageFailed() {
    // show message
  }


  zoomOut() {
    this.scale -= .1;
    this.transform = {
      ...this.transform,
      scale: this.scale
    };
  }

  zoomIn() {
    this.scale += .1;
    this.transform = {
      ...this.transform,
      scale: this.scale
    };
  }

  selectFile(event): void {
    this.showError=false
    this.selectedFiles = event.target.files;
    this.selectedFileName = this.selectedFiles.item(0).name
    console.log(this.selectedFiles.item(0).name);

  }

  upload(): void {
    
    this.showProgressbar=true
    const file = this.image;
    this.selectedFiles = undefined;

    this.currentFileUpload = new FileUpload(file);
    this.uploadService.pushFileToStorage(this.currentFileUpload, this.baseUrl).subscribe( 
      percentage => {
        this.percentage = Math.round(percentage);
        if (this.percentage === 100) {
         
          setTimeout(() => {this.showProgressbar=false
            this._snackBar.open('Image uploaded successfully..', 'close', {
              duration: 2000,
              horizontalPosition: this.horizontalPosition,
              verticalPosition: this.verticalPosition,
            });
             this.hide() }, 2000);
          // this.createPost.imageURL = localStorage.getItem("imageURL")
          // console.log(this.createPost.imageURL);

        }
      },error=>{
        this.handleError(error)
      }

    )

  }

  dataURLtoFile(dataurl, filename) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
  }
}
