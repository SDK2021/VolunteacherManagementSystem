import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { VolunteachersService } from 'src/app/admin/shared-services/volunteachers.service';
import { District } from 'src/app/core/model/district';
import { Taluka } from 'src/app/core/model/taluka';
import { User } from 'src/app/core/model/user';
import { Village } from 'src/app/core/model/village';
import { Volunteacher } from 'src/app/core/model/volunteacher';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';
import { UsersService } from 'src/app/user/services/users.service';
@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // editProfile:FormGroup
  villages: Array<Village>
  // profileImg:string="default-profile.jpg"
  districts: Array<District>
  districtSelected: number
  // editProfile:FormGroup


  showProgressbar:boolean=false
  volunteacher: Volunteacher = new Volunteacher()
  user: User
  firstName: string
  lastName: string
  isShow: boolean;
  villageSelected: number
  dob: string
  constructor(private router: Router, private addressService: AddressService, private profileService: ProfileService, private fb: FormBuilder, private _snackBar: MatSnackBar, private userServeice: UsersService, private authService: authentication) { }



  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.editProfile.value);
  }

  ngOnInit(): void {
    this.getProfileDetail()
    this.isShow = true;
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

  openSnackBar() {
    this._snackBar.open('Profile edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  setProfileValue(volunteacher: Volunteacher) {
    this.editProfile.get(['userInfo', 'fname']).setValue(this.firstName)
    this.editProfile.get(['userInfo', 'lname']).setValue(this.lastName)
    this.editProfile.get(['userInfo', 'gender']).setValue(volunteacher.user.gender)
    this.editProfile.get(['userInfo', 'dob']).setValue(formatDate(volunteacher.user.dob, 'yyyy-MM-dd', 'en'))

    this.editProfile.get(['contactInfo', 'email']).setValue(volunteacher.user.email)
    this.editProfile.get(['contactInfo', 'phone']).setValue(volunteacher.user.phoneNumber)
    this.editProfile.get(['contactInfo', 'pincode']).setValue(volunteacher.pincode)
    this.editProfile.get(['contactInfo', 'city']).setValue(volunteacher.district.districtId)

    this.editProfile.get(['otherInfo', 'education']).setValue(volunteacher.education)
    this.editProfile.get(['otherInfo', 'employer']).setValue(volunteacher.employerName)

    if (volunteacher.user.type.typeId == 3) {
      this.editProfile.get(['otherInfo', 'schoolName']).setValue(volunteacher.school)
      this.editProfile.get(['contactInfo', 'village']).setValue(volunteacher.village.villageId)
    }
    else {
      this.isShow = false;
    }
  }



  getVolunteacherForm() {
    this.volunteacher.user.userName = this.editProfile.get(['userInfo', 'fname']).value + " " +
      this.editProfile.get(['userInfo', 'lname']).value

    this.volunteacher.user.gender = this.editProfile.get(['userInfo', 'gender']).value
    this.volunteacher.user.dob = this.editProfile.get(['userInfo', 'dob']).value
    this.volunteacher.user.email = this.editProfile.get(['contactInfo', 'email']).value
    this.volunteacher.user.phoneNumber = this.editProfile.get(['contactInfo', 'phone']).value
    this.volunteacher.pincode = this.editProfile.get(['contactInfo', 'pincode']).value

    this.volunteacher.village = this.editProfile.get(['contactInfo', 'village']).value

    this.volunteacher.district = this.editProfile.get(['contactInfo', 'city']).value
    this.volunteacher.education = this.editProfile.get(['otherInfo', 'education']).value
    this.volunteacher.employerName = this.editProfile.get(['otherInfo', 'employer']).value
    this.volunteacher.school = this.editProfile.get(['otherInfo', 'schoolName']).value




  }
  saveProfile() {
    this.showProgressbar=true
    this.getVolunteacherForm()
    console.log(this.volunteacher);
    console.log(this.volunteacher);
    if (this.villageSelected = 0) {
      this.villageSelected = this.volunteacher.village.villageId
    }

    if (!(this.volunteacher.user.dob === this.dob)) {
      let dob: string = this.volunteacher.user.dob
      let date: string[] = dob.split("-")
      let vtdob = date[1] + "-" + date[2] + "-" + date[0]
      this.volunteacher.user.dob = vtdob
    }
    this.addressService.getDistrictById(this.editProfile.get(['contactInfo', 'city']).value).pipe(finalize(() => {
      if (this.volunteacher.user.type.typeId == 3) {
        this.addressService.getVillageByid(this.editProfile.get(['contactInfo', 'village']).value).pipe(finalize(() => {
          this.userServeice.saveVolunteacher(this.volunteacher.volunteacherId, this.volunteacher).subscribe(data => {
            this.showProgressbar=false
            console.log(data + "  edited")
            this.openSnackBar()
            this.router.navigate(['/user/profile/posts'])
            
          }, error => {
              this.handleError(error)
            })
        })).subscribe(data => {
          this.volunteacher.village = data
        })
      }
      else {
        this.volunteacher.village = null
        this.userServeice.saveVolunteacher(this.volunteacher.volunteacherId, this.volunteacher).subscribe(data => {
          this.showProgressbar=false
          console.log(data + "  edited")
          this.openSnackBar()
          this.router.navigate(['/user/profile/posts'])
        }, error => {
          this.handleError(error)
        })
      }


    })).subscribe(data => {
      this.volunteacher.district = data
    })
  }

  getProfileDetail() {
    let username: string;
    let authuser: string[];
    let userId: number;
    let userName: string[]

    authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
    username = atob(authuser[0]);
    this.userServeice.getUserByEmail(username).pipe(finalize(() => {
      this.profileService.getVolunteacherByUser(userId).pipe(finalize(() => {
        this.addressService.getDistricts(this.volunteacher.district.state.stateId).pipe(finalize(() => {
          if (this.volunteacher.user.userId == 3) {
            this.addressService.getVillages(this.volunteacher.village.taluka.talukaId).subscribe(data => {
              this.villages = data
            }, error => {
              this.handleError(error)
            })
          }
        })).subscribe(data => {
          this.districts = data

          console.log(this.districtSelected);

        })
      })).subscribe(data => {
        this.volunteacher = data
        this.dob = this.volunteacher.user.dob
        this.districtSelected = this.volunteacher.district.districtId
        this.setProfileValue(this.volunteacher);
      })
    })).subscribe(data => {
      this.user = data;
      console.log(this.user)
      userName = this.user.userName.split(" ")
      this.firstName = userName[0]
      this.lastName = userName[1]
      userId = this.user.userId;
    })
  }



  editProfile = this.fb.group({
    userInfo: this.fb.group({
      fname: [null, [Validators.required, Validators.pattern('^[a-zA-z]{3,10}$')]],
      lname: [null, [Validators.required, Validators.pattern('^[a-zA-z]{3,10}$')]],
      gender: ['Select', Validators.required],
      dob: ['', Validators.required],
    }),
    contactInfo: this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      phone: [null, [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      pincode: [null, [Validators.required, Validators.pattern('^[0-9]{6}$')]],
      village: ['', Validators.required],
      city: ['', Validators.required],
    }),
    otherInfo: this.fb.group({
      education: ['', Validators.required],
      employer: ['', Validators.required],
      schoolName: ['', Validators.required],
    }),

  });

}
