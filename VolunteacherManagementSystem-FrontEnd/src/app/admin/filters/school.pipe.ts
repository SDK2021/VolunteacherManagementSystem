import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'school'
})
export class SchoolPipe implements PipeTransform {

  transform(value: any , searchKey:string): unknown {
    return value.filter(function(search){
      return search.schoolName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 ||
      search.stream.toLowerCase().indexOf(searchKey.toLowerCase())>-1 
      // || search.status.toLowerCase().indexOf(searchKey.toLowerCase())>-1 
    });
  }

}
