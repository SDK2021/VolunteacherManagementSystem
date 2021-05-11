import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'session'
})
export class SessionPipe implements PipeTransform {

  transform(value: any , searchKey:string): unknown {
    if(value.length === 0)
    {
        return value
    }
    return value.filter(function(serach){
      //extract Date Year Country ? State ? Area , village , district
      return serach.project.projectName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1
      ||  serach.village.villageName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1

    })
  }

}
