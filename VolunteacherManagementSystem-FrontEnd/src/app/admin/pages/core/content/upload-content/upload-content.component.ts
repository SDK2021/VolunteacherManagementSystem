import { Component, OnInit } from '@angular/core';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { KidsService } from 'src/app/kids/shared-services/kids.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Content } from 'src/app/core/model/content';
import { FileUpload } from 'src/app/core/model/file-upload';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { finalize } from 'rxjs/operators';
import { Router } from '@angular/router';
@Component({
  selector: 'app-upload-content',
  templateUrl: './upload-content.component.html',
  styleUrls: ['./upload-content.component.css']
})
export class UploadContentComponent implements OnInit {
  isShow:boolean=false
  groups : Array<KidsGroup>

  contents:Content[]=new Array()

  pdfSource:String=""

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showProgressbar:boolean=false

  selectedFiles: FileList;
  selectedFileName:string;
  currentFileUpload:FileUpload
  content:Content
  groupId:number
  shows:boolean = false

  isFileUploaded:boolean=false

  fileUrl:string=null

  percentage:number=0


  constructor( private router: Router,private kidsService:KidsService, private sessionService:SessionsService, private uploadService: FileUploadService,private _sharedservice:KidsService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getkidsgroup()
    this.getContent(1)
    this.getAllContents()

    
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

  show()
  {
    this.isShow=!this.isShow
  }

 
  getkidsgroup()
  {
    this._sharedservice.getkidsgrouplist().subscribe(data =>{
      this.groups=data; 
    },error=>{
      this.handleError(error)
    });
    
  }

  openSnackBar() {
    this._snackBar.open('Uploaded successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  selectedGroup(event)
  {
    console.log(event.target.value);
    this.groupId = event.target.value
  }

  
  groupSelected(event)
  {
    this.groupId = event.target.value
    this.getContent(this.groupId)
  }


  selectedFile(event): void {
    this.selectedFiles = event.target.files;
    this.selectedFileName = this.selectedFiles.item(0).name
  }

  uploadContent()
  {
    this.showProgressbar=true
     this.currentFileUpload = new FileUpload( this.selectedFiles.item(0));
     this.uploadService.pushFileToStorage(this.currentFileUpload, "/vms/content").subscribe( 
        percentage => { 
          
          if (percentage == 100) {
            this.uploadService.imageUrl.subscribe(data => {
              this.fileUrl = data
              //alert("in component..." + this.imageURL)
              if (this.fileUrl != null && this.isFileUploaded== false) {
                setTimeout(() => {
                  this.showProgressbar=false
                  this._snackBar.open('File uploaded successfully..', 'close', {
                  duration: 2000,
                  horizontalPosition: this.horizontalPosition,
                  verticalPosition: this.verticalPosition,
                  
                }); 
                  this.addContent();
                  
                  }, 2000);
                 this.isFileUploaded = true
              }
            })
          }
           
      },error=>{
        this.handleError(error)
      }
 
     )
  }

  addContent()
  {
    this.content = new Content()
    this.content.contentData=this.fileUrl
    this.kidsService.kidGroupById(this.groupId).pipe(finalize(()=>{
      this.sessionService.addContent(this.content).subscribe(data=>{
        setTimeout(() => {
          this.getAllContents()
        }, 1000);
       
      },error=>{
        this.handleError(error)
      })
    })).subscribe(data=>{
      this.content.group = data
    })
  }

  
  getContent(groupId:number)
  {
      this.shows = true
      this.sessionService.getContentByGroup(groupId).subscribe(data=>{
      this.pdfSource = data.contentData
      },error=>{
        this.handleError(error)
      })
  }

  getAllContents()
  {
    this.sessionService.getAllContents().subscribe(data=>{
      this.contents=data 
      },error=>{
        this.handleError(error)
      })
  }
}
