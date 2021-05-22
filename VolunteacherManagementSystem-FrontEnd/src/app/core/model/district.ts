import { State } from "./state";
import { Taluka } from "./taluka";


export class District {
    districtId:number;
    districtName:String;
    state:State;
    talukas:Taluka[];
}
