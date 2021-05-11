import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Payment } from 'src/app/core/model/payment';
import { DonationService } from 'src/app/admin/shared-services/donation.service';
import { Router } from '@angular/router';
import { Usertype } from 'src/app/core/model/usertype';
import { Donor } from 'src/app/core/model/donor';
@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {
  payments:Array<Payment> = new Array()
  search:string=''
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showProgressbar: boolean = false
  showSpinner:boolean=false
  noDonation:boolean=false
  pLength:number

  page:number=0
  totalDonersPages:number
  previousDisabled:boolean = true
  nextDisabled:boolean = false
  
  constructor(private router:Router,private dialog:MatDialog, private _snackBar: MatSnackBar, private donationService:DonationService) { 
   
  }
  
  ngOnInit(): void {
    this.payments['donor']=new Donor()
    this.payments['donor']['userType']=new Usertype()
    this.getDonations(this.page)
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
  openDialog()
  {
    this.dialog.open(DialogBoxComponent)
    this.openSnackBar()
  }

  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getDonations(page:number)
  {
    this.showSpinner=true
     this.donationService.getDonations(page).subscribe(data=>{
       this.payments=data['content']
       this.totalDonersPages = data['totalPages']
       if(this.totalDonersPages == 1)
       {
         this.nextDisabled = true
       }
       this.showSpinner=false
       if (data != null) {
        this.pLength = this.payments.length
        this.noDonation=false
      }
      //this.pLength= 0
      if(this.pLength==0)
      {
        this.noDonation=true
      }
       console.log(this.payments);      
     },error=>{
      this.handleError(error)
    })
  }

  deleteDonation(id:number)
  {
    this.showProgressbar=true
    
     this.donationService.deletePayment(id).subscribe(data=>{
       console.log(data);  
       this.openSnackBar()  
       setTimeout(() => {
        this.getDonations(this.page)
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
        this.deleteDonation(id)
      }
    })
    
  }
     
  nextPage()
  {
    console.log("Hello");
    console.log(this.totalDonersPages);
    
    if(this.page < this.totalDonersPages - 1)
    {
      this.page +=1
      this.getPageableDonors(this.page);
      this.previousDisabled = false
    }
    if(this.page == this.totalDonersPages - 1)
    {
      this.nextDisabled = true
    }
  }

  previousPage()
  {
    if(this.page > -1)
    {
      this.page -=1
      this.getPageableDonors(this.page);
      this.nextDisabled = false
    }
    if(this.page ==0){
      this.previousDisabled = true
    }
  }
  getPageableDonors(page: number) {
    this.showSpinner=true
    this.donationService.getDonations(page).subscribe(data=>{
      this.payments=data['content']
      this.showSpinner=false
    })
  }

}
