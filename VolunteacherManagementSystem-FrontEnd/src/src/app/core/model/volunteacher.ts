
import { District } from "./district";
import { Project } from "./project";
import { School } from "./school";
import { Session } from "./session";
import { User } from "./user";
import { Village } from "./village";


export class Volunteacher {
	volunteacherId:number;

	school: School;
	employerName: String;

	status: number;

	joiningDate: string;

	endingDate: string;

	pincode: number;
	education: String;

	//One User-- for password
	user: User;

	village: Village;

	district: District;

	sessions: Session[];

	projects: Project[];

	experience:string


}
