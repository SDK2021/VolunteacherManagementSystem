
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionsService } from 'src/app/admin/shared-services/sessions/sessions.service';
import { Content } from 'src/app/core/model/content';
import { KidsGroup } from 'src/app/core/model/kids-group';
import { KidsService } from 'src/app/kids/shared-services/kids.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  groups : Array<KidsGroup>
  contents:Content[]=new Array()
  showSpinner:boolean = true
  groupId:number
  pdfSource:String=''
  shows:boolean = false
  constructor(private router: Router,private _sharedservice:KidsService,private sessionService:SessionsService) { 
    
  }

  ngOnInit(): void {
    this.getkidsgroup()
    this.getContent(1)
    this.getAllContents()
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

  getkidsgroup()
  {
    this._sharedservice.getkidsgrouplist().subscribe(data =>{
      this.groups=data;
    },error=>{
      this.handleError(error)
    })
    
  }

  load()
  {
    this.showSpinner = false
  }
  
  getContent(groupId:number)
  {
      this.shows = true
      this.sessionService.getContentByGroup(groupId).subscribe(data=>{
      this.pdfSource = data.contentData
      },error=>{
        this.handleError(error)
      })
  }

  getAllContents()
  {
    this.sessionService.getAllContents().subscribe(data=>{
      this.contents=data
      },error=>{
        this.handleError(error)
      })
  }

  groupSelected(event)
  {
    this.groupId = event.target.value
    this.getContent(this.groupId)
  }

}
