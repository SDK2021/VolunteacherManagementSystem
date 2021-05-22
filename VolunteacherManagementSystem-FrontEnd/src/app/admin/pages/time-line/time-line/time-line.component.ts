import { Component, OnInit } from '@angular/core';
import { throwMatDialogContentAlreadyAttachedError } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { TimeLineService } from 'src/app/shared/shared-services/time-line.service';

@Component({
  selector: 'app-time-line',
  templateUrl: './time-line.component.html',
  styleUrls: ['./time-line.component.css']
})
export class TimeLineComponent implements OnInit {

  posts:Array<Timelinepost>;
  pLength:number
  showSpinner:boolean=false
  noPosts:boolean=false
  page:number=0
  totalPostPages:number
  constructor(private router:Router,private _sharedservice:TimeLineService) { }

  ngOnInit(): void {
    this.page=0
    this.getPosts(this.page)
  }

  getPosts(page:number)
  {
    this.showSpinner=true
    this._sharedservice.getTimelinePosts(page).subscribe(data =>{
      this.posts=data['content'];
      this.totalPostPages = data['totalPages']
      this.showSpinner=false
      if (data != null) {
        this.pLength = this.posts.length
        this.noPosts=false
      }
      //this.pLength=0
      if(this.pLength==0)
      {
        this.noPosts=true
      }
      
      console.log(this.posts)
    },error=>{
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
    });
  }
  
  onScroll()
  {
    if(this.page < this.totalPostPages - 1)
    {
      this.page +=1
      this.getPageablePosts(this.page);
    }
  }
  getPageablePosts(page: number) {
    this._sharedservice.getTimelinePosts(page).subscribe(data =>{
      data['content'].forEach(post => {
        this.posts.push(post)
      });
    })
  }

  
}
