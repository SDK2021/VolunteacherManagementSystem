import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { Area } from 'src/app/core/model/area';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { Kid } from 'src/app/core/model/kid';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { KidsService } from '../../shared-services/kids.service';

@Component({
  selector: 'app-edit-kid',
  templateUrl: './edit-kid.component.html',
  styleUrls: ['./edit-kid.component.css']
})
export class EditKidComponent implements OnInit {

  baseUrl: string = "/vms/kids/profiles"
  imageURL: string=null


  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  levels: Array<string>

  standards: Array<number> = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
  area: Array<Area>
  isShow: boolean = true
  Show: boolean = true
  showForm: boolean = false

  showProgressbar: boolean = false
  isKidEdited: boolean = null
  oldImage: String = ''

  countries: Array<Country>
  states: Array<State>
  districts: Array<District>
  talukas: Array<Taluka>
  villages: Array<Village>
  areas: Array<Area>
  groups: Array<KidsGroup>

  stateSelected: number;
  districtSelected: number;
  talukaSelected: number;
  villageSelected: number;
  areaSelected: number;
  groupSelected: number;
  edit: boolean = false
  kid: Kid = new Kid();

  showImageSpinner: boolean = true
  namePattern: string = "[a-zA-Z ]{3,20}"


  constructor(private fileService: FileUploadService, private route: ActivatedRoute, private router: Router, private _snackBar: MatSnackBar, private kidsService: KidsService, private addressService: AddressService) { }

  ngOnInit() {
    this.showForm = true

    // this.imageURL = localStorage.getItem("imageURL")

    // if (this.imageURL != null) {
    //   this.fileService.delete(this.imageURL)
    //   console.log("deleted from ngOniinIt");
    //   localStorage.removeItem("imageURL")

    // }

    this.getkidsgroup()
    this.getAllCountries();
    this.getAllStates();
    this.getAllDistricts();
    this.getAllTalukas();
    this.getAllVillages();

    this.levels = [
      "AatmaSiksha", "AatmaShodh", "AatmaVishesh"
    ]
    this.getKidById(this.route.snapshot.params['id'])
  }
  // ngOnDestroy() {
  //   if (this.isKidEdited == false) {
  //     if (this.imageURL != null) {
  //       this.fileService.delete(this.imageURL)
  //       localStorage.removeItem("imageURL")
  //       console.log("Removed from destroy");

  //     }

  //     console.log("Bye Bye");

  //   }
  // }
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

  show(isShow): void {
    this.showForm = isShow
    this.imageURL = localStorage.getItem("imageURL")
    localStorage.removeItem("imageURL")
    this.showImageSpinner=true
  }

  getKidById(kidId: number) {
    this.kid = new Kid()
    this.kid.village = new Village()
    this.kid.area = new Area()
    this.kid.group = new KidsGroup()

    this.kidsService.kidById(kidId).pipe(finalize(() => {
      this.addressService.getAreas(this.kid.village.villageId).pipe(finalize(() => {
        this.addressService.getVillages(this.kid.village.taluka.talukaId).pipe(finalize(() => {
          this.addressService.getTalukas(this.districtSelected).pipe(finalize(() => {
            this.addressService.getDistricts(this.stateSelected).subscribe(data => {
              this.districts = data;
            },
              error => {
                this.handleError(error)
              })

          })).subscribe(data => {
            this.talukas = data
          }, error => {
            this.handleError(error)
          })
        })).subscribe(data => {
          this.villages = data
          this.stateSelected = this.kid.village.taluka.district.state.stateId
          this.districtSelected = this.kid.village.taluka.district.districtId
          this.talukaSelected = this.kid.village.taluka.talukaId
          this.villageSelected = this.kid.village.villageId
          this.areaSelected = this.kid.area.areaId
          this.groupSelected=this.kid.group.groupId
        }, error => {
          this.handleError(error)
        })
      })).subscribe(data => {
        this.areas = data
      })
    })).subscribe(data => {
      this.kid = data
      this.imageURL = this.kid.photo
      console.log(this.kid);
    })
  }

