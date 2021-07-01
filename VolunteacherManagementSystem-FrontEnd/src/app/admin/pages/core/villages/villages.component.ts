import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Village } from 'src/app/core/model/village';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { Country } from 'src/app/core/model/country';
import { State } from 'src/app/core/model/state';
import { District } from 'src/app/core/model/district';
import { Taluka } from 'src/app/core/model/taluka';
import { finalize } from 'rxjs/operators';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { Router } from '@angular/router';
import { KidService } from 'src/app/admin/shared-services/kid.service';
@Component({
  selector: 'app-villages',
  templateUrl: './villages.component.html',
  styleUrls: ['./villages.component.css']
})
export class VillagesComponent implements OnInit {
  
  tab1:boolean
  tab2:boolean=true

  isShow:boolean=false;

  disabled:boolean=null

  showProgressbar:boolean=false
  showSpinner:boolean=false
  noVillages:boolean=false
  vLength:number;
  villages:Array<Village>=new Array()
  
  options: FormGroup;
  colorControl = new FormControl('primary');
  fontSizeControl = new FormControl(16, Validators.min(10));

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  search:string=''

  stateSelected:number;
  districtSelected:number;
  talukaSelected:number;
 

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  village:Village = new Village()
  Show:boolean=true


  constructor(private kidService:KidService, private router:Router,fb: FormBuilder,private projectService:ProjectsService,private addressService:AddressService, private dialog:MatDialog , private _snackBar: MatSnackBar) { 
    this.options = fb.group({
      color: this.colorControl,
      fontSize: this.fontSizeControl,
    });
    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.stateSelected = 8
    this.districtSelected = 141
    this.talukaSelected = 35

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

  getFontSize() {
    return Math.max(10, this.fontSizeControl.value);
  }

  ngOnInit(): void {
    this.getAllVillages()
  }

  show()
  {
    this.isShow=!this.isShow;
  }

  showTab1(show:boolean)
  {
    console.log("tab1"+show)
    this.tab1=show
  }
  showTab2(show:boolean)
  {
    console.log("tab2"+show)
    this.tab2=show
  }

  openDialog()
  {
    this.dialog.open(DialogBoxComponent)
    this.openSnackBar()
  }
  openSnackBar() {
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

  onSubmit(from:NgForm)
  {
    console.log(this.village)
  }

  getAllVillages()
  {
    this.showSpinner=true
    this.addressService.getAllVillages().pipe(finalize(()=>{
       this.kidService.getAllKidslist().subscribe(data => {
        let flag = 0
        for (let village of this.villages) {
          flag = 0
          for (let kid of data) {
            if (kid.area.village.villageId === village.villageId) {
              flag = 1
              console.log("Area:" + village.villageId);
              break
            }
          }
          if (flag == 1) {
            village.isDelete = true
          }
          else {
            village.isDelete = false
          }
        }
        console.log(this.village);
      
      })
    })).subscribe(data=>{
      this.villages=data
      this.showSpinner=false
      if (data != null) {
        this.vLength = this.villages.length
        this.noVillages=false
      }
      //this.vLength=0
      if(this.vLength==0)
      {
        this.noVillages=true
      }
      console.log(this.villages);
      
    },error=>{
      this.handleError(error)
    })
  }

  trackById(index:number,village:Village)
  {
    return village.villageId
  }

  

  addVillage(form:NgForm)
  {
    this.disabled=true
    this.showProgressbar = true
    this.addressService.getTalukaById(this.talukaSelected).pipe(finalize(()=>{
      this.projectService.addVillage(this.village).subscribe(data=>{
        console.log(data)
       
        form.reset()
        setTimeout(()=>{ 
          this.getAllVillages()
          this.showProgressbar = false
          this.openSnackBar()
          this.showTab1(false)
          this.showTab2(true)
          this.disabled=false
        },2000)
       
      },error=>{
        this.handleError(error)
      })
    })).subscribe(data=>{
      this.village.taluka = data
    })
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
    this.addressService.getDistricts(7).subscribe(data=>{
    this.districts = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedDistrict(event)
  {    
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
    if(event.target.value > 0)
    {
          this.addressService.getVillages(event.target.value).subscribe(data=>{
          this.villages = data
        })
    }
  }

  deleteVillage(id: number) {
    this.disabled = true
    this.showProgressbar = true
    this.addressService.deleteVillage(id).subscribe(data => {
      console.log(data);
      setTimeout(() => {
        this.getAllVillages()
        this.showProgressbar = false
        this.openDeleteSnackBar()
        this.disabled = false
      }, 2000);
    }, error => {
      this.handleError(error)
    })
  }

  delete(id: number) {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data => {
      console.log(data.delete)
      if (data.delete) {
        this.deleteVillage(id)
      }
    })

  }

}
