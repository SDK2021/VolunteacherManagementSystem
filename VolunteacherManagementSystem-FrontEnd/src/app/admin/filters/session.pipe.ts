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

      let m1=new RegExp('^j[anuary]?[anuary]?[anuary]?[anuary]?[anuary]?[anuary]?$')
      let m2=new RegExp('^f[ebruary]?[ebruary]?[ebruary]?[ebruary]?[ebruary]?[ebruary]?[ebruary]?$')
      let m3=new RegExp('^m[arch]?$[arch]?$[arch]?$[arch]?$')
      let m4=new RegExp('^a[pril]?[pril]?[pril]?[pril]?$')
      let m5=new RegExp('^m[ay]?[ay]?[ay]?$')
      let m6=new RegExp('^j[une]?[une]?[une]?$')
      let m7=new RegExp('^j[uly]?[uly]?[uly]?$')
      let m8=new RegExp('^a[ugust]?[ugust]?[ugust]?[ugust]?[ugust]?$')
      let m9=new RegExp('^s[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?[eptember]?$')
      let m10=new RegExp('^o[ctober]?[ctober]?[ctober]?[ctober]?[ctober]?[ctober]?$')
      let m11=new RegExp('^n[ovember]?[ovember]?[ovember]?[ovember]?[ovember]?[ovember]?[ovember]?$')
      let m12=new RegExp('^d[ecember]?[ecember]?[ecember]?[ecember]?[ecember]?[ecember]?[ecember]?$')

      
      //extract Date Year Country ? State ? Area , village , district
      return serach.project.projectName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1
      ||  serach.village.villageName.toLowerCase().indexOf(searchKey.toLowerCase()) > -1

    })
  }

}
