import { User } from "./user";


export class Timelinepost {
     postId:number;
	
	 postPhoto:String;
	
	 postDescription:String;
	
	 creationDate:string;

	 likes:number;

	 createdBy:User;

	 isLiked:boolean=false;

	 isEdit:boolean=false;
}
