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
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-schools',
  templateUrl: './edit-school.component.html',
  styleUrls: ['./edit-school.component.css']
})
export class EditSchoolComponent implements OnInit {

  stream:Array<string>
  showProgressbar: boolean = false


  schools:Array<School>=new Array()

  
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  

  Show:boolean=false;

  villageSelected:number;
  stateSelected:number;
  districtSelected:number;
  talukaSelected:number;

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  villages:Array<Village>

  status:Array<string>=["Good","Better","Under Construction"]

  school:School=new School()


  constructor(private route:ActivatedRoute,private router:Router,private addressService:AddressService, private dialog:MatDialog, private _snackBar: MatSnackBar, private schoolService:SchoolService) { 

    this.stream=[
      "Primary School",
      "Seconadary School",
      "Higher Secondary School",
    ]
    
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
    this.school.village=new Village()
    this.getSchool(this.route.snapshot.params['id'])
  }
  


  openEditSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  onSubmit()
  {
    console.log(this.school);
    
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
    this.addressService.getDistricts(event.target.value).subscribe(data=>{
    this.Show = false
    this.districts = data
    this.talukas = []
    this.villages = []
    })
  }

  getAllDistricts() 
  {
    this.addressService.getDistricts(7).subscribe(data=>{
    this.districts = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedDistrict(event)
  {
    this.districtSelected = event.target.value;
    this.addressService.getTalukas(event.target.value).subscribe(data=>{
    this.talukas = data
    this.villages = []
    })
  }

  getAllTalukas() 
  {
    this.addressService.getTalukas(141).subscribe(data=>{
    this.talukas = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedTaluka(event)
  {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if(event.target.value != 0)
    {
          this.addressService.getVillages(event.target.value).subscribe(data=>{
          this.villages = data
        })
    }
  }

  getAllVillages() 
  {
    this.addressService.getVillages(35).subscribe(data=>{
    this.villages = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedVillage(event)
  {
    this.villageSelected = event.target.value;
  }


 



  getSchool(schoolId)
  {
    console.log(schoolId);

    this.schoolService.getSchoolById(schoolId).subscribe(data=>{
      let date:string[] = data.startingDate.split("-")
      this.school.startingDate = date[2] + "-" + date[0] +"-" + date[1]
      this.school = data
      console.log(data);
    })
  }

  saveSchool()
  {
    console.log(this.school);
    this.showProgressbar=true
    console.log(this.school)
    this.addressService.getVillageByid(this.villageSelected).pipe(finalize(()=>{
      let startdate:String = this.school.startingDate
      let sdate:string[] = startdate.split("-")
      let startingdate = sdate[0] + "-" +  sdate[1] + "-" + sdate[2]
      this.school.startingDate = startingdate
  
      console.log(this.school)

      this.schoolService.editSchool(this.school.schoolId,this.school).subscribe(data=>{
        console.log(data)
        setTimeout(()=>{
          this.showProgressbar=false
          this.openEditSnackBar()
         this.router.navigate(['/admin/schools'])
        },2000)
      },error=>{
        this.handleError(error)
      })
    })).subscribe(data=>{
      this.school.village = data
    })
  }


  
}
