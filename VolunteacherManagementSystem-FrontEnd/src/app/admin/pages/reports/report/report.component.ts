import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import  pdfMake  from 'pdfmake/build/pdfmake';
import pdfFonts  from 'pdfmake/build/vfs_fonts';
import { DashboardService } from 'src/app/admin/shared-services/dashboard.service';
import { ReportService } from 'src/app/admin/shared-services/report.service';
import { Village } from 'src/app/core/model/village';
import { AddressService } from 'src/app/shared/shared-services/address.service';
pdfMake.vfs = pdfFonts.pdfMake.vfs;
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  array:number[]=[1,2,3,4,5,6]
  dd: any;
  constructor(private addressService:AddressService, private reportService:ReportService, private router:Router, private dashboardService:DashboardService) { }

  totalVolunteachers: number
  totalKids: number
  totalSessions: number
  totalEvents: number
  showSpinner:boolean=false
  totalVTS:number 
  totalLVTS:number
  totalNewKids:number
  totalHours:number
  year:number
  villages:Village[] = new Array()
  ngOnInit(): void {
    this.year = new Date().getFullYear()
    this.totalLVTS = 0
    this.totalVTS = 0
    this.getTotalEvents()
    this.getTotalKids()
    this.getTotalSessions()
    this.getTotalVolunteachers()
    this.getAllNewVolunteacher()
    this.getAllNewKids()
    this.getTotalHours()
    this.getInfoVillage()
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
      this.showSpinner=false;
    },error=>{
      this.handleError(error)
    })
  }

  getTotalKids() {
      this.dashboardService.getTotalKids().subscribe(data=>{
        this.totalKids=data
      },error=>{
        this.handleError(error)
      })
  }

  getTotalSessions() {
    this.showSpinner=true;
    this.dashboardService.getTotalSesssions().subscribe(data=>{
      this.totalSessions=data
      this.showSpinner=false;
    },error=>{
      this.handleError(error)
    })

  }

  getTotalEvents() {
    this.showSpinner=true;
    this.dashboardService.getTotalEvents().subscribe(data=>{
      this.totalEvents=data
      this.showSpinner=false;
    },error=>{
      this.handleError(error)
    })
  }

  getAllNewVolunteacher()
  {
    
    this.reportService.getAllNewUsers().subscribe(data=>{
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
      this.totalNewKids = data
    })
  }

  getTotalHours()
  {
    this.reportService.getTotalHours().subscribe(data=>{
      this.totalHours = data
    })
  }

  getInfoVillage()
  {
    this.addressService.getAllVillages().pipe(finalize(()=>{
      for (let village of this.villages) {
          this.reportService.getTotalKidsByVillage(village.villageId).subscribe(data=>{
            village.totalKids = data
          })

          this.reportService.getTotalSessionByVillage(village.villageId).subscribe(data=>{
            village.totalSessions = data
          })

          this.reportService.getTotalUsersBySessionVillage(village.villageId).subscribe(data=>{
         
            village.totalVolunteachers = 0
            village.totalLVTS = 0
            for (let user of data) 
            {
              if(user.type.typeId == 2)
              {
                village.totalVolunteachers += 1
              } 
              
              if(user.type.typeId == 3)
              {
                village.totalLVTS += 1
              }
            }
            
          })
      }
    })).subscribe(data=>{
      this.villages = data
    })
  }

  getInfoByVillage()
  {
    
     this.addressService.getAllVillages().pipe(finalize(()=>{
      for (let village of this.villages) 
      {
        this.reportService.getTotalKidsByVillage(village.villageId).subscribe(data=>{
          village.totalKids = data
        })
      }
      

      for (let village of this.villages) 
      {
        this.reportService.getTotalSessionByVillage(village.villageId).subscribe(data=>{
          village.totalSessions = data
        })
      }
      for (let village of this.villages) 
      {
          this.reportService.getTotalUsersBySessionVillage(village.villageId).subscribe(data=>{
            village.totalVolunteachers = 0
            village.totalLVTS = 0
            for (let user of data) 
            {
              if(user.type.typeId == 2)
              {
                village.totalVolunteachers += 1
              } 
              
              if(user.type.typeId == 3)
              {
                village.totalLVTS += 1
              }
            }
            
            this.dd.content.push(
            {
              text: '\n' + village.villageName,style:"villageHeader"
            },
            {
              style: 'tableExample',
              table: {
              headerRows: 1,
              body: 
              [
                [{text: 'Volunteachers', style: 'tableHeader'}, {text: 'Local Volunteachers', style: 'tableHeader'}, {text: 'Kids', style: 'tableHeader'},{text:"Sessions",style:"tableHeader"}],
                [{text: village.totalVolunteachers,style:'row'}, {text: village.totalLVTS,style:'row'}, {text: village.totalKids,style:'row'},{text:village.totalSessions,style:'row'}]
              ]
              }
            })
        }) 
      } 
    })).subscribe(data=>{
      this.villages = data
    })
  }
 disabled:boolean=false
 showProgressbar:boolean=false
  generateReport(){
    this.showProgressbar=true
    this.disabled=true
      this.getInfoByVillage()
     // playground requires you to assign document definition to a variable called dd
  this.dd = {
  	content: [
	    {
	      text:[{text: 'AIREP Annual Report ' , style: 'header'}, {text:this.year, style: 'header'}]  		 
	    },
	    {
	        text: '_______________________________________________________________________________________________\n',      
	        style:'hr'
	    },
      {
        text: '_______________________________________________________________________________________________\n',      
        style:'hr1'
      },
	    {
			style: 'p1',
			italics: false,
			text: [
				'We can also mix named-styles and style-overrides at both paragraph and inline level. ',
				'For example, this paragraph uses the "bigger" style, which changes fontSize to 15 and sets italics to true. ',
				'Texts are not italics though. It\'s because we\'ve overriden italics back to false at ',
				'the paragraph level. \n\n',  
				'We can also mix named-styles and style-overrides at both paragraph and inline level. ',
				'For example, this paragraph uses the "bigger" style, which changes fontSize to 15 and sets italics to true. ',
				'Texts are not italics though. It\'s because we\'ve overriden italics back to false at ',
				'the paragraph level. \n\n',  
	      ]
	    },
	    {
	          text:'_______________________________________________________________________________________________\n\n',color:'grey'
	    },
      
	   {  	
	       
			columns: [
				{
					alignment:'center',
					fontSize:40,
					color:'blue',
					text: this.totalVolunteachers
				},
				{
					alignment:'center',
					fontSize:40,
					color:'yellow',
					text: this.totalKids
				},
				{
					alignment:'center',
					fontSize:40,
					color:'red',
					text: this.totalSessions
				},
				{
					alignment:'center',
					fontSize:40,
					color:'green',
					text: this.totalEvents
				},
			]
		},
		{  	
			columns: [
				{
					fontSize:15,
					alignment:'center',
					text: 'Volunteachers'
				},
				{
					alignment:'center',
					fontSize:15,
					text: ' Kids'
				},
				{
			    	alignment:'center',
					fontSize:15,
					text: 'Sessions'
				},
				{
    				alignment:'center',
					fontSize:15,
					text: 'Events'
				},
			]
		},
		{
	          text:'\n_______________________________________________________________________________________________\n\n',color:'grey'
	    },
	    {  	
	       style:'totalvt',
			columns: [
				{
          alignment:'center',
					text: 'New Volunteachers'
				},
				{
          alignment:'center',
					text: ' New Kids'
				},
				{
          alignment:'center',
					text: 'Total Hours'
				},
			
			]
		},
		 {  	
	       
			columns: [
				{
					alignment:'left',
					fontSize:13,
					text: [{text:"\nThis year  ",color:"gray"},
          {text:this.totalVTS, style:"data"},
           {text:"  new volunteachers  ",color:"gray"},
           {text: this.totalLVTS ,style:"data"},
           {text:"  local volunteachers have joined AIREP.",color:"gray"}
        ]
				},
				{
          alignment:'left',
          margin:[20,0,0,0],
					fontSize:13,
					text: [{text:"\nThis year  ",color:"gray"},
          {text:this.totalNewKids,style:"data"},
          {text:"  new students have joined AIREP.",color:"gray"}]
				},
				{
					alignment:'left',
					fontSize:13,
					text: [{text:"\nThis year AIREP has done  ",color:"gray"}, 
          {text:this.totalHours,style:"data"},
          {text:"  hours of teaching.",color:"gray"}]
				},
			
			],
      
		},
    {
      text: "\n\nVillage Data",alignment:"center",fontSize:20
    },
		],
    	styles: {
		header: {
			fontSize: 38,
			bold: true,
            alignment: 'center',
            color: 'black'
		},
		hr: {
			fontSize: 12,
			margin:0,
			padding:0,
			bold: true,
      color:"brown"
		},
    hr1: {
			fontSize: 12,
			margin:0,
			padding:0,
			bold: true,
      color:"orange"
		},
		quote: {
			italics: true
		},
		small: {
			fontSize: 8
		},
		tableExample: {
			margin: [90, 10, 0, 10],
			alignment: 'justify'
		},
		p1: {
			fontSize: 15,
			margin:15,
			alignment: 'justify',
		},
		tableHeader: {
		  margin: [2, 5, 3,4],
			bold: true,
			fontSize: 13,
			color: 'black',
      alignment:'center'
		},
    row:{
      alignment:'center'
    },
		totalvt:{
		    alignment:'justify',
			fontSize:16,
			bold: true,
		},
    villageHeader:{
      fontSize: 18,
      alignment:'center',
      color:"gray",
      bold:true
    },
    data:{
      fontSize: 16,
      bold:true, 
    }
     },
    defaultStyle: {
		columnGap: 20
	}
 }; 
  setTimeout(() => {
    pdfMake.createPdf(this.dd).download("AIREP Report")
    this.showProgressbar=false
    this.disabled=false
  }, 5000);
  }
}
