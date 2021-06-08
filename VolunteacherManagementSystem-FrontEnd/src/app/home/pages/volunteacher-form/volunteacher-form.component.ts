import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { Applicantrequest } from 'src/app/core/model/applicantrequest';
import { District } from 'src/app/core/model/district';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { User } from 'src/app/core/model/user';
import { Usertype } from 'src/app/core/model/usertype';
import { Village } from 'src/app/core/model/village';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { AddressService } from 'src/app/shared/shared-services/address.service';

@Component({
  selector: 'app-volunteacher-form',
  templateUrl: './volunteacher-form.component.html',
  styleUrls: ['./volunteacher-form.component.css']
})
export class VolunteacherFormComponent implements OnInit {

  v: Volunteacher = new Volunteacher()
  states:Array<State>
  villages: Array<Village>
  districts: Array<District>
  passwordMatch: boolean
  applicantRequest:Applicantrequest = new Applicantrequest()
  usertypes:string[] = new Array()
  isLvt:boolean = false
  isVt:boolean = false
  usertypeSelected:number
  stateSelected:number
  districtSelected:number
  villageSelected:number
  constructor(private volunteacherService:VolunteachersService, private route:ActivatedRoute, private router: Router, private addressService: AddressService) {
    this.v.user = new User()
  }

  ngOnInit(): void {
    console.log(this.route.snapshot.queryParamMap.get('requestId'));
    this.getApplicantRequest()
    this.usertypes = ["volunteacher","local volunteacher"];
    
    
     
  }

  selectedUserType(event)
  {
    console.log(event.target.value);
    
    if(event.target.value > 0)
    {
      if(event.target.value == 1)
      {
        this.isVt = true
        this.isLvt = false
        this.getAllStates()  
      }
      else
      {
        this.isLvt = true
        this.isVt = false
        this.getAllVillages()
      }
      this.usertypeSelected = event.target.value
    }
    else
    {
      this.isVt = false
      this.isLvt = false
    }
  }
  getAllStates() {
    this.addressService.getStates(8).subscribe(data=>[
      this.states = data
    ])
  }

  selectedState(event)
  {
    this.stateSelected = event.target.value
    if(event.target.value > 0)
    {
      this.addressService.getDistricts(this.stateSelected).subscribe(data=>{
        this.districts = data
      })
    }
    else
    {
      this.districts = []
      this.stateSelected = 0
      this.districtSelected = 0
    }
  }

  selectedVillage(event)
  {
    this.villageSelected = event.target.value
  }

  selectedDistrict(event)
  {
    this.districtSelected = event.target.value
  }

  getApplicantRequest() {
    this.volunteacherService.getApplicantRequestById(+this.route.snapshot.queryParamMap.get('requestId')).subscribe(data=>{
      this.applicantRequest = data
      this.v.user.email = this.applicantRequest.emailId
      this.v.user.phoneNumber = this.applicantRequest.phoneNumber
      this.v.user.userName = this.applicantRequest.name
      this.v.status = 0
      this.v.user.photo = "https://firebasestorage.googleapis.com/v0/b/volunteacher-management-50e59.appspot.com/o/vms%2Fusers%2Fprofile%2Fdummy%20user.png?alt=media&token=14623cbf-6392-4895-a1a4-82947dadfb58";
    })
  }

  checkPassword(conPass: string) {

    if (conPass.length > 0) {
      if (conPass == this.v.user.password) {
        this.passwordMatch = false
      }
      else {
        this.passwordMatch = true
      }
    }
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

  getAllVillages() {
    this.addressService.getAllVillages().subscribe(data => {
      this.villages = data
    }, error => {
      this.handleError(error)
    })
  }

  showProgressbar:boolean=false
  addUser()
  {
    this.showProgressbar=true
    console.log(this.v);
      let sessiondate: string = this.v.user.dob
      let dob: string[] = sessiondate.split("-")
      let dateofbirth = dob[1] + "-" + dob[2] + "-" + dob[0]
      this.v.user.dob = dateofbirth
      this.v.user.photo='https://firebasestorage.googleapis.com/v0/b/volunteacher-management-50e59.appspot.com/o/vms%2Fusers%2Fprofile%2Fdummy%20user.pngPCmLUI3m?alt=media&token=6c615dcc-cd9a-4d01-af0a-aba279687b02'

    if(this.isVt == true)
    {
      this.addressService.getDistrictById(this.districtSelected).pipe(finalize(()=>{
      let  usertype:Usertype  = new Usertype();
      usertype.typeId = 2
      usertype.type = "VOLUNTEACHER"
      this.v.user.type = usertype
      console.log(this.v);
      this.volunteacherService.addVolunteacher(this.v).subscribe(data=>{
        console.log(data);
        this.showProgressbar=false
        setTimeout(() => {
          this.router.navigate(['/login'])
        }, 1500);
        
      },error=>{
        this.handleError(error)
      })

      })).subscribe(data=>{
        this.v.district = data
      })
    }

    if(this.isLvt == true)
    {
      this.addressService.getVillageByid(this.villageSelected).pipe(finalize(()=>{
        let  usertype:Usertype  = new Usertype();
        usertype.typeId = 3
        usertype.type = "LOCAL VOLUNTEACHER"
        this.v.user.type = usertype
        console.log(this.v);
        this.volunteacherService.addVolunteacher(this.v).subscribe(data=>{
          console.log(data); 
          this.showProgressbar=false
          setTimeout(() => {
            this.router.navigate(['/login'])
          }, 1500); 
        })
  
        })).subscribe(data=>{
          this.v.village = data
        })
    } 
  }
}
