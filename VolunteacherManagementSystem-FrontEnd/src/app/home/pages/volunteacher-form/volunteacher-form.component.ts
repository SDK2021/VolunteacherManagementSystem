import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Taluka } from 'src/app/core/model/taluka';
import { User } from 'src/app/core/model/user';
import { Village } from 'src/app/core/model/village';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { AddressService } from 'src/app/shared/shared-services/address.service';

@Component({
  selector: 'app-volunteacher-form',
  templateUrl: './volunteacher-form.component.html',
  styleUrls: ['./volunteacher-form.component.css']
})
export class VolunteacherFormComponent implements OnInit {

  v:Volunteacher=new Volunteacher()
  villages: Array<Village>
  cities: Array<Taluka>
  constructor(private router:Router,private addressService:AddressService) {
    this.v.user=new User()
   }

  ngOnInit(): void {
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

  getAllVillages(talukaId:number) 
  {
    this.addressService.getVillages(talukaId).subscribe(data=>{
    this.villages = data
    },error=>{
      this.handleError(error)
    })
  }

  // getTalukas(districtId:number)
  // {
  //   this.addressService.getTalukas(districtId).subscribe(data=>{
  //   this.cities = data
  //   })
  // }


}
