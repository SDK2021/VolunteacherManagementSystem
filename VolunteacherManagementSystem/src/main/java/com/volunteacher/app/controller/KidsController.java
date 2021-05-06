package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
		System.out.println(kid);
		return kidService.addKid(kid);
	}
	
	@GetMapping("/kids")
	public ResponseEntity<Object> getKidsList(@RequestParam("page") int id)
	{
		return kidService.kidList(id);
	}
	
	@GetMapping("/kids/{id}")
	public ResponseEntity<Object> getKid(@PathVariable Long id)
	{
		return kidService.kidById(id);
	}
	
	@GetMapping("/group-kids")
	public ResponseEntity<Object> getKidsByGroup(@RequestParam(name = "group") int gid)
	{
			return kidService.kidsListByGroup(gid);
	}
	
	@GetMapping("/village-kids")
	public ResponseEntity<Object> getKidsListByVillage(@RequestParam("village") int vid)
	{
		return kidService.kidsListByVillage(vid);
	}
	
	@GetMapping("/area-kids")
	public ResponseEntity<Object> getKidsListByArea(@RequestParam("area") int aid)
	{
		return kidService.kidsListByArea(aid);
	}
	
	@GetMapping("/village-group-kids")
	public ResponseEntity<Object> getKidsListByGroupAndVillage(@RequestParam("village") int vid, @RequestParam("group") int gid)
	{
		return kidService.kidsListByVillageAndGroup(vid, gid);
	}
	
	@GetMapping("/village-area-kids")
	public ResponseEntity<Object> getKidsListByVillageAndArea(@RequestParam("village") int vid, @RequestParam("area") int aid)
	{
		return kidService.kidsListByVillageAndArea(vid, aid);
	}
	
	@GetMapping("/area-group-kids")
	public ResponseEntity<Object> getKidsListByAreaAndGroup(@RequestParam("area") int aid, @RequestParam("group") int gid)
	{
		return kidService.kidsListByAreaAndGroup(aid, gid);
	}

	@GetMapping("/area-group-village-kids")
	public ResponseEntity<Object> getKidsListByVillageAndGroupAndArea(@RequestParam("area") int aid, @RequestParam("group") int gid,@RequestParam("village") int vid)
	{
		return kidService.kidsListByAreaAndGroupAndVillage(aid, gid, vid);
	}
	
	@GetMapping("/level-kids")
	public ResponseEntity<Object> getKidsListByLevel(@RequestParam("level") int level)
	{
		return kidService.kidsListByLevel(level);
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
	
	@GetMapping("/total-kids")
	public ResponseEntity<Object> getTotalKids()
	{
		return kidService.getTotalKids();
	}
	
	@GetMapping("/kids-reports")
	public ResponseEntity<Object> getKidReportList()
	{
		return kidReportService.kidReportList();
	}
	
	@PostMapping("/kids-reports")
	public ResponseEntity<Object> addkidReport(@RequestBody KidsReport kidsReport)
	{
		System.out.println(kidsReport);
		return kidReportService.addKidReport(kidsReport);
	}
	
	@GetMapping("kids/{id}/kids-reports")
	public ResponseEntity<Object> getKidReportByKid(@PathVariable Long id)
	{
		return kidReportService.kidReportByKid(id);
	}
	
	@GetMapping("kid-reports")
	public ResponseEntity<Object> kidsReportByYear(@RequestParam("kid") Long kid,@RequestParam("year") int year)
	{
		return kidReportService.kidReportByYear(kid, year);
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
	
	@GetMapping("/kids-groups/{id}")
	public ResponseEntity<Object> getkidsGroupbyId(@PathVariable int id)
	{
		return kidService.kidsGroupById(id);
	}
	
	@PutMapping("/kids-groups/{id}")
	public ResponseEntity<Object> updateKidGroup(@RequestBody KidsGroup kidsGroup, @PathVariable int id)
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
	
	@PostMapping("/add-kids-attendance")
	public ResponseEntity<Object> createKidsAttendance(@RequestBody Attendance attendance,@RequestHeader("KidsIds") String[] kidsIds)
	{
		return this.attendanceService.addKidsAttendance(attendance, kidsIds);
	}
}
