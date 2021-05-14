import { Component, Injectable, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Kid } from 'src/app/core/model/kid';
import { KidService } from 'src/app/admin/shared-services/kid.service';
import { Router } from '@angular/router';
import { Village } from 'src/app/core/model/village';
import { Area } from 'src/app/core/model/area';
@Component({
  selector: 'app-kids-table',
  templateUrl: './kids-table.component.html',
  styleUrls: ['./kids-table.component.css']
})



export class KidsTableComponent implements OnInit {
  
  //remove this
  kids:Array<Kid>=new Array()
  kLength:number
  search:string=''
  public deletesuccess:boolean = false

  showProgressbar:boolean=false
  showSpinner:boolean=false
  noKids:boolean=false

  page:number=0
  totalKidPages:number
  previousDisabled:boolean = true
  nextDisabled:boolean = false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private router:Router,private dialog:MatDialog, private _snackBar: MatSnackBar, private kidService:KidService) {
    
   }
  

  ngOnInit(): void {
    this.page=0
    this.kids['village']=new Village()
    this.kids['area']=new Area()
    
    this.getAllKids(this.page)
    this.deletesuccess = false
    // console.log("Hello");
    
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

  deleteKid(id:number)
  {
    this.showProgressbar=true
     this.kidService.deleteKid(id).subscribe(data=>{
       console.log(data);  
       this.openSnackBar()  
       setTimeout(() => {
        this.getAllKids(this.page)
        this.showProgressbar=false
       }, 2000);
     },error=>{
        this.showProgressbar=false
        console.log(error);
        this.handleError(error)
     
    })
  }

  delete(id:number)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteKid(id)
      }
    })
    
  }

  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getAllKids(page:number)
  {
      this.showSpinner=true
      this.kidService.getAllKids(page).subscribe(data=>{
        this.kids=data['content']
        this.totalKidPages = data['totalPages']
        if(this.totalKidPages == 1)
        {
          this.nextDisabled = true
        }
        this.showSpinner=false
        if (data != null) {
          this.kLength = this.kids.length
          this.noKids=false
        }
        //this.kLength=0
        if(this.kLength==0)
        {
          this.noKids=true
        }
        console.log(this.kids);
        
      },error=>{
        this.handleError(error)
      })
  }

  trackById(index:number,kid:Kid)
  {
    return kid.kidId
  }


  blob:Blob = new Blob()
  download()
  {
    this.kidService.downloadKids().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Kids.xlsx";
      link.click();
  })
  }
  
   
  nextPage()
  {
    console.log("Hello");
    console.log(this.totalKidPages);
    
    if(this.page < this.totalKidPages - 1)
    {
      this.page +=1
      this.getPageableKids(this.page);
      this.previousDisabled = false
    }
    if(this.page == this.totalKidPages - 1)
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
    this.kidService.getAllKids(page).subscribe(data=>{
      this.kids = data['content']
      this.showSpinner=false
    })
  }
}
