import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'kids',
  pure:false
})
export class KidsPipe implements PipeTransform {

  transform(value: any, searchKey : string): unknown {
    if(value.length === 0)
    {
        return value
    }
    return value.filter(function(search){

      let exp1=new RegExp('^a[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?[atma shiksha]?$')
      let exp2=new RegExp('^a[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?[atma shodh]?$')
      let exp3=new RegExp('^a[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?[atma vishesh]?$')
     
      if(exp1.test(searchKey.toLowerCase()) )
        return  search.level==1 

        if(exp2.test(searchKey.toLowerCase()))
        return  search.level==2
        
        if(exp3.test(searchKey.toLowerCase()) )
        return  search.level==3


      return search.name.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.village.villageName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.group.groupName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.school.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.standard.toString().toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1 ||
      search.area.areaName.toLowerCase().indexOf(searchKey.toLocaleLowerCase())>-1

    });
  }

}
