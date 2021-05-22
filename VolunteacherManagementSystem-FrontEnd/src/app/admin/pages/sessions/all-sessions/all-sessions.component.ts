
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Session } from 'src/app/core/model/session';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
@Component({
  selector: 'app-all-sessions',
  templateUrl: './all-sessions.component.html',
  styleUrls: ['./all-sessions.component.css']
})
export class AllSessionsComponent implements OnInit {

  
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  search:string=''
  sessions:Array<Session>=new Array()

  showSpinner:boolean=false
  noSessions:boolean=false
  sLength:number
 
  page:number=0
  totalSessionsPages:number
  previousDisabled:boolean = true
  nextDisabled:boolean = false
  showProgressbar: boolean = false

  constructor(private router:Router, private sessionService:SessionsService, private _snackBar: MatSnackBar, private dialog: MatDialog) {
   
   }

  ngOnInit(): void {
    this.page=0
    this.getAllSessions(this.page)
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

  blob:Blob = new Blob()
  download()
  {
    this.sessionService.downloadSessions().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Sessions.xlsx";
      link.click();
  })
  }

  getAllSessions(page:number)
  {
    this.showSpinner=true
      this.sessionService.getAllSessions(page).subscribe(data=>{
        this.sessions=data['content']
        this.totalSessionsPages = data['totalPages']
        if(this.totalSessionsPages == 1)
        {
          this.nextDisabled = true
        }
        this.showSpinner=false
        if (data != null) {
          this.sLength = this.sessions.length
          this.noSessions=false
        }
        //this.sLength=0
        if(this.sLength==0)
        {
          this.noSessions=true
        }
        console.log(this.sessions);
        
      },error=>{
      this.handleError(error)
    })
  }

  deleteSession(id:number)
  {
    this.showProgressbar=true
     this.sessionService.deleteSession(id).subscribe(data=>{
       console.log(data);  
       this.openDeleteSnackBar()  
       setTimeout(() => {
        this.getAllSessions(this.page)
        this.showProgressbar=false
       }, 2000);
     },error=>{
      this.handleError(error)
    })
  }

  delete(id:number)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteSession(id)
      }
    })
  }


  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  nextPage()
  {
    console.log("Hello");
    console.log(this.totalSessionsPages);
    
    if(this.page < this.totalSessionsPages - 1)
    {
      this.page +=1
      this.getPageableKids(this.page);
      this.previousDisabled = false
    }
    if(this.page == this.totalSessionsPages - 1)
    {
      this.nextDisabled = true
    }
  }

  previousPage()
  {
    if(this.page > -1)
    {
      this.page -=1
      this.getPageableKids(this.page);
      this.nextDisabled = false
    }
    if(this.page ==0){
      this.previousDisabled = true
    }
  }
  getPageableKids(page: number) {
    this.showSpinner=true
    this.sessionService.getAllSessions(page).subscribe(data=>{
      this.sessions=data['content']
      this.showSpinner=false
    })
  }

  trackById(index,s:Session)
  {
    return s.sessionId
  }
}
