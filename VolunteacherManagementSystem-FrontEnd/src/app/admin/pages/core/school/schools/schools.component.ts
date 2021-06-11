import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { School } from 'src/app/core/model/school';
import { SchoolService } from 'src/app/admin/shared-services/school.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { finalize } from 'rxjs/operators';
import { Country } from 'src/app/core/model/country';
import { State } from 'src/app/core/model/state';
import { District } from 'src/app/core/model/district';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-schools',
  templateUrl: './schools.component.html',
  styleUrls: ['./schools.component.css']
})
export class SchoolsComponent implements OnInit {

  stream:Array<string>
  showProgressbar: boolean = false


  schools:Array<School>=new Array()

  
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  search:string=''

  tab1:boolean
  tab2:boolean=true

  isShow:boolean=false;
  Show:boolean=true;

  disabled:boolean=null

  stateTouched:boolean = false
  districtTouched:boolean = false
  talukaTouched:boolean = false
  villageTouched:boolean = false
  streamTouched:boolean = false
  statusTouched:boolean = false

  villageSelected:number;
  stateSelected:number;
  districtSelected:number;
  talukaSelected:number;

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  villages:Array<Village>

  
  showSpinner:boolean=false
  noSchools:boolean=false
  sLength:number

  totalSchoolPages:number
  previousDisabled:boolean = true
  nextDisabled:boolean = false

  
  status:Array<string>=["Good","Better","Under Construction"]

  page:number=0
  school:School=new School()
 

  constructor(private router:Router,private addressService:AddressService, private dialog:MatDialog, private _snackBar: MatSnackBar, private schoolService:SchoolService) { 

    this.stream=[
      "Primary School",
      "Seconadary School",
      "Higher Secondary School",
    ]
    
    this.villageSelected = 0
    this.talukaSelected = 35
    this.stateSelected = 7
    this.districtSelected = 141

    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.getAllVillages();
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

  ngOnInit(): void {
    this.page=0
    this.getAllSchools(this.page)
    
  }
  
  blob:Blob = new Blob()
  download()
  {
    this.schoolService.downloadSchools().subscribe((data) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "Schools.xlsx";
      link.click();
  })
  }

  show()
  {
    this.isShow=!this.isShow;
  }


  showTab2(show:boolean)
  {
    console.log("tab2"+show)
    this.tab2=show
    this.tab1=false
    
  }

  delete(id:number)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteSchool(id)
      }
    })
    
  }
  openAddSnackBar() {
    this._snackBar.open('Added successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

 
  onSubmit()
  {
    console.log(this.school); 
  }

   
  addSchool(form)
  {
    this.disabled=true
    this.showProgressbar=true
    this.school.stream = form.stream
    this.school.status = form.status
    console.log(form.status);
    
    console.log(this.school)
    this.addressService.getVillageByid(this.villageSelected).pipe(finalize(()=>{
      let startdate:String = this.school.startingDate
      let sdate:string[] = startdate.split("-")
      let startingdate = sdate[1] + "-" +  sdate[2] + "-" + sdate[0]
      this.school.startingDate = startingdate
  
      console.log(this.school)

      this.schoolService.addSchool(this.school).subscribe(data=>{
        console.log(data)
       
        
        setTimeout(()=>{
          this.getAllSchools(this.page)
          this.showProgressbar=false
          this.openAddSnackBar()
          this.showTab2(true)
          this.showTab1(false)
          this.disabled=false
          this.school=null
        },2000)
       
      },error=>{
        this.handleError(error)
      })
    })).subscribe(data=>{
      this.school.village = data
    })
  }
  showTab1(show:boolean)
  {
    console.log("tab1"+show)
    this.tab1=show
  }

  getAllCountries()
  {
    this.addressService.getCountries().subscribe(data=>{
      this.countries = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedCountry(event)
  {
    this.addressService.getStates(event.target.value).subscribe(data=>{
      this.states = data
    })
  }

  getAllStates() 
  {
    this.addressService.getStates(8).subscribe(data=>{
      this.states = data;
    },error=>{
      this.handleError(error)
    })
  } 

  selectedState(event)
  {
    this.stateSelected = event.target.value;
    this.Show = false
    this.villageSelected = 0
    this.talukaSelected = 0
    this.districtSelected = 0
    this.talukas = []
    this.villages = []
    if(event.target.value > 0)
    {
      this.addressService.getDistricts(event.target.value).subscribe(data=>{
      this.districts = data
      })
    }
  }

  getAllDistricts() 
  {
    this.addressService.getDistricts(this.stateSelected).subscribe(data=>{
    this.districts = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedDistrict(event)
  {
    this.villageSelected = 0
    this.talukaSelected = 0
    if(event.target.value > 0)
    {
      this.districtSelected = event.target.value;
      this.addressService.getTalukas(event.target.value).subscribe(data=>{
      this.talukas = data
      this.villages = []
      })
    }
    else{
      this.talukas = []
      this.villages = []
      this.districtSelected = 0
    }
  }
  getAllTalukas() 
  {
    this.addressService.getTalukas(this.districtSelected).subscribe(data=>{
    this.talukas = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedTaluka(event)
  {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if(event.target.value > 0)
    {
          this.addressService.getVillages(event.target.value).subscribe(data=>{
          this.villages = data
        })
    }
  }

  getAllVillages() 
  {
    this.addressService.getVillages(this.talukaSelected).subscribe(data=>{
    this.villages = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedVillage(event)
  {
    this.villageSelected = event.target.value;
  }


  getAllSchools(page:number)
  { 
    this.showSpinner=true
    this.schoolService.getAllSchool(page).subscribe(data=>{
      this.schools=data['content']
      this.totalSchoolPages = data['totalPages']
      if(this.totalSchoolPages == 1)
      {
        this.nextDisabled = true
      }
      this.showSpinner=false
      if (data != null) {
        this.sLength = this.schools.length
        this.noSchools=false
      }
      //this.sLength=0
      if(this.sLength==0)
      {
        this.noSchools=true
      }
      console.log(this.schools);
    },error=>{
      this.handleError(error)
    })
  }



  trackById(index:number,school:School)
  {
      return school.schoolId
  }
 
  deleteSchool(id:number)
  {
    this.showProgressbar=true
     this.schoolService.deleteSchool(id).subscribe(data=>{
       console.log(data);  
       this.openDeleteSnackBar()  
       setTimeout(() => {
        this.getAllSchools(this.page)
        this.showProgressbar=false
       }, 2000);
     },error=>{
      this.handleError(error)
    })
  }

  nextPage()
  {
    console.log("Hello");
    console.log(this.totalSchoolPages);
    
    if(this.page < this.totalSchoolPages - 1)
    {
      this.page +=1
      this.getPageableSchools(this.page);
      this.previousDisabled = false
    }
    if(this.page == this.totalSchoolPages - 1)
    {
      this.nextDisabled = true
    }
  }

  previousPage()
  {
    if(this.page > -1)
    {
      this.page -=1
      this.getPageableSchools(this.page);
      this.nextDisabled = false
    }
    if(this.page ==0){
      this.previousDisabled = true
    }
  }
  getPageableSchools(page: number) {
    this.schoolService.getAllSchool(page).subscribe(data=>{
      this.schools=data['content']
    })
  }

  
}
