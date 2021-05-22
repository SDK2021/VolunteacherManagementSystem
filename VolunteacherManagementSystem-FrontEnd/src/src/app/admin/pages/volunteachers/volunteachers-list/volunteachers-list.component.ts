import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { VolunteacherPipe } from 'src/app/admin/filters/volunteacher.pipe';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { Volunteacher } from 'src/app/core/model/volunteacher';

@Component({
  selector: 'app-volunteachers-list',
  templateUrl: './volunteachers-list.component.html',
  styleUrls: ['./volunteachers-list.component.css']
})
export class VolunteachersListComponent implements OnInit {


  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  showProgressbar: boolean = false
  showSpinner: boolean = false

  page:number=0
  vLength: number
  totalVTPages:number

  volunteachers: Array<Volunteacher> = new Array()

  constructor(private router: Router, private dialog: MatDialog, private _snackBar: MatSnackBar, private sharedService: VolunteachersService) {
   
    
  }

  ngOnInit(): void {
    this.page=0
    this.getAllVoluntecahers(this.page)
  }

  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else {
      this.router.navigate(['error-page'])
    }
  }

  
  blob:Blob = new Blob()
  download()
  {
    this.sharedService.downloadVolunteachers().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Volunteachers.xlsx";
      link.click();
  })
  }

  search: string = ''
  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getAllVoluntecahers(page:number) {

    this.showSpinner=true
    this.sharedService.getAllVolunteachers(page).subscribe(data => {
      this.volunteachers = data['content']
      this.totalVTPages = data['totalPages']
      console.log(this.volunteachers);
      this.vLength=this.volunteachers.length
      this.showSpinner=false
    }, error => {
      this.handleError(error)
    })
  }

  delete(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.deleteVolunteacher(id)
      }
    })

  }
  trackById(index, v: Volunteacher) {
    return v.volunteacherId
  }

  deleteVolunteacher(id: number) {
    this.showProgressbar = true
    this.sharedService.deleteVolunteacher(id).subscribe(data => {
      console.log(data);
      this.openSnackBar()
      setTimeout(() => {
        this.getAllVoluntecahers(this.page)
        this.showProgressbar = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })
  }
  

   
  onScroll()
  {
    if(this.page < this.totalVTPages - 1)
    {
      this.page +=1
      this.getPageableVolunteachers(this.page);
    }
  }
  getPageableVolunteachers(page: number) {
    this.sharedService.getAllVolunteachers(page).subscribe(data =>{
      data['content'].forEach(vt => {
        this.volunteachers.push(vt)
        console.log(this.volunteachers);
        
      });
    })
  }
}


