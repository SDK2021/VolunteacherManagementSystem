import { Requirement } from "./requirement";
import { Village } from "./village";




export class School {

    schoolId:number;
    schoolName:string;
    pincode:number;
    phoneNumber:string;
    startingDate:string;
    type:String;
    totalTeachers:number;
    lab:boolean;
    grade:string;
    totalStudent:number;
    status:number;
    village:Village;
    requirements:Requirement[]; 

}
