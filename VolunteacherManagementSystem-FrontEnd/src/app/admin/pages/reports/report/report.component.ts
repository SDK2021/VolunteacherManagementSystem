import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from 'src/app/admin/shared-services/dashboard.service';
import { ReportService } from 'src/app/admin/shared-services/report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  array:number[]=[1,2,3,4,5,6]
  constructor(private reportService:ReportService, private router:Router, private dashboardService:DashboardService) { }

  totalVolunteachers: number
  totalKids: number
  totalSessions: number
  totalEvents: number
  showSpinner:boolean
  totalVTS:number 
  totalLVTS:number
  totalNewKids:number
  ngOnInit(): void {
    this.totalLVTS = 0
    this.totalVTS = 0
    this.getTotalEvents()
    this.getTotalKids()
    this.getTotalSessions()
    this.getTotalVolunteachers()
    this.getAllNewVolunteacher()
    this.getAllNewKids()
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

  getTotalVolunteachers() {
    this.showSpinner=true;
    this.dashboardService.getTotalvolunteachers().subscribe(data=>{
      this.totalVolunteachers=data
      console.log(data);
      this.showSpinner=false;
      console.log(this.totalVolunteachers);
    },error=>{
      this.handleError(error)
    })
  }

  getTotalKids() {
      this.dashboardService.getTotalKids().subscribe(data=>{
        this.totalKids=data
        console.log(this.totalKids);
      },error=>{
        this.handleError(error)
      })
  }

  getTotalSessions() {
    this.showSpinner=true;
    this.dashboardService.getTotalSesssions().subscribe(data=>{
      this.totalSessions=data
      console.log(data);
      this.showSpinner=false;
      console.log(this.totalSessions);
    },error=>{
      this.handleError(error)
    })

  }

  getTotalEvents() {
    this.showSpinner=true;
    this.dashboardService.getTotalEvents().subscribe(data=>{
      this.totalEvents=data
      console.log(data);
      this.showSpinner=false;
      console.log(this.totalEvents);
    },error=>{
      this.handleError(error)
    })
  }

  getAllNewVolunteacher()
  {
    console.log("Hello");
    
    this.reportService.getAllNewUsers().subscribe(data=>{
      console.log(data);
      for (let vt of data) {
        if(vt.user.type.typeId == 2)
        {
          this.totalVTS +=1
        }
        if(vt.user.type.typeId == 3)
        {
          this.totalLVTS += 1
        }
      }
    },error=>{
      this.handleError(error)
    })
  }

  getAllNewKids()
  {
    this.reportService.getAllNewKids().subscribe(data=>{
      console.log(data); 
      this.totalNewKids = data
    })
  }
}
