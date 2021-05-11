
import { Area } from "./area";
import { Taluka } from "./taluka";

export class Village {
    villageId:number;
    villageName:String;
    taluka:Taluka;
    areas:Array<Area>
}