  saveKid() {
    console.log(this.imageURL);
    
    // if (this.imageURL != null) {
    //   this.oldImage = this.kid.photo
    //   this.kid.photo = this.imageURL
    // }
    if (this.areaSelected > 0 && this.groupSelected > 0) {
      this.showProgressbar = true
      this.kid.photo = this.imageURL
      this.groupSelected = this.kid.group.groupId
      this.areaSelected = this.kid.area.areaId
      this.villageSelected = this.kid.village.villageId

      console.log(this.kid);
      let dob: String = this.kid.dob
      let dobdate: String[] = dob.split("-")
      let dateofbirth = dobdate[0] + "-" + dobdate[1] + "-" + dobdate[2]
      this.kid.dob = dateofbirth
      console.log(this.kid.dob);
      this.kidsService.getAreaById(this.areaSelected).subscribe(areadata => {
        console.log(areadata)
        this.kid.area = areadata
        this.kidsService.kidGroupById(this.groupSelected).pipe(finalize(() => {
          this.kidsService.villageById(areadata.village.villageId).pipe(finalize(() => {
            this.kidsService.addKid(this.kid).subscribe(data => {
              console.log(data)
              // this.isKidEdited = true
              // if (this.oldImage != null) {
              //   this.fileService.delete(this.oldImage)
              //   localStorage.removeItem("imageURL")
              // }
              this.showProgressbar = false
              this.openEditSnackBar();
              this.router.navigate(['/user/kids/edit-kids/kids-list'])
            }, error => {
              this.handleError(error)
            })
          })).subscribe(data => {
            this.kid.village = data
          })
        })).subscribe(data => {
          this.kid.group = data
        })
      })
    }
  }

  openEditSnackBar() {
    this._snackBar.open('Kid edited successfully..', 'close', {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getAllCountries() {
    this.addressService.getCountries().subscribe(data => {
      this.countries = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedCountry(event) {
    this.addressService.getStates(event.target.value).subscribe(data => {
      this.states = data
    }, error => {
      this.handleError(error)
    })
  }

  getAllStates() {
    this.addressService.getStates(8).subscribe(data => {
      this.states = data;
    }, error => {
      this.handleError(error)
    })
  }

  selectedState(event) {
    this.stateSelected = event.target.value;
    this.Show = false
    this.villageSelected = 0
    this.talukaSelected = 0
    this.districtSelected = 0
    this.areaSelected = 0
    this.talukas = []
    this.villages = []
    this.areas = []
    if (event.target.value > 0) {
      this.addressService.getDistricts(event.target.value).subscribe(data => {
        this.districts = data
      }, error => {
        this.handleError(error)
      })
    }
  }

  getAllDistricts() {
    this.addressService.getDistricts(7).subscribe(data => {
      this.districts = data;
    }, error => {
      this.handleError(error)
    })
  }


  load() {
    this.showImageSpinner = false
  }
  selectedDistrict(event) {
    this.villageSelected = 0
    this.talukaSelected = 0
    this.areaSelected = 0
    this.Show = false
    if (event.target.value > 0) {
      this.districtSelected = event.target.value;
      this.addressService.getTalukas(event.target.value).subscribe(data => {
        this.talukas = data
        this.areas = []
        this.villages = []
      }, error => {
        this.handleError(error)
      })
    }
    else {
      this.talukas = []
      this.villages = []
      this.areas = []
      this.districtSelected = 0
    }
  }

  getAllTalukas() {
    this.addressService.getTalukas(141).subscribe(data => {
      this.talukas = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedTaluka(event) {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if (event.target.value != 0) {
      this.addressService.getVillages(event.target.value).subscribe(data => {
        this.villages = data
        this.areas = []
      }, error => {
        this.handleError(error)
      })
    }
    else {
      this.talukaSelected = 0
      this.villageSelected = 0
      this.areaSelected = 0
      this.areas = []
      this.villages = []
    }
  }

  getAllVillages() {
    this.addressService.getVillages(35).subscribe(data => {
      this.villages = data
    }, error => {
      this.handleError(error)
    })
  }

  selectedVillage(event) {
    this.villageSelected = event.target.value;
    console.log(event.target.value);
    if (event.target.value != 0) {
      this.addressService.getAreas(event.target.value).subscribe(data => {
        this.areas = data
      }, error => {
        this.handleError(error)
      })
    }
    else {
      this.areas = []
    }
  }

  selectedArea(event) {
    this.areaSelected = event.target.value;
  }

  getkidsgroup() {
    this.kidsService.getkidsgrouplist().subscribe(data => {
      this.groups = data;
    }, error => {
      this.handleError(error)
    });

  }

  selectedGroup(event) {
    this.groupSelected = event.target.value;
  }

  openSnackBar() {
    this._snackBar.open('Kid is added successfully..', 'close', {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  editPhoto() {
    if (this.edit == false) {
      this.edit = true
      this.showForm = false
    }
    else {
      this.showForm = true
    }

  }

}
