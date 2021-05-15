import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'school',
  pure:false
})
export class SchoolPipe implements PipeTransform {


  transform(value: any , searchKey:string): unknown {

    let status1=new RegExp('^g[ood]?[ood]?[ood]?$')
    let status2=new RegExp('^b[etter]?[etter]?[etter]?[etter]?[etter]?$')
    let status3=new RegExp('^u[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?[nder construction]?')
    return value.filter(function(search){

      if(status1.test(searchKey))
      {
          return search.status==1
      }
      if(status2.test(searchKey))
      {
          return search.status==2
      }
      if(status3.test(searchKey))
      {
          return search.status==3
      }
      return search.schoolName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 ||
      search.stream.toLowerCase().indexOf(searchKey.toLowerCase())>-1 
     
    });
  }

}
