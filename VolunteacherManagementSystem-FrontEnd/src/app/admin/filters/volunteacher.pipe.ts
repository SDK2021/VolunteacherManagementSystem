import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'volunteacher',
  pure:false
})
export class VolunteacherPipe implements PipeTransform {

  transform(value:any, searchKey:string): unknown {
    
    if(value.length === 0)
    {
      return value
    }
    return value.filter(function(search){

       let exp1=new RegExp('^a[ctive]?[ctive]?[ctive]?[ctive]?[ctive]?$')
       let exp2=new RegExp('^i[nactive]?[nactive]?[nactive]?[nactive]?[nactive]?$')
    
      if(exp1.test(searchKey.toLowerCase()))
        return search.status==1

      if(exp2.test(searchKey.toLowerCase()))
        return search.status==2

      if(search.user.type.type=='VOLUNTEACHER')
        return search.district.districtName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 || 
        search.user.userName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 || 
        search.employerName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 

      if(search.user.type.type=='LOCAL VOLUNTEACHER')
        return search.village.villageName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 ||
        search.user.userName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 || 
        search.school.toLowerCase().indexOf(searchKey.toLowerCase())>-1  

        

      //return 

    })
  }

}
