import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Route } from '@angular/compiler/src/core';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Sessionreport } from 'src/app/core/model/sessionreport';
@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  search:string=''
  feedbacks:Sessionreport[]=new Array()
  
  showSpinner:boolean=false
  noFeedbacks:boolean=false
  fLength:number
  showProgressbar: boolean = false
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  page:number=0

  constructor(private router:Router,private route:ActivatedRoute,private sessionService:SessionsService,private dialog:MatDialog , private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.page=0
    this.getAllFeedbacks(this.page,this.route.snapshot.params['id'])
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
 
  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getAllFeedbacks(page:number,id:number)
  {
    this.showSpinner=true
    this.sessionService.getAllSessionReportsBySession(page,id).subscribe(data=>{
        this.feedbacks=data['content']
        this.showSpinner=false
        if (data != null) {
          this.fLength = this.feedbacks.length
          this.noFeedbacks=false
        }
        //this.pLength=0
        if(this.fLength==0)
        {
          this.noFeedbacks=true
        }
    }, error => {
      this.handleError(error)
    })
  }

  deleteFeedback(id:number)
  {
    this.showProgressbar=true
    
     this.sessionService.deleteSessionReport(id).subscribe(data=>{
       console.log(data);  
       this.openSnackBar()  
       setTimeout(() => {
        this.getAllFeedbacks(this.page,this.route.snapshot.params['id'])
        this.showProgressbar=false
       }, 2000);
     },error=>{
     this.handleError(error)
    })
  }

  delete(id:number,image:string)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteFeedback(id)
      }
    })
    
  }

  onScroll() {
    this.page += 1
    this.getPageableEvent(this.page,this.route.snapshot.params['id']);
  }
  getPageableEvent(page: number,id:number) {
    this.sessionService.getAllSessionReportsBySession(page,id).subscribe(data=>{
      data['content'].forEach(feedback => {
        this.feedbacks.push(feedback)
      });
    })
  }
}
