import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Country } from 'src/app/core/model/country';
import { District } from 'src/app/core/model/district';
import { Notification } from 'src/app/core/model/notification';
import { Project } from 'src/app/core/model/project';
import { Session } from 'src/app/core/model/session';
import { State } from 'src/app/core/model/state';
import { Taluka } from 'src/app/core/model/taluka';
import { Village } from 'src/app/core/model/village';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { NotificationsService } from 'src/app/user/services/notifications.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  styleUrls: ['./edit-session.component.css']
})
export class EditSessionComponent implements OnInit {
  
  session:Session=new Session()
  projects:Project[]=new Array()

  showProgressbar: boolean = false


  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  Show: boolean = true
  countries: Array<Country>
  states: Array<State>
  districts: Array<District>
  talukas: Array<Taluka>
  villages: Array<Village>

  disabled:boolean=null

  stateSelected: number;
  districtSelected: number;
  talukaSelected: number;
  villageSelected: number;
  projectSelected: number;

  sessionDate:string;



  constructor(private route:ActivatedRoute,private router: Router,private projectService: ProjectsService, private addressService: AddressService, private _snackBar: MatSnackBar,  private sessionService: SessionsService) {

  }

  ngOnInit(): void {

    this.session.village=new Village()
    this.session.project=new Project()
   
    let date: Date = new Date()
    console.log(date)
   
    this.countries = []
    this.states = []
    this.villages = []
    this.talukas = []
    this.districts = []
    

    this.getAllCountries();
    this.getAllStates();
    this.getProjects();

    this.getSession(this.route.snapshot.params['id'])
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

  

  openEditSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

 

  onSubmit(form: NgForm) {
    console.log(this.session);

  }



  getSession(sessionId)
  {
    this.sessionService.sessionById(sessionId).subscribe(data=>{
      let date:string[] = data.sessionDate.split("-")
      this.session.sessionDate = date[2] + "-" + date[0] +"-" + date[1]
      this.session = data
      this.sessionDate = data.sessionDate
      console.log(data);
      this.addressService.getVillages(this.session.village.taluka.talukaId).pipe(finalize(()=>{
        this.addressService.getTalukas(this.districtSelected).pipe(finalize(()=>{
            this.addressService.getDistricts(this.stateSelected).subscribe(data=>{
            this.districts = data;             
          },
          error=>{
            this.handleError(error)
          })
          
        })).subscribe(data=>{
        this.talukas = data
        },error=>{
          this.handleError(error)
        })
    })).subscribe(data=>{
      this.villages = data
      this.stateSelected = this.session.village.taluka.district.state.stateId
      this.districtSelected = this.session.village.taluka.district.districtId
      this.talukaSelected = this.session.village.taluka.talukaId
      this.villageSelected = this.session.village.villageId
      this.projectSelected = this.session.project.projectId
    },error=>{
      this.handleError(error)
    })
    })
  }

  showDate()
  {
    console.log(this.session.sessionDate);
    
  }
  saveSession()
  {
    this.disabled=true
    if(this.villageSelected > 0 && this.projectSelected > 0)
    {
      console.log(this.sessionDate + " " + this.session.sessionDate+"Uprvali first");
      
      this.showProgressbar = true
      if(!(this.sessionDate === this.session.sessionDate))
      {
        console.log("Hello");
        
        let sessiondate: string = this.session.sessionDate
        console.log(sessiondate +" Session date ");
        
        let sdate: string[] = sessiondate.split("-")
        let sessionDate = sdate[1] + "-" + sdate[2] + "-" + sdate[0]
        this.session.sessionDate = sessionDate
        console.log(this.session.sessionDate +" Formated");
        
      }
      

      console.log(this.villageSelected + " " + this.projectSelected);
      
      if(this.villageSelected == 0)
        this.villageSelected = this.session.village.villageId

      if(this.projectSelected == 0)
        this.projectSelected = this.session.project.projectId
      this.session.startingTime = this.session.startingTime + ":00"
      this.session.endingTime = this.session.endingTime + ":00"
      this.session.notified = false

      this.projectService.getProject(this.projectSelected).pipe(finalize(() => {
        this.addressService.getVillageByid(this.villageSelected).pipe(finalize(() => {
          this.sessionService.editSession(this.session.sessionId,this.session).subscribe(data => {
            console.log(data)
         
            setTimeout(()=>{
              this.showProgressbar = false
              this.openEditSnackBar()
              this.disabled=false
              this.router.navigate(['/admin/sessions'])
            },2000)
          },error=>{
            this.handleError(error)
          })
        })).subscribe(data => {
          this.session.village = data
        })
      })).subscribe(data => {
        this.session.project = data
      })
    }  
  }
 
  getProjects() {
    this.projectService.getAllProjects().subscribe(data => {
      this.projects = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedProject(event) {
    this.projectSelected = event.target.value;
    console.log(event.target.value);
  }

  getAllCountries() {
    this.addressService.getCountries().subscribe(data => {
      this.countries = data
    },error=>{
      this.handleError(error)
    })
  }

  selectedCountry(event) {
    this.addressService.getStates(event.target.value).subscribe(data => {
      this.states = data
    })
  }

  getAllStates() {
    this.addressService.getStates(8).subscribe(data => {
      this.states = data;
    },error=>{
      this.handleError(error)
    })
  }

  selectedState(event) {
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


  selectedDistrict(event) {
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

  selectedTaluka(event) {
    this.talukaSelected = event.target.value;
    console.log(event.target.value);
    if (event.target.value != 0) {
      this.addressService.getVillages(event.target.value).subscribe(data => {
        this.villages = data
      })
    }
  }

  selectedVillage(event) {
    this.villageSelected = event.target.value;
    console.log(event.target.value);
  }
}
