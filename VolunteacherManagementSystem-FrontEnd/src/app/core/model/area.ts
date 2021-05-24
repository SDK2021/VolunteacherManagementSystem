import { Kid } from "./kid";
import { Village } from "./village";

export class Area {
    areaId:number
    areaName:string
    village:Village;
    kids:Kid[];

    isEdit:boolean=false
    isDelete:boolean = false
}
