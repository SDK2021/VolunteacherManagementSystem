import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'event'
})
export class EventPipe implements PipeTransform {

  transform(value: any, searchKey: string): unknown {
    //by adress year month 

    if(value.length===0)
    {
      return value
    }
    return value.filter(function(search){
      return search.title.toLowerCase().indexOf(searchKey.toLowerCase())>-1
    });
  }

}
