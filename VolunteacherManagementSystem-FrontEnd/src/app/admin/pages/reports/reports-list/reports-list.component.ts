import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReportService } from 'src/app/admin/shared-services/report.service';
import { Report } from 'src/app/core/model/report';
import { User } from 'src/app/core/model/user';

@Component({
  selector: 'app-reports-list',
  templateUrl: './reports-list.component.html',
  styleUrls: ['./reports-list.component.css']
})
export class ReportsListComponent implements OnInit {

  //remove this
  reportList:Array<Report>
  years:Array<number>=[2021,2022,2023,2024,2025]
  showSpinner:boolean=false
  noReports:boolean=false
  rLength:number
  year:number = new Date().getFullYear()
  
  constructor(private router: Router,private reportService:ReportService) { 
  }


  ngOnInit(): void {
    this.reportList = []
    this.getReportsByYear(this.year)
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

  getReportsByYear(year:number)
  {
    this.showSpinner=true
      this.reportService.getReportsByYear(year).subscribe(data=>{
        this.reportList=data
        this.showSpinner=false
        if (data != null) {
          this.rLength = this.reportList.length
          this.noReports=false
        }
        //this.rLength=0
        if(this.rLength==0)
        {
          this.noReports=true
        }
        console.log(this.reportList);
        
      },error=>{
        this.handleError(error)
      })
  }

  selectedYear(event)
  {
    this.year = event.target.value;
    console.log(this.year);
    this.noReports=false
    this.rLength=0
    this.getReportsByYear(this.year)
  }
}
