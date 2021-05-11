import { Payment } from "./payment";
import { Usertype } from "./usertype";


export class Donor {
    donorId:number;

	donorName:String;

	donorPhone:String;

	donorEmail:String;

	userType:Usertype;
	
	payment:Payment[];


}

