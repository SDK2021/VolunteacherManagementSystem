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
import { KidService } from 'src/app/admin/shared-services/kid.service';
@Component({
  selector: 'app-areas',
  templateUrl: './areas.component.html',
  styleUrls: ['./areas.component.css']
})
export class AreasComponent implements OnInit {
  isShow:boolean=false
  villageId:number;
  area:Area = new Area()

  showSpinner:boolean=false
  noAreas:boolean=false
  aLength:number

  showProgressbar: boolean = false

  constructor(private kidService:KidService, private router:Router,private projectService:ProjectsService,private addressService:AddressService, private route:ActivatedRoute,private dialog:MatDialog, private _snackBar: MatSnackBar) { }
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
    this.showSpinner=true
    this.addressService.getAreas(this.route.snapshot.params['id']).pipe(finalize(()=>{
      this.kidService.getAllKidslist().subscribe(data=>{
        let flag=0
        for (let area of this.areas) {
          flag = 0
          for (let kid of data) 
          {
            if(kid.area.areaId === area.areaId)
            {
              flag = 1
              console.log("Area:"+area.areaId);
              break 
            }
          }
          if(flag==1)
          {
            area.isDelete = true
          }
          else
          {
            area.isDelete = false
          }
        }
        console.log(this.areas);
        this.showSpinner=false
        if (data != null) {
          this.aLength = this.areas.length
          this.noAreas=false
        }
        //this.aLength=0
        if(this.aLength==0)
        {
          this.noAreas=true
        }
      })
    })).subscribe(data=>{
      this.areas = data
    })
  }
  editArea(index:number) {
    
    console.log(this.areas.filter(post=>post));
    
    console.log(this.areas[index]["isEdit"]=!this.areas[index]["isEdit"])
    this.area.areaName=this.areas[index]["areaName"]
    console.log(this.areas[index]["areaId"]);
    
   
  }

  handleError(error)
  {
    console.log(error);        
    if(error.status===500)
    {
      this.router.navigate(['internal-server-error'])
      
    }
    else
    {
      this.router.navigate(['error-page'])
    }
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
        this.handleError(error)
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
   
    console.log(this.area);
    this.area=this.areas[index]
    // alert(this.area.areaName)
    this.addressService.saveArea(this.areas[index]["areaId"],this.area).subscribe(data=>{
      console.log(data);
      setTimeout(() => {
        this.getAllArea()
        this.areas[index]["isEdit"]=false
      }, 1000);
    })
    
  }

  deleteArea(id:number)
  {
    this.showProgressbar=true
     this.addressService.deleteArea(id).subscribe(data=>{
       console.log(data);  
       this.openDeleteSnackBar()  
       setTimeout(() => {
        this.getAllArea()
        this.showProgressbar=false
       }, 2000);
     },error=>{
      this.handleError(error)
    })
  }

  delete(id:number)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deleteArea(id)
      }
    })
    
  }

  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
}
