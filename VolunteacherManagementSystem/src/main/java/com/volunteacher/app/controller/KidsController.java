package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Attendance;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.KidsGroup;
import com.volunteacher.app.model.KidsReport;
import com.volunteacher.app.service.interfaces.AttendanceService;
import com.volunteacher.app.service.interfaces.KidService;
import com.volunteacher.app.service.interfaces.KidsReportService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class KidsController {
	
	@Autowired
	KidService kidService;
	
	@Autowired
	KidsReportService kidReportService;
	
	@Autowired
	AttendanceService attendanceService;
	

	@PostMapping("/kids")
	public ResponseEntity<Object> addKid(@RequestBody Kid kid)
	{
		return kidService.addKid(kid);
	}
	
	@GetMapping("/kids")
	public ResponseEntity<Object> getKidsList()
	{
		return kidService.kidList();
	}
	
	@GetMapping("/kids/{id}")
	public ResponseEntity<Object> getKid(@PathVariable Long id)
	{
		return kidService.kidById(id);
	}
//	
//	@GetMapping("/kids")
//	public List<Kid> getKidsByGroup(@RequestParam(name = "group") int id)
//	{
//			return kidService.kidsListByGroup(id);
//	}
//	
	@GetMapping("/villagekids")
	public ResponseEntity<Object> getKidsByGroupAndVillage(@RequestParam("villageId") int vid, @RequestParam("groupId") int gid)
	{
		return kidService.kidsListByGroupAndVillage(vid, gid);
	}
	
	@PutMapping("/kids/{id}")
	public ResponseEntity<Object> updateKid(@RequestBody Kid kid, @PathVariable Long id)
	{
		return kidService.updateKid(kid, id);
	}
	
	@DeleteMapping("/kids/{id}")
	public ResponseEntity<Object> deleteKid(@PathVariable Long id)
	{
		return kidService.deleteKid(id);
	}
	
	@GetMapping("/kids-reports")
	public ResponseEntity<Object> getKidReportList()
	{
		return kidReportService.kidReportList();
	}
	
	@PostMapping("/kids-reports")
	public ResponseEntity<Object> addkidReport(@RequestBody KidsReport kidsReport)
	{
		return kidReportService.addKidReport(kidsReport);
	}
	
	@GetMapping("kids/{id}/kid-report")
	public ResponseEntity<Object> getKidReportByKid(@PathVariable int id)
	{
		return kidReportService.kidReportByKid(id);
	}
	
	@PutMapping("/kids-reports/{id}")
	public ResponseEntity<Object> updateKidReport(@RequestBody KidsReport kidsReport,@PathVariable int id)
	{
		return kidReportService.updateKidReport(kidsReport, id);
	}
	
	@DeleteMapping("/kids-reports/{id}")
	public ResponseEntity<Object> deleteKidReport(@PathVariable int id)
	{
		return kidReportService.deleteKidReport(id);
	}
	
	@GetMapping("/kids-groups")
	public ResponseEntity<Object> getKidGroupList()
	{
		return kidService.kidGroupList();
	}
	
	@PostMapping("/kids-groups")
	public ResponseEntity<Object> addKidGroup(@RequestBody KidsGroup kidsGroup)
	{
		return kidService.addKidsGroup(kidsGroup);
	}
	
	@DeleteMapping("/kids-groups/{id}")
	public ResponseEntity<Object> deleteKidGroup(@PathVariable int id)
	{
		return kidService.deleteKidsGroup(id);
	}
	
	@PutMapping("/kids-groups/{id}")
	public ResponseEntity<Object> deleteKidGroup(@RequestBody KidsGroup kidsGroup, @PathVariable int id)
	{
		return kidService.updateKidsGroup(kidsGroup, id);
	}
	
	@GetMapping("/attendances")
	public ResponseEntity<Object> getAttendanceList()
	{
		return attendanceService.attendanceList();
	}
	
	@GetMapping("/attendance")
	public ResponseEntity<Object> getAttendanceBySessionAndGroup(@RequestParam("groupId") int gid, @RequestParam("sessionId") Long sid)
	{
		return attendanceService.attendanceByGroupAndSession(gid, sid);
	}
	
	@GetMapping("/attendances/group/{id}")
	public ResponseEntity<Object> getAttendanceBySession(@PathVariable int id)
	{
		return attendanceService.attendanceByGroup(id);
	}
	
	@PutMapping("/attendances/{id}")
	public ResponseEntity<Object> updateAttendance(@RequestBody Attendance attendance,@PathVariable Long id)
	{
		return attendanceService.updateAttendance(attendance, id);
	}
	
}
