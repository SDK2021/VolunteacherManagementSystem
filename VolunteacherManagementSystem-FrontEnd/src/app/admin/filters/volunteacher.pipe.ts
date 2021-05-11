import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'volunteacher'
})
export class VolunteacherPipe implements PipeTransform {

  transform(value:any, searchKey:string): unknown {
    console.log("In pipe");
    if(value.length === 0)
    {
      return value
    }
    return value.filter(function(search){
      return search.user.userName.toLowerCase().indexOf(searchKey.toLowerCase())>-1
    })
  }

}
