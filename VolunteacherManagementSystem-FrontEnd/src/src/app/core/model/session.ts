import { Time } from "@angular/common";
import { Attendance } from "./attendance";
import { Kid } from "./kid";
import { Project } from "./project";
import { Sessionreport } from "./sessionreport";
import { User } from "./user";
import { Village } from "./village";
import { Volunteacher } from "./volunteacher";


export class Session {
	sessionId: number;

	sessionDate: string;

	startingTime: string;

	endingTime: string;

	creationDate: string;

	project: Project;

	attendance: Attendance[];

	sessionReports: Sessionreport[];

	village: Village;

	users: User[];

	//convert to boolean
	notified: boolean;

	disable:boolean

}
