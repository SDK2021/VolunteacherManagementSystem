import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-set-kids-photo',
  templateUrl: './set-kids-photo.component.html',
  styleUrls: ['./set-kids-photo.component.css']
})
export class SetKidsPhotoComponent implements OnInit {

  baseUrl:string="/vms/kids/profile"
  constructor() { }

  ngOnInit(): void {
  }

}
