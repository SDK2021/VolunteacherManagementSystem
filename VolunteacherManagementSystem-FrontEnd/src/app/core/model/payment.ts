import { Time } from "@angular/common";
import { Donor } from "./donor";

export class Payment {
     paymentId:number;

	 creationDate:string;

	 paymentMode:String;

	 amount:number;

	 transactionId:String;
     donor:Donor;
}
