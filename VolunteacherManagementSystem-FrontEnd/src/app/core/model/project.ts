
import { Time } from "@angular/common";
import { Kid } from "./kid";
import { Session } from "./session";
import { User } from "./user";
import { Volunteacher } from "./volunteacher";


export class Project {
	projectId: number;

	projectName: String;

	description:String;
	
	startingDate: string;

	endingDate: string;

	projectData: string;

	creationDate: string;

	totalSessions: Number;

	totalVolunteachers: Number;

	totalKids: Number;

	sessions: Session[];

	events: Event[];

	creationTime: string;
	
	users: User[];
	
	kids: Kid[];

	photo:string
}
