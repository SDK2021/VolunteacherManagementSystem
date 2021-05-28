import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Timelinepost } from 'src/app/core/model/timelinepost';
import { User } from 'src/app/core/model/user';
import { TimeLineService } from 'src/app/shared/shared-services/time-line.service';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { ProfileService } from 'src/app/shared/shared-services/profile.service';
import { UsersService } from 'src/app/user/services/users.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Usertype } from 'src/app/core/model/usertype';
import { MatDialog } from '@angular/material/dialog';
import { FileUploadService } from 'src/app/core/services/file-upload.service';
import { DialogBoxComponent } from 'src/app/admin/components/dialog-box/dialog-box.component';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  urlType:string
  post: Timelinepost = new Timelinepost()
  posts: Array<Timelinepost>=new Array();
  liked: string;
  isLiked = false
  user: User = new User()
  postEdit:boolean=true
  tempPost:Array<Timelinepost>
  page:number=0
  totalPostsPages:number
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  showSpinner:boolean=false

  isAdminProfile:boolean=false

  showProgressbar:boolean=false

  showImageSpinner:boolean=true
  constructor(private dialog:MatDialog,private fileService:FileUploadService,private _snackBar: MatSnackBar,private postService:TimeLineService,private route:ActivatedRoute,private router:Router,private authService: authentication, private userService: UsersService, private profileService: ProfileService) { }

  ngOnInit(): void {

    this.user.type=new Usertype()
    console.log(this.router.url.split('/'));
    let array:Array<string>=this.router.url.split('/')
    console.log(array);
    if(array[1]==="admin"&&array[2]==='volunteachers')
    {
      this.urlType=array[1]
      this.getVolunteachersPost(this.route.snapshot.params['id'])
      this.postEdit=false
    }
    else
    {
      this.urlType=array[1]
      if(array[1]==="admin" && array[2]=='profile')
        this.isAdminProfile=true
      else
        this.isAdminProfile=false
      this.getposts(this.page)
    }
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
  pLength:number
  getposts(page:number) {
    this.showSpinner=true
    let username: string;
    let authuser: string[];
    let userId: number;

    authuser = localStorage.getItem(this.authService.LOCAL_STORAGE_ATTRIBUTE_USERNAME).split(" ");
    username = atob(authuser[0]);

    this.userService.getUserByEmail(username).subscribe(data => {
      console.log(data)
      this.user = data;
      userId = this.user.userId;

      this.profileService.getAllPostByUser(page,userId).subscribe(data => {
        this.posts = data['content']
        
        this.showSpinner=false
        if (data != null) {
          this.pLength = data['content'].length
          console.log(this.pLength);
          
        }
       
        console.log(this.posts);
      },error=>{
        this.handleError(error)
      })
    });
  }

  getVolunteachersPost(id:number) {
    this.showSpinner=true
      this.profileService.getAllPostByUser(this.page,id).subscribe(data => {
        this.posts = data['content'];
        this.showSpinner=false
        if (data != null) {
          this.pLength = data['content'].length
          
        }
        this.totalPostsPages = data['totalPages']
        // this.getPosts(data)
        console.log(this.posts);
      },error=>{
        this.handleError(error)
      })
  }


  like(): void {
    if (this.isLiked == false) {
      this.liked = "text-danger"
      this.isLiked = true
    }
    else {
      this.liked = "text-dark"
      this.isLiked = false
    }

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
    this.profileService.getAllPostByUser(page,this.route.snapshot.params['id']).subscribe(data => {

      data['content'].forEach(post => {
        this.posts.push(post)
      });
    })
  }

  editPost(index:number) {
    console.log(this.posts.filter(post=>post));
    
    console.log(this.posts[index]["isEdit"]=!this.posts[index]["isEdit"])
    this.post.postDescription=this.posts[index]["postDescription"]
   
  }

  getPost(postId:number)
  {
    this.profileService.postById(postId).subscribe(data=>{
      this.post = data
    })
  }

  trackByID(index,post:Timelinepost)
  {
    return post.postId
  }

  getPosts(posts:Array<Timelinepost>)
  {
      this.tempPost=posts;
  }
  deletePost(id:number,image:string)
  {
    this.showProgressbar=true
    this.postService.deleteTimelinePost(id).subscribe(data =>{
      console.log(data)
      this.fileService.delete(image)
    this.openDeleteSnackBar()
    this.getposts(this.page)
    this.showProgressbar=false

    },error=>{
      this.handleError(error)
    })

  }

  delete(id:number,image:string)
  {
    this.dialog.open(DialogBoxComponent).afterClosed().subscribe(data=>{
       console.log(data.delete)
      if(data.delete)
      { 
        this.deletePost(id,image)
      }
    })
  }

  openDeleteSnackBar() {
    this._snackBar.open('Deleted successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  openEditSnackBar() {
    this._snackBar.open('Post Edited successfully..', 'close', {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  savePost(postId:number)
  {
    this.showProgressbar=true
    this.profileService.savePost(postId,this.post).subscribe(data=>{
      console.log(data);
      this.showProgressbar=false
      let array:Array<string>=this.router.url.split('/')
      console.log(array);
      
        this.getposts(this.page)
     
      this.openEditSnackBar()
      
    },error=>{
      this.handleError(error)
    })
  }

  close(index:number)
  {
    this.posts[index]['isEdit']=false
  }
}
