import { Requirement } from "./requirement";
import { Village } from "./village";




export class School {

    schoolId:number;
    schoolName:string;
    pincode:number;
    totalLabs:number;
    phoneNumber:string;
    startingDate:string;
    stream:String;
    totalStudent:number;
    status:number;
    village:Village;
    requirements:Requirement[]; 

}
