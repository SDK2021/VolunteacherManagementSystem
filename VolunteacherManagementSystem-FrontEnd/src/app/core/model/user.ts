
import { Announcement } from "./announcement";
import { Kidsreport } from "./kidsreport";
import { Project } from "./project";
import { Session } from "./session";
import { Sessionreport } from "./sessionreport";
import { Timelinepost } from "./timelinepost";
import { Usertype } from "./usertype";
import { Volunteacher } from "./volunteacher";


export class User {
	userId: number;

	userName: String;

	email: String;

	gender: number;

	phoneNumber: String;

	dob: string;

	password: String;

	type: Usertype;
	photo: String;
	kidsReports: Kidsreport[];

	announcement: Announcement[];

	sessionReports: Sessionreport[];

	volunteacher: Volunteacher;

	posts: Timelinepost[];

	projectList: Project[];

	sessionList: Session[];

}
