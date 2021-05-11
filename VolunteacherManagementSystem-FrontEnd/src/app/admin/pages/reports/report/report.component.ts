import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  array:number[]=[1,2,3,4,5,6]
  constructor() { }

  ngOnInit(): void {
  }

}
