import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Chart from 'chart.js';
import { Area } from 'src/app/core/model/area';
import { Kid } from 'src/app/core/model/kid';
import { Kidsreport } from 'src/app/core/model/kidsreport';
import { KidsService } from 'src/app/kids/shared-services/kids.service';
import { colors, chartOptions, parseOptions } from 'src/app/variables/charts';

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
  showkidsReportComparision: boolean = true
  
  kidReport:Kidsreport=new Kidsreport()

  showSpinner:boolean=false

  personalityChart:any

  kidReports:Array<Kidsreport>=new Array()


  constructor(private route:ActivatedRoute,private kidsService:KidsService,private router:Router) {}

  

  ngOnInit(): void {

   
    
    
    this.getAllKidsReport()

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

    
    var chartSales = document.getElementById('chart-sales');

    // this.salesChart = new Chart(chartSales, {
		// 	type: 'line',
		// 	options: chartExample1.options,
		// 	data: chartExample1.data
		// });

    this.getKidReportById(this.route.snapshot.params['rid'])
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

      var chartPie = document.getElementById('chart-pie');
      this.personalityChart = new Chart(chartPie, {
        type: 'pie',
        options: {

        },
        data: {
          labels: ["Discipline", "Goshthi", "Abhivyakti"],
          datasets: [
            {
              data: [this.kidReport.discipline, this.kidReport.goshthi, this.kidReport.abhivyakti],
              backgroundColor: [
                "#fff200",
                "hotpink",
                "#5e72e4"
              ]
            }
          ],

        }
      })
      var chartOrders = document.getElementById('chart-orders');

      var performanceChart = new Chart(chartOrders, {
        type: 'bar',
        options: {
          scales: {
            yAxes: [
              {
                ticks: {
                  callback: function (value) {
                    if (!(value % 10)) {
                      return value;
                    }
                  }
                }
              }
            ]
          },
          tooltips: {
            callbacks: {
              label: function (item, data) {
                var label = data.datasets[item.datasetIndex].label || "";
                var yLabel = item.yLabel;
                var content = "";
                if (data.datasets.length > 1) {
                  content += label;
                }
                content += yLabel;
                return content;
              }
            }
          }
        },
        data: {
          labels: ["Prayer", "Games", "Sports", "Science", "Art", "Volunteaching"],
          datasets: [
            {
              label: "Performance",
              data: [this.kidReport.prayer, this.kidReport.games, this.kidReport.sports, this.kidReport.science, this.kidReport.artCraft, this.kidReport.volunteaching]
            }
          ]
        }
      })
    

      
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
  getAllKidsReport() {
    this.kidsService.getKidReport(this.route.snapshot.params['id']).subscribe(data => {
      this.kidReports = data
      console.log(this.kidReports);
      var chartProgress = document.getElementById('chart-sales')
      if (this.kidReports.length >= 3) {
        console.log("Hello");
        
         this.showkidsReportComparision = true

        let totalReport1: number = ((this.kidReports[0].abhivyakti + this.kidReports[0].artCraft + this.kidReports[0].discipline + this.kidReports[0].english + this.kidReports[0].games
          + this.kidReports[0].goshthi + this.kidReports[0].gujarati + this.kidReports[0].science + this.kidReports[0].maths + this.kidReports[0].prayer + this.kidReports[0].sports + this.kidReports[0].volunteaching) * 100) / 120

        let totalReport2: number = ((this.kidReports[1].abhivyakti + this.kidReports[1].artCraft + this.kidReports[1].discipline + this.kidReports[1].english + this.kidReports[1].games
          + this.kidReports[1].goshthi + this.kidReports[1].gujarati + this.kidReports[1].science + this.kidReports[1].maths + this.kidReports[1].prayer + this.kidReports[1].sports + this.kidReports[1].volunteaching) * 100) / 120

        let totalReport3: number = ((this.kidReports[2].abhivyakti + this.kidReports[2].artCraft + this.kidReports[2].discipline + this.kidReports[2].english + this.kidReports[2].games
          + this.kidReports[2].goshthi + this.kidReports[2].gujarati + this.kidReports[2].science + this.kidReports[2].maths + this.kidReports[2].prayer + this.kidReports[2].sports + this.kidReports[2].volunteaching) * 100) / 120

        console.log(+totalReport1 + " " + +totalReport3 + " " + +totalReport2);
       
        

        let progressChart = new Chart(chartProgress, {
          type: 'line',
          options: {
            scales: {
              yAxes: [{
                gridLines: {
                  color: colors.gray[900],
                  zeroLineColor: colors.gray[900]
                },
                ticks: {
                  callback: function (value) {
                    if (!(value % 10)) {
                      return value;
                    }
                  }
                }
              }]
            }
          },
          data: {
            labels: ['Report1', 'Report2', 'Report3'],
            datasets: [{
              label: 'Performance',
               data: [Math.round(totalReport1), Math.round(totalReport2), Math.round(totalReport3)]
              //data: [25,25,25]
            }]
          }
        })
      }
      else
      {
        this.showkidsReportComparision = false
      }
    },error=>{
      this.handleError(error)
    })

  }
}
