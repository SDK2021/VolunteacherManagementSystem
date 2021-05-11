import { Time } from "@angular/common";
import { Activity } from "./activity";
import { Kid } from "./kid";
import { Participant } from "./participant";
import { Project } from "./project";
import { User } from "./user";
import { Village } from "./village";


export class Event {
	eventId: number;
	title: String;

	eventData: String;

	eventDate: string;

	eventStartingTime: string;

	eventEndingTime: string;

	project: Project;

	participants: Participant[];

	village: Village;

	kids: Kid[];

	users:User[]

	notification: Notification;

	activities: Activity[];

	photo:string;

	notified:boolean

	disable:boolean

}
