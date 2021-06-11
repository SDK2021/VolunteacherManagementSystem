import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { AddressService } from 'src/app/shared/shared-services/address.service';

@Component({
  selector: 'app-edit-village',
  templateUrl: './edit-village.component.html',
  styleUrls: ['./edit-village.component.css']
})
export class EditVillageComponent implements OnInit {

 

  showProgressbar:boolean=false

  
  options: FormGroup;
  colorControl = new FormControl('primary');
  fontSizeControl = new FormControl(16, Validators.min(10));

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  search:string=''
  disabled:boolean=null

  stateSelected:number;_
  districtSelected:number;
  talukaSelected:number;
 

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  village:Village = new Village()
  Show:boolean=true
  loaddata:boolean = false
  showSpinner: boolean = true


  constructor(private route:ActivatedRoute,private router:Router,fb: FormBuilder,private projectService:ProjectsService,private addressService:AddressService, private dialog:MatDialog , private _snackBar: MatSnackBar) { 
    this.options = fb.group({
      color: this.colorControl,
      fontSize: this.fontSizeControl,
    });
  
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
      
    
    this.getVillage(this.route.snapshot.params['id'])
    this.getAllCountries();
    this.getAllStates();

  }

  saveVillage()
  {
    this.disabled=true
    if(this.talukaSelected != 0)
    {
      this.showProgressbar=true
      console.log("Hello");
      
      this.addressService.getTalukaById(this.talukaSelected).pipe(finalize(()=>{
        this.addressService.saveVillage(this.village.villageId,this.village).subscribe(data=>{
          this.showProgressbar=false
          this.openSnackBar()
          this.router.navigate(['/admin/villages'])
          console.log(data);
          this.disabled=false
        })
      })).subscribe(data=>{
        this.village.taluka = data
      },error=>{
        this.handleError(error)
      })
      console.log(this.village);
      
    }
  }
 

  openDialog()
  {
    this.dialog.open(DialogBoxComponent)
    this.openSnackBar()
  }
  openSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  onSubmit(from:NgForm)
  {
    console.log(this.village)
  }

  trackById(index:number,village:Village)
  {
    return village.villageId
  }

  

  addVillage(form:NgForm)
  {
    this.showProgressbar = true
    this.addressService.getTalukaById(this.talukaSelected).pipe(finalize(()=>{
      this.projectService.addVillage(this.village).subscribe(data=>{
        console.log(data)
        this.showProgressbar = false
        this.openSnackBar()
        form.reset()
        setTimeout(()=>{
         this.router.navigate(['/admin/villages'])
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
    if(event.target.value > 0)
    {
      this.addressService.getStates(event.target.value).subscribe(data=>{
        this.states = data
      })
    }
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
    if(this.stateSelected > 0)
    {
      this.addressService.getDistricts(event.target.value).subscribe(data=>{
      this.Show = false
      this.districts = data
      this.talukas = []
      })
   }
   else{
     this.districts=[]
    this.talukas = []
   }
   this.talukaSelected = 0
   this.districtSelected = 0
  }

 

  selectedDistrict(event)
  {
    this.districtSelected = event.target.value;
    if(this.districtSelected > 0)
    {
      this.addressService.getTalukas(event.target.value).subscribe(data=>{
      this.talukas = data
      })
    }
  }

 

  selectedTaluka(event)
  {
    this.talukaSelected = event.target.value;
  }

  getVillage(id:number)
  {
      this.addressService.getVillageByid(id).pipe(finalize(()=>{
          this.addressService.getTalukas(this.districtSelected).pipe(finalize(()=>{
              this.addressService.getDistricts(this.stateSelected).subscribe(data=>{
              this.districts = data;
              },error=>{
                this.handleError(error)
              },
              ()=>{
                this.loaddata = true
                this.showSpinner = false
              })
            
          })).subscribe(data=>{
          this.talukas = data
          },error=>{
            this.handleError(error)
          })
      })).subscribe(data=>{
        this.village=data
        this.stateSelected = this.village.taluka.district.state.stateId
        this.districtSelected = this.village.taluka.district.districtId
        this.talukaSelected = this.village.taluka.talukaId
      },error=>{
        this.handleError(error)
      })
  }
}
