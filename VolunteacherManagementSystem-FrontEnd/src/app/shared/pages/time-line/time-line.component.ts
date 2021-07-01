import { Component, OnInit } from '@angular/core';
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
  liked:string;
  isLiked=false
  userType:string

  disabled:boolean=null

  showImageSpinner:boolean=true

  page:number=0
  totalPostsPages:number

  constructor(private _sharedservice:TimeLineService,private router:Router) {

    this.liked="text-dark"
   }

  ngOnInit(): void {
    this.page=0
    this.getPosts(this.page)
    this.userType=this.getUserType()
  }

  navigate()
  {
    this.router.navigate(['/user/posts/create-post'])
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

  
  load()
  {
    this.showImageSpinner=false
  }
  getPosts(page:number)
  {
    this.showSpinner=true
    this._sharedservice.getTimelinePosts(page).subscribe(data =>{
      this.posts=data['content'];
      this.totalPostsPages = data['totalPages']
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
      this.handleError(error)
    })
  }
  // like(id:number):void{
  //   if(this.posts[id].isLiked==false)
  //   {
  //     this.liked="text-danger"
  //     this.posts[id].isLiked=true
  //   }
  //   else
  //   {
  //     this.liked="text-dark"
  //     this.posts[id].isLiked=false
  //   }
      
  // }

  getUserType():string
  {
      let array:Array<string>=new Array()
      array=this.router.url.split('/') 
      return array[1]     
  }

 

  onScroll() {
    console.log("Hello");
    
    if(this.page < this.totalPostsPages - 1)
    {
      this.page += 1
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
