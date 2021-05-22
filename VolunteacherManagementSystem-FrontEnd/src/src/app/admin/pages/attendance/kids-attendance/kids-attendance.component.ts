import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kids-attendance',
  templateUrl: './kids-attendance.component.html',
  styleUrls: ['./kids-attendance.component.css']
})
export class KidsAttendanceComponent implements OnInit {
  attendances:Array<any>

  search:string=''
  constructor() { }

  ngOnInit(): void {
    this.attendances=[
      
      {photo:"team-1-800x800.jpg",name:"Krunal Prajapati",icon:"fas fa-times",color:"text-danger"},
      {photo:"team-4-800x800.jpg",name:"Suhanee Mavar",icon:"fas fa-check",color:"text-success"},
      {photo:"team-1-800x800.jpg",name:"Dhrumil Gohil",icon:"fas fa-times",color:"text-danger"}

    ]
  }

}
