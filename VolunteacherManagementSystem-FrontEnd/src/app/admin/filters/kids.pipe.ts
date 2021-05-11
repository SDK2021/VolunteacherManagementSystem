import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'kids'
})
export class KidsPipe implements PipeTransform {

  transform(value: any, searchKey : string): unknown {
    if(value.length === 0)
    {
        return value
    }
    return value.filter(function(search){
      return search.name.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.village.villageName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.group.groupName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.school.schoolName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.standard.toString().toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.level.toString().toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.area.areaName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1

    });
  }

}
