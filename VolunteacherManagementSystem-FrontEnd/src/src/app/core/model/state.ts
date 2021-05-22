import { Country } from "./country";
import { District } from "./district";




export class State {
    stateId:number;
    stateName:String;
    country:Country;
    districts:District[];
}
