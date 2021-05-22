import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { Area } from 'src/app/core/model/area';
import { finalize } from 'rxjs/operators';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-areas',
  templateUrl: './areas.component.html',
  styleUrls: ['./areas.component.css']
})
export class AreasComponent implements OnInit {
  isShow:boolean=false
  villageId:number;
  area:Area = new Area()

  showProgressbar: boolean = false

  constructor(private router:Router,private projectService:ProjectsService,private addressService:AddressService, private route:ActivatedRoute,private dialog:MatDialog, private _snackBar: MatSnackBar) { }
  areas:Array<Area> = []
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  ngOnInit(): void {
    this.getAllArea()
    this.villageId = this.route.snapshot.params['id']
  }
  show()
  {
    this.isShow=!this.isShow
  }

  getAllArea()
  {
    this.addressService.getAllArea().subscribe(data=>{
      this.areas = data
    })
  }
  editArea(index:number) {
    
    console.log(this.areas.filter(post=>post));
    
    console.log(this.areas[index]["isEdit"]=!this.areas[index]["isEdit"])
    this.area.areaName=this.areas[index]["areaName"]
    console.log(this.areas[index]["areaId"]);
    
   
  }
  addArea(form:NgForm)
  {
    this.showProgressbar=true
    this.addressService.getVillageByid(this.villageId).pipe(finalize(()=>{
      this.projectService.addArea(this.area).subscribe(data=>{
        console.log(data)
        this.openAddSnackBar()
        form.reset()
        this.show()
        setTimeout(()=>{
          this.getAllArea()
          this.showProgressbar=false
        },2000)
      },error=>{
        console.log(error);        
          if(error.status===500)
          {
            this.router.navigate(['internal-server-error'])
            
          }
          else
          {
            this.router.navigate(['error-page'])
          }
      })
    })).subscribe(data=>{
      this.area.village = data
    })
  }

  // openDialog()
  // {
  //   this.dialog.open(DialogBoxComponent)
  //   this.openSnackBar()
  // }

  openSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  openEditSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
  openAddSnackBar() {
    this._snackBar.open('Added successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  saveArea(index:number)
  {
    this.openEditSnackBar()
    this.areas[index]["isEdit"]=false
    console.log(this.area);
    this.addressService.saveArea(this.areas[index]["areaId"],this.area).subscribe(data=>{
      console.log(data);
      setTimeout(() => {
        this.getAllArea()
      }, 1000);
    })
    
  }
}
