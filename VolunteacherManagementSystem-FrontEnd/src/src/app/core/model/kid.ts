
import { Attendance } from "./attendance";
import { KidsGroup } from "./kids-group";
import { Kidsreport } from "./kidsreport";
import { Project } from "./project";
import { School } from "./school";
import { Area } from "./area";
import { Village } from "./village";

export class Kid {

    kidId: number;
    name: String;
    gender: number;
    dob: string;
    standard: number;
    level:number;
    
    photo:string;
    school:string;
    village:Village;
    group:KidsGroup;
    
    projects:Project[];
    area:Area;
    events:Event[];
    attendances:Attendance[];
    kidsreport:Kidsreport[];
    age:number
    totalSessions:number;

    attendance:boolean=false
}

