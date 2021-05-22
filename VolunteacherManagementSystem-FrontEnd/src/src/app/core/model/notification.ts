
import { Event } from "./event";
import { Session } from "./session";
import { User } from "./user";


export class Notification {
	notificationId: number;

	notificationType: String;

	createdBy: User;

	userType: String;

	session: Session;

	event: Event;




}
