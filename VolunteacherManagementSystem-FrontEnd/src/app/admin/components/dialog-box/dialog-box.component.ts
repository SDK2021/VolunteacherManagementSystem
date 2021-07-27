import { Component, Injectable, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-box',
  templateUrl: './dialog-box.component.html',
  styleUrls: ['./dialog-box.component.css']
})

@Injectable({
  providedIn:'root'
})
export class DialogBoxComponent implements OnInit {

  yes:boolean=false
  no:boolean=false


  constructor( private dialogRef: MatDialogRef<DialogBoxComponent>) { }

  ngOnInit(): void {
  }

  yesClick()
  {
      this.yes=true
      this.no=false
      console.log("dialog" + this.yes);
      this.dialogRef.close({delete:this.yes});
     
  }

  noClick()
  {
    this.no=false
    this.yes=false
    this.dialogRef.close({delete:this.no});
  }


  ngOnDestroy()
  {
  
      console.log("Bye  Bye");
      
  }
}
