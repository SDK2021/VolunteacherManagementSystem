import { Activity } from "./activity";
import { Event } from "./event";
import { User } from "./user";
import { Usertype } from "./usertype";


export class Participant {
     participantId:number;
	
	 name:String;
	
	 email:String;
	
	 gender:number;
	
	 phoneNumber:String;
	
	 dob:string;
	
     type:Usertype;
	
	 event:Event;
	
	 activities:Activity[];
	
	 user:User;

}
