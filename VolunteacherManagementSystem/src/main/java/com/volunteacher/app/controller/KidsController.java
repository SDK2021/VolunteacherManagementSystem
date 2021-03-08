package com.volunteacher.app.controller;

import java.util.List;

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
	
	@GetMapping("/kids/")
	public List<Kid> kidsList()
	{
		return kidService.kidList();
	}
	
	@GetMapping("/kids/{id}")
	public Kid getKid(@PathVariable Long id)
	{
		return kidService.getKid(id);
	}
	
	@GetMapping("/kids")
	public List<Kid> kidslistByGroup(@RequestParam(name = "group") int id)
	{
			return kidService.kidsListByGroup(id);
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
	
	@GetMapping("/kidsReport/")
	public List<KidsReport> kidReportList()
	{
		return kidReportService.kidReportList();
	}
	
	@PostMapping("/kidsReports")
	public ResponseEntity<Object> addkidReport(@RequestBody KidsReport kidsReport)
	{
		return kidReportService.addKidReport(kidsReport);
	}
	
	@GetMapping("kids/{id}/kid-report")
	public KidsReport kidReportByKid(@PathVariable int id)
	{
		return kidReportService.kidReportByKid(id);
	}
	
	@GetMapping("/kids-group/")
	public List<KidsGroup> kidGroupList()
	{
		return kidService.kidGroupList();
	}
	
	@GetMapping("/attendances/")
	public List<Attendance> attndanceList()
	{
		return attendanceService.attendanceList();
	}
	
	@GetMapping("/attendance")
	public Attendance attendanceBySessionAndGroup(@RequestParam("groupId") int gid, @RequestParam("sessionId") Long sid)
	{
		return attendanceService.attendanceByGroupAndSession(gid, sid);
	}
	
	@GetMapping("/attendances/group/{id}")
	public List<Attendance> attendanceBySession(@PathVariable int id)
	{
		return attendanceService.attendanceByGroup(id);
	}
}
