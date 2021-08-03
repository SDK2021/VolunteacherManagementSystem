import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'village',
  pure: false
})
export class VillagePipe implements PipeTransform {

  transform(value: any, searchKey: any): unknown {

    if (value.length === 0) {
      return value
    }
    return value.filter(function (search) {
      return search.villageName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1
    });
  }

}
