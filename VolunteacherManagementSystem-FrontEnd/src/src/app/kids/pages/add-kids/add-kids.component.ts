import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Kid } from 'src/app/core/model/kid';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';

import { KidsService } from '../../shared-services/kids.service';
import { Area } from 'src/app/core/model/area';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { Country } from 'src/app/core/model/country';
import { State } from 'src/app/core/model/state';
import { District } from 'src/app/core/model/district';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { finalize } from 'rxjs/operators';
import { FileUploadService } from 'src/app/core/services/file-upload.service';


@Component({
  selector: 'app-add-kids',
  templateUrl: './add-kids.component.html',
  styleUrls: ['./add-kids.component.css']
})

@Injectable({
  providedIn:'root'
})
export class AddKidsComponent implements OnInit {

  baseUrl:string="/vms/kids/profiles"
  imageURL:string

  heading:string=''
  saveBtn:boolean=false
  submitBtn=false
  isEdit=false
  showProgressbar:boolean=false
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  levels:Array<string>

  standards:Array<number>=[1,2,3,4,5,6,7,8,9,10,11,12]
  area: Array<Area>
  isShow:boolean=true
  showForm:boolean=false
  
  isKidAdded:boolean=false

  countries:Array<Country>
  states:Array<State>
  districts:Array<District>
  talukas:Array<Taluka>
  villages:Array<Village>
  areas:Array<Area>
  groups:Array<KidsGroup>

  stateSelected:number;
  districtSelected:number;
  talukaSelected:number;
  villageSelected:number;
  areaSelecetd:number;
  groupSelected:number;

  kid:Kid=new Kid();

  namePattern:string="[a-zA-Z ]{3,20}"


  constructor(private fileService:FileUploadService,private route:ActivatedRoute,private router:Router,private _snackBar: MatSnackBar,private kidsService:KidsService,private addressService : AddressService) {}

  ngOnInit() {
   
    this.imageURL = localStorage.getItem("imageURL")
   
    if(this.imageURL!=null)
    {
      this.fileService.delete(this.imageURL)
      console.log("deleted");
      localStorage.removeItem("imageURL")
      
    }

    this.getkidsgroup()
    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.getAllVillages();

    this.stateSelected = 0;
    this.districtSelected = 0;
    this.talukaSelected = 0;
    this.villageSelected = 0;
    this.areaSelecetd = 0;
    this.groupSelected = 0;
    

    this.levels=[
      "AatmaSiksha","AatmaShodh","AatmaVishesh"
    ]

    // if(this.router.url.endsWith('edit'))
    // {
    //   //get Id form param , get kid and set values
    //   this.heading='Edit'
    //   this.saveBtn=true
    //   this.submitBtn=false
    //   this.isEdit=true
    //   this.showForm=true
    //   this.getKidById(this.route.snapshot.params['id'])
    // }
    // else
    // {
    //   this.heading='Add New'
    //   this.saveBtn=false
    //   this.submitBtn=true
    //   this.isEdit=false
      
    // }
  }

  ngOnDestroy()
  {
    if(this.isKidAdded==false)
    {
      if(this.imageURL!=null)
      {
        this.fileService.delete(this.imageURL)
        localStorage.removeItem("imageURL")
      }
       
      console.log("Bye Bye");
      
    }
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

 
  show(isShow):void
  {
    this.showForm=isShow
    this.imageURL = localStorage.getItem("imageURL")
    
  }
  
  addKid()
  {
    this.showProgressbar=true
    console.log(this.kid);
    let photoUrl = this.imageURL
    this.kid.photo = photoUrl;
    let dob:String = this.kid.dob
    let dobdate:String[] = dob.split("-")
    let dateofbirth = dobdate[1] + "-" +  dobdate[2] + "-" + dobdate[0]
    this.kid.dob = dateofbirth
    console.log(this.kid.dob);
    this.kidsService.getAreaById(this.areaSelecetd).subscribe(areadata=>{
      console.log(areadata)
      this.kid.area = areadata
      this.kidsService.kidGroupById(this.groupSelected).pipe(finalize(()=>{
        this.kidsService.villageById(areadata.village.villageId).pipe(finalize(()=>{
          this.kidsService.addKid(this.kid).subscribe(data=>{
            console.log(data)
            this.isKidAdded=true
            this.showProgressbar=false
            localStorage.removeItem("imageURL")
            this.openSnackBar();
          },error=>{
            this.handleError(error)
          })
        })).subscribe(data=>{
          this.kid.village = data
        })
      })).subscribe(data=>{
        this.kid.group = data
      })    
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
    },error=>{
      this.handleError(error)
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
    this.isShow = false
    this.districts = data
    this.talukas = []
    this.villages = []
    this.areas = []
    },error=>{
      this.handleError(error)
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
    this.areas = []
    },error=>{
      this.handleError(error)
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
          this.areas = []
        },error=>{
          this.handleError(error)
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
    console.log(event.target.value);
    if(event.target.value!=0)
    {
        this.addressService.getAreas(event.target.value).subscribe(data=>{
        this.areas = data
      },error=>{
        this.handleError(error)
      })
    }
    else
    {
      this.areas = []
    }
  }

  selectedArea(event)
  {
    this.areaSelecetd = event.target.value;
  }

  getkidsgroup()
  {
    this.kidsService.getkidsgrouplist().subscribe(data =>{
      this.groups=data;
    },error=>{
      this.handleError(error)
    });
    
  }

  selectedGroup(event)
  {
    this.groupSelected = event.target.value;
  }

  openSnackBar() {
    this._snackBar.open('Kid is added successfully..', 'close', {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
   
  
}
