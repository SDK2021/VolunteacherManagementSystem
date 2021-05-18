
import { Area } from "./area";
import { Taluka } from "./taluka";

export class Village {
    villageId:number;
    villageName:String;
    taluka:Taluka;
    areas:Array<Area>
    totalKids:number = 0
    totalVolunteachers:number = 0
    totalSessions:number = 0
    totalLVTS:number = 0
    
}
