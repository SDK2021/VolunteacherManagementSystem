import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'session',
  pure:false
})
export class SessionPipe implements PipeTransform {

  transform(value: any , searchKey:string): unknown {
    if(value.length === 0)
    {
        return value
    }
    return value.filter(function(search){

      let m1=new RegExp('^j[anuary]?[anuary]?[anuary]?[anuary]?[anuary]?[anuary]?$')
      let m2=new RegExp('^f[ebruary]?[ebruary]?[ebruary]?[ebruary]?[ebruary]?[ebruary]?[ebruary]?$')
      let m3=new RegExp('^m[arch]?[arch]?[arch]?[arch]?$')
      let m4=new RegExp('^a[pril]?[pril]?[pril]?[pril]?$')
      let m5=new RegExp('^m[ay]?[ay]?$')
      let m6=new RegExp('^j[un]?[un]?$')
      let m7=new RegExp('^j[uly]?[uly]?[uly]?$')
      let m8=new RegExp('^a[ugust]?[ugust]?[ugust]?[ugust]?[ugust]?$')
      let m9=new RegExp('^s[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?$')
      let m10=new RegExp('^o[ctober]?[ctober]?[ctober]?[ctober]?[ctober]?[ctober]?$')
      let m11=new RegExp('^n[ovember]?[ovember]?[ovember]?[ovember]?[ovember]?[ovember]?[ovember]?$')
      let m12=new RegExp('^d[ecember]?[ecember]?[ecember]?[ecember]?[ecember]?[ecember]?[ecember]?$')

      let year=new RegExp('^[0-9]{1,4}$')

      
      if(m6.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('06')
      }
      if(m1.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('01')
      }

      if(m2.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('02')
      }

      if(m5.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('05')
      }

      if(m3.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('03')
      }

      
      if(m4.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('04')
      }

      


      if(m7.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('07')
      }

      if(m8.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('08')
      }

      if(m9.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('09')
      }

      if(m10.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('10')
      }

      if(m11.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('11')
      }

      if(m12.test(searchKey.toLowerCase()))
      {
       return search.sessionDate.toString().split('-')[0].includes('12')
      }

      if(year.test(searchKey.toLowerCase()))
       return search.sessionDate.toString().split('-')[2].includes(searchKey)

      return search.project.projectName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1
      ||  search.village.villageName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1

    })
  }

}
