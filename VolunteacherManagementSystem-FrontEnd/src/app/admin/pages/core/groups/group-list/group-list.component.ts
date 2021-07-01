import { Component, OnInit } from '@angular/core';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { Router, ActivatedRoute } from '@angular/router';
import { KidsService } from 'src/app/kids/shared-services/kids.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { NgForm } from '@angular/forms';
import { ProjectsService } from 'src/app/admin/shared-services/projects.service';
import { KidService } from 'src/app/admin/shared-services/kid.service';
import { AddressService } from 'src/app/shared/shared-services/address.service';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';
import { MatDialog } from '@angular/material/dialog';
import { finalize } from 'rxjs/operators';
@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  sessionId: number
  groups: Array<KidsGroup>;
  gLength:number
  noGroups:boolean=false
  colors: Array<string>;
  isShow: boolean = false
  group: KidsGroup = new KidsGroup();

  isEdit:boolean=false

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  kidsGroup: KidsGroup = new KidsGroup()

  showSpinner:boolean=false
  showProgressbar: boolean = false

  disabled:boolean=null

  isError:boolean=false

  constructor(private kidService:KidService, private dialog:MatDialog, private addressService:AddressService,private router:Router,private kids: KidService, private kidsService: KidsService, private route: ActivatedRoute, private _snackBar: MatSnackBar) {
    this.colors = ["bg-deeppink","bg-info","bg-danger", "bg-yellow", "bg-purple"];
  }

  ngOnInit(): void {
    this.getkidsgroup()
  }

  handleError(error)
  {
    console.log(error);
    console.log(error.status);
    
    if(error.status===500)
    {
      this.router.navigate(['internal-server-error'])
    }
    else if(error.status===400)
    {
        this.isError=true
        this.showProgressbar=false
    }
    else
    {
      this.router.navigate(['error-page'])
    }
  }

  getkidsgroup() {
    this.showSpinner=true
    this.kidsService.getkidsgrouplist().pipe(finalize(()=>{
      this.kidService.getAllKidslist().subscribe(data => {
        let flag = 0
        for (let group of this.groups) {
          flag = 0
          for (let kid of data) {
            if (kid.group.groupId === group.groupId) {
              flag = 1              
              break
            }
          }
          if (flag == 1) {
            group.isDelete = true
          }
          else {
            group.isDelete = false
          }
        }
      })
    })).subscribe(data => {
      this.groups = data;
      this.showSpinner=false
      if (data != null) {
        this.gLength = this.groups.length
        this.noGroups=false
      }
      //this.gLength=0
      if(this.gLength==0)
      {
        this.noGroups=true
      }
    },error=>{
      this.handleError(error)
    });

  }

  show() {
    this.isShow = !this.isShow
  }

  addGroup(form:NgForm) {
    this.disabled=true
    this.showProgressbar = true
    this.kids.addKidsGroup(this.kidsGroup).subscribe(data => {
      console.log(data)
      
      form.reset()
      setTimeout(() => {
        this.getkidsgroup()
        this.showProgressbar = false
        this.openSnackBar()
        this.disabled=false
        this.show()
      }, 2000)
    },error=>{
      this.handleError(error)
    })
  }

  saveGroup(){
    this.disabled=true
    console.log(this.kidsGroup);
    this.kidsService.editKidsGroup(this.kidsGroup.groupId,this.kidsGroup).subscribe(data=>{
      console.log(data);
      this.disabled=false
      this.isEdit=false
      this.kidsGroup=new KidsGroup()
    },error=>{
      this.handleError(error)
    })

    
  }

  openSnackBar() {
    this._snackBar.open('Added successfully..', 'close', {
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

  openDeleteSnackBar() {
    this._snackBar.open('Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    console.log(this.kidsGroup);

    this.show()
  }

  getGroup(id:number)
  {
      this.addressService.getGroupById(id).subscribe(data=>{
        this.kidsGroup=data
      },error=>{
        this.handleError(error)
      })
  }
  edit(id:number)
  {
    this.isEdit=true
    this.getGroup(id)
  }

  deleteGroup(id: number) {
    this.disabled = true
    this.showProgressbar = true
    this.addressService.deleteGroup(id).subscribe(data => {
      console.log(data);
      setTimeout(() => {
        this.getkidsgroup()
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
        this.deleteGroup(id)
      }
    })
  }

}
