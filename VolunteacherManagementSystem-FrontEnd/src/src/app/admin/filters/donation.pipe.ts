import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'donation',
  pure:false
})
export class DonationPipe implements PipeTransform {

  transform(value: any, searchKey:string): unknown {
    if(value.length === 0)
    {
      return value
    }
    return value.filter(function(search){
      
      return search.donor.userType.type.toLowerCase().indexOf(searchKey.toLowerCase())>-1  ||
     search.donor.donorName.toLowerCase().indexOf(searchKey.toLowerCase())>-1 
    });
  }

}
