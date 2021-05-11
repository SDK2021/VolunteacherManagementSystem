import { Time } from "@angular/common";
import { User } from "./user";

export class Announcement {

    announcementId:number;
    data:String;
    createdBy:User;
    creationDate:string;
    creationTime:string;
}
