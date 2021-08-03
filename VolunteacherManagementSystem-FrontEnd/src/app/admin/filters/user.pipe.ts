import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'user'
})
export class UserPipe implements PipeTransform {

  transform(value: any, searchKey: string): unknown {
    if (value.length === 0) {
      return value
    }
    return value.filter(function (search) {
      return search.type.type.toLowerCase().indexOf(searchKey.toLocaleLowerCase()) > -1 ||
        search.userName.toLowerCase().indexOf(searchKey.toLocaleLowerCase()) > -1
    })
  }

}
