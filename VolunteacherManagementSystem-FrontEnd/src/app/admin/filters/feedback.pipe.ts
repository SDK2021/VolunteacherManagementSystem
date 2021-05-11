import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'feedback'
})
export class FeedbackPipe implements PipeTransform {

  transform(value: any , searchKey : string): unknown {
    if(value.length === 0)
    {
      return value
    }
    return value.filter(function(search){
      return search.user.userName.toLowerCase().indexOf(searchKey.toLowerCase())>-1
    });
  }

}
