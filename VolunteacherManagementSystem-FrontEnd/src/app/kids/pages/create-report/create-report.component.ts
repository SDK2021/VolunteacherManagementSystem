import { Component, OnInit } from '@angular/core';
import { FormBuilder} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Kidsreport } from 'src/app/core/model/kidsreport';
import { Kid } from 'src/app/core/model/kid';
import { KidsService } from '../../shared-services/kids.service';
import { Area } from 'src/app/core/model/area';
import { finalize } from 'rxjs/operators';


@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.css']
})
export class CreateReportComponent implements OnInit {

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  standards:Array<number>=[1,2,3,4,5,6,7,8,9,10,11,12]
  kid:Kid=new Kid()
  kidId:number=null
  report:Kidsreport=new Kidsreport()

  showProgressbar:boolean=false

  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }



  constructor(private route:ActivatedRoute, private fb: FormBuilder,private _auth:authentication,private router:Router,private _snackBar: MatSnackBar,private kidService:KidsService) {}

  ngOnInit() {
    //this.kid = new Kid()
    this.kid.area=new Area()
    this.kidId = this.route.snapshot.params['id']
    this.getKidById(this.kidId);
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

  getKidById(kidId: number) {
    this.kidService.kidById(kidId).subscribe(data=>{
      this.kid = data
      this.kid=this.calculateAge(data)
      console.log(this.kid);
      
    },error=>{
      this.handleError(error)
    })
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    
    console.log(this.report)
    this.createReport()
  }

  openSnackBar() {
    this._snackBar.open('Report created successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  gujaratiError:boolean=false
  englishError:boolean=false
  mathsError:boolean=false

  checkGujarati(marks:number)
  {
    if(marks>100)
    {
        this.gujaratiError=true
    }
    else
    {
      this.gujaratiError=false
    }
  }

  checkEnglish(marks:number)
  {
    if(marks>100)
    {
      this.englishError=true
    }
    else
    {
      this.englishError=false
    }
  }

  checkMaths(marks:number)
  {
    if(marks>100)
    {
      this.mathsError=true
    }
    else
    {
      this.mathsError=false
    }
  }

  createReport() {

    this.showProgressbar=true
    let kidId:number;
     kidId = this.route.snapshot.params['id']
      this.kidService.kidById(kidId).pipe(finalize(()=>{
        this.kidService.addKidReport(this.report).subscribe(data=>{
          console.log(data)
          this.showProgressbar=false
          this.openSnackBar()
          this.router.navigate(['/user/kids/create-report/kids-list'])
        },error=>{
          console.log(error)
        })
      })).subscribe(data=>{
        this.report.kid = data
      })
    
  }  

  calculateAge(kid:Kid):Kid
  {
     
        let currentDate=new Date()
        let bDate=new Date(kid.dob)
        
        let diffInSec= Math.abs(currentDate.getTime()-bDate.getTime())
        console.log(diffInSec);
        
        kid.age=(diffInSec/(1000 * 3600 * 24)/365)+1
        let array:Array<string>=kid.age.toString().split('.')
        kid.age=Number.parseInt(array[0])
        console.log(kid.age)
        
     return kid
     
  }
}
