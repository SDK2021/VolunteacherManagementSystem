import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.css']
})
export class TabsComponent implements OnInit {
  
  tab1Class:boolean=false
  tab2Class:boolean=true

  label1:string=''
  label2:string=''

  @Output() showTab_1=new EventEmitter<boolean>();
  @Output() showTab_2=new EventEmitter<boolean>();


  constructor(private router:Router) { 
  
  }

  ngOnInit(): void {
        if(this.router.url.endsWith('attendance'))
        {
          this.label1="Kids"
          this.label2="Volunteachers"
        }
        else
        {
          this.label1="Create"
          this.label2="View"
        }
  }

  showTab1()
  {
    this.tab2Class=false
    this.tab1Class=true
    this.showTab_2.emit(false)
    this.showTab_1.emit(true)
  }
   showTab2()
  {
    this.tab1Class=false
    this.tab2Class=true
    this.showTab_1.emit(false)
    this.showTab_2.emit(true)
  }


}
