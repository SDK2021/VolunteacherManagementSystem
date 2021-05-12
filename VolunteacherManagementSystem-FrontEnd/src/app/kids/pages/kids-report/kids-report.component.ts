import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Chart from 'chart.js';
import { Area } from 'src/app/core/model/area';
import { Kid } from 'src/app/core/model/kid';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { chartExample1, chartExample2, chartExample3, chartOptions, parseOptions } from 'src/app/variables/charts';
import { KidsService } from '../../shared-services/kids.service';

@Component({
  selector: 'app-kids-report',
  templateUrl: './kids-report.component.html',
  styleUrls: ['./kids-report.component.css']
})
export class KidsReportComponent implements OnInit {
  public datasets: any;
  public data: any;
  public salesChart;
  public clicked: boolean = true;
  public clicked1: boolean = false;
  kid:Kid
  
  constructor(private _auth:authentication,private router:Router,private kidService:KidsService, private route:ActivatedRoute) {}

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
  
  getKidById(kidId: number) {
    this.kidService.kidById(kidId).subscribe(data=>{
      this.kid = data
      this.kid=this.calculateAge(data)
      console.log(this.kid);
      
    },error=>{
      this.handleError(error)
    })
  }

  ngOnInit(): void {
    this.kid = new Kid()
    this.kid.area=new Area()
    this.getKidById( this.route.snapshot.params['id']);
    this.datasets = [
      [0, 20, 10, 30, 15, 40, 20, 60, 60],
      [0, 20, 5, 25, 10, 30, 15, 40, 40]
    ];
    this.data = this.datasets[0];


    var chartOrders = document.getElementById('chart-orders');

    parseOptions(Chart, chartOptions());


    var ordersChart = new Chart(chartOrders, {
      type: 'bar',
      options: chartExample2.options,
      data: chartExample2.data
    });


    var chartPie = document.getElementById('chart-pie');

    parseOptions(Chart, chartOptions());

    var personalityChart = new Chart(chartPie, {
      type: 'pie',
      options: chartExample3.options,
      data: chartExample3.data
    });
    
    var chartSales = document.getElementById('chart-sales');

    this.salesChart = new Chart(chartSales, {
			type: 'line',
			options: chartExample1.options,
			data: chartExample1.data
		});
  }


  public updateOptions() {
    this.salesChart.data.datasets[0].data = this.data;
    this.salesChart.update();
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

}