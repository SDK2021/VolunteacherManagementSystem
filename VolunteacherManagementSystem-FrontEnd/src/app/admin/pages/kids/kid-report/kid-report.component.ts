import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Chart from 'chart.js';
import { Area } from 'src/app/core/model/area';
import { Kid } from 'src/app/core/model/kid';
import { Kidsreport } from 'src/app/core/model/kidsreport';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { KidsService } from 'src/app/kids/shared-services/kids.service';
import {  chartOptions, parseOptions } from 'src/app/variables/charts';

@Component({
  selector: 'app-kid-report',
  templateUrl: './kid-report.component.html',
  styleUrls: ['./kid-report.component.css']
})
export class KidReportComponent implements OnInit {
  public datasets: any;
  public data: any;
  public salesChart;
  public clicked: boolean = true;
  public clicked1: boolean = false;
  
  kidReport:Kidsreport=new Kidsreport()

  showSpinner:boolean=false

  showImageSpinner:boolean=true


  constructor(private route:ActivatedRoute,private kidsService:KidsService,private router:Router) {}

  

  ngOnInit(): void {

    console.log(Chart.colors);
    console.log(Chart.colors.theme['danger']);
    
    

    this.kidReport.kid=new Kid()
    this.kidReport.kid.area=new Area()
    this.datasets = [
      [0, 20, 10, 30, 15, 40, 20, 60, 60],
      [0, 20, 5, 25, 10, 30, 15, 40, 40]
    ];
    this.data = this.datasets[0];


  

    parseOptions(Chart, chartOptions());


    var chartPie = document.getElementById('chart-pie');

    parseOptions(Chart, chartOptions());

    var personalityChart = new Chart(chartPie, {
      type: 'pie',
      options: this.personalityChart.options,
      data: this.personalityChart.data
    });
    
    var chartSales = document.getElementById('chart-sales');

    // this.salesChart = new Chart(chartSales, {
		// 	type: 'line',
		// 	options: chartExample1.options,
		// 	data: chartExample1.data
		// });

    this.getKidReportById(this.route.snapshot.params['rid'])
  }


  
  load()
  {
    this.showImageSpinner=false
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


  public updateOptions() {
    this.salesChart.data.datasets[0].data = this.data;
    this.salesChart.update();
  }

  getKidReportById(id: number) {
    this.showSpinner=true
    this.kidsService.kidReportById(id).subscribe(data=>{
      this.kidReport = data
      this.showSpinner=false
      this.kidReport.kid=this.calculateAge(this.kidReport.kid)
      console.log(this.kidReport);
      
    },error=>{
      this.handleError(error)
    })
  }

  calculateAge(kid:Kid):Kid
  {
     
        let currentDate=new Date()
        let bDate=new Date(kid.dob)
        
        let diffInSec= Math.abs(currentDate.getTime()-bDate.getTime())
        console.log(diffInSec);
        
        kid.age=(diffInSec/(1000 * 3600 * 24)/365)+1
        let array:Array<string>=kid.age.toString().split('.')
        kid.age=Number.parseInt(array[0])
        console.log(kid.age)
        
     return kid
     
  }
  personalityChart = {
    options: {
  
    },
    data: {
      labels: ["Discipline", "Prayer", "Goshthi","Abhivyakti"],
      datasets: [
        {
          data: [this.kidReport.discipline, this.kidReport.prayer, this.kidReport.goshthi,this.kidReport.abhivyakti],
          backgroundColor: [
            "red",
            "green",
            "blue"
          ]
        }
      ],
      
    }
  }


}
