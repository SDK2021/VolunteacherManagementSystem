import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { KidService } from 'src/app/admin/shared-services/kid.service';
import { Kidsreport } from 'src/app/core/model/kidsreport';

@Component({
  selector: 'app-kids-reports-list',
  templateUrl: './kids-reports-list.component.html',
  styleUrls: ['./kids-reports-list.component.css']
})
export class KidsReportsListComponent implements OnInit {

  showSpinner:boolean=false
  noReports:boolean=false
  krLength:number
  kidId:number
  year:number=2021

  years:number[]=[2021,2022,2023,2024,2025]
  kidsReports:Array<Kidsreport>=new Array()

  constructor(private router:Router, private route:ActivatedRoute,private kidService:KidService) { }

  ngOnInit(): void {
    this.kidId=this.route.snapshot.params['id']
    this.getAllReports(this.kidId,this.year)
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

  selectedYear(event)
  {
    this.year = event.target.value;
    console.log(this.year);
    this.noReports=false
    this.krLength=0
    this.getAllReports(this.route.snapshot.params['id'],this.year)
  }


  getAllReports(id:number,year:number)
  {
    this.showSpinner=true
    this.kidService.getAllKidsReportByKid(id,year).subscribe(data=>{
        this.kidsReports=data
        this.showSpinner=false
        if (data != null) {
          this.krLength = this.kidsReports.length
          this.noReports=false
        }
        //this.krLength=0
        if(this.krLength==0)
        {
          this.noReports=true
        }
        console.log(this.kidsReports);
        
    },error=>{
      this.handleError(error)
    })
  }

}
