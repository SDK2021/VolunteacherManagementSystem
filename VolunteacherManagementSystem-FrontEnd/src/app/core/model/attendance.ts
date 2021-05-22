
import { Kid } from "./kid";
import { KidsGroup } from "./kids-group";
import { Session } from "./session";



export class Attendance {
    attendanceId:number;
	
	session:Session; 
	
	group:KidsGroup;

	kids:Kid[];
}
