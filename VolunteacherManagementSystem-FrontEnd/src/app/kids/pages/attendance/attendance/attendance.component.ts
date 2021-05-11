import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { authentication } from 'src/app/home/shared-services/authentication.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {
   monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
  ];
  month:string
  year:number
  constructor(private _auth:authentication,private router:Router) {}

  ngOnInit() {
    let date:Date = new Date()
    console.log(date)
    this.month=this.monthNames[date.getMonth()]
    //console.log("current"+ this.month)
    this.year=date.getFullYear()
   
  }

}
