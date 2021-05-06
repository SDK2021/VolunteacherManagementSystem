package com.volunteacher.app.service.classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.School;
import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.Volunteacher;
import com.volunteacher.app.repository.EventRepository;
import com.volunteacher.app.repository.KidRepository;
import com.volunteacher.app.repository.SchoolRepository;
import com.volunteacher.app.repository.SessionRepository;
import com.volunteacher.app.repository.VolunteacherRepository;
import com.volunteacher.app.service.interfaces.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	KidRepository kidRepository;
	
	@Autowired
	VolunteacherRepository volunteacherRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	SchoolRepository schoolRespository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Override
	public ResponseEntity<Object> downloadKids() {
		try {
			
			List<Kid> kids = new ArrayList<>();
			kids = (List<Kid>) kidRepository.findAll();
			System.out.println(kids);
			String[] column = {"KidId","Name","Age","DOB","Gender","Group","Area","Level","School","Village","total Session"};
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream opStream = new ByteArrayOutputStream();
			
			Sheet sheet = workbook.createSheet("Kids");
			
			Row headerRow = sheet.createRow(0);
			
			for (int i = 0; i < column.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(column[i]);
			}
			
			int rowIdx = 1;
		 
			for (Kid kid : kids) {
				Row row = sheet.createRow(rowIdx++);
				String gender = "";
				String level = "";
						
				row.createCell(0).setCellValue(kid.getKidId());
				row.createCell(1).setCellValue(kid.getName());
			    Calendar cal = (Calendar)  Calendar.getInstance();  
				row.createCell(2).setCellValue(cal.get(Calendar.YEAR) - kid.getDob().get(Calendar.YEAR));
				row.createCell(3).setCellValue(kid.getDob().get(Calendar.DAY_OF_MONTH) + "/" + kid.getDob().get(Calendar.MONTH) + "/" + kid.getDob().get(Calendar.YEAR));
				if(kid.getGender() == 1)
				{
					gender = "Male";
				}
				else 
				{
					gender = "Female";
				}
				row.createCell(4).setCellValue(gender);
				row.createCell(5).setCellValue(kid.getGroup().getGroupName());
				row.createCell(6).setCellValue(kid.getArea().getAreaName());
				if(kid.getLevel() == 1)
				{
					level = "Aatmsodh";
				}
				else if(kid.getLevel() == 1)
				{
					level = "Aatmvishes";
				}
				else 
				{
					level = "Aatm";
				}
				row.createCell(7).setCellValue(level);
				row.createCell(8).setCellValue(kid.getSchool());
				row.createCell(9).setCellValue(kid.getVillage().getVillageName());
				row.createCell(10).setCellValue(kid.getTotalSessions());
				
			}
			
			 sheet.autoSizeColumn(0);
		     sheet.autoSizeColumn(1);
		     sheet.autoSizeColumn(2);
		     sheet.autoSizeColumn(3);
		     sheet.autoSizeColumn(4);
		     sheet.autoSizeColumn(5);
		     sheet.autoSizeColumn(6);
		     sheet.autoSizeColumn(7);
		     sheet.autoSizeColumn(8);
		     sheet.autoSizeColumn(9);
		     sheet.autoSizeColumn(10);
		     
			workbook.write(opStream);
			System.out.println(rowIdx);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=Kids.xlsx");
			workbook.close();
			return ResponseEntity.status(HttpStatus.OK).headers(header)
			        .body(new InputStreamResource(new ByteArrayInputStream(opStream.toByteArray())));
		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on download kids");
		}
	}

	@Override
	public ResponseEntity<Object> downloadVolunteachers() {
		try {
			
			String[] columns = {"Vounteachers Id","Name","DOB","Gender","Phone Number","Type","Joining Date","District","Village","School","Employer Name","Education"}; 
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream opstream = new  ByteArrayOutputStream();
			
			List<Volunteacher> volunteachers = new ArrayList<>();
			volunteachers = (List<Volunteacher>) volunteacherRepository.findAll();
			
			Sheet sheet = workbook.createSheet("Volunteachers");
			
			Row headerRow = sheet.createRow(0);
			
			for (int i = 0; i < columns.length; i++) 
			{
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
			}
			
			int rowIndex = 1;
			
			for (Volunteacher volunteacher : volunteachers) {
				String gender = "";
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(volunteacher.getUser().getUserId());
				row.createCell(1).setCellValue(volunteacher.getUser().getUserName());
				row.createCell(2).setCellValue(volunteacher.getUser().getDob().get(Calendar.DAY_OF_MONTH) + "/" + volunteacher.getUser().getDob().get(Calendar.MONTH) + "/" + volunteacher.getUser().getDob().get(Calendar.YEAR));
				if(volunteacher.getUser().getGender() == 1)
				{
					gender = "Male";
				}
				else 
				{
					gender = "Female";
				}
				row.createCell(3).setCellValue(gender);
				row.createCell(4).setCellValue(volunteacher.getUser().getPhoneNumber());
				row.createCell(5).setCellValue(volunteacher.getUser().getType().getType());
				row.createCell(6).setCellValue(volunteacher.getJoiningDate().get(Calendar.DAY_OF_MONTH) + "/" + volunteacher.getJoiningDate().get(Calendar.MONTH) + "/" + volunteacher.getJoiningDate().get(Calendar.YEAR));
				row.createCell(7).setCellValue(volunteacher.getDistrict().getDistrictName());
				row.createCell(8).setCellValue(volunteacher.getVillage().getVillageName());
				row.createCell(9).setCellValue(volunteacher.getSchool());
				row.createCell(10).setCellValue(volunteacher.getEmployerName());
				row.createCell(11).setCellValue(volunteacher.getEducation());
			}
			
			 sheet.autoSizeColumn(0);
		     sheet.autoSizeColumn(1);
		     sheet.autoSizeColumn(2);
		     sheet.autoSizeColumn(3);
		     sheet.autoSizeColumn(4);
		     sheet.autoSizeColumn(5);
		     sheet.autoSizeColumn(6);
		     sheet.autoSizeColumn(7);
		     sheet.autoSizeColumn(8);
		     sheet.autoSizeColumn(9);
		     sheet.autoSizeColumn(10);
		     sheet.autoSizeColumn(11);
			
			workbook.write(opstream);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=Volunteachers.xlsx");
			workbook.close();
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(new InputStreamResource(new ByteArrayInputStream(opstream.toByteArray())));
			
		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on download Vounteacher");
		}
	}
	
	@Override
	public ResponseEntity<Object> downloadEvents() {
		try {
			
			String[] columns = {"Event Id","Event Name","Event Date","Description","Village"};
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream opstream = new  ByteArrayOutputStream();
			
			List<Event> events = new ArrayList<>();
			events = (List<Event>) eventRepository.findAll();
			
			Sheet sheet = workbook.createSheet("Events");
			
			Row headerRow = sheet.createRow(0);
			
			for (int i = 0; i < columns.length; i++) 
			{
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
			}
			
			int rowIndex = 1;
			
			for (Event event : events) {
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(event.getEventId());
				row.createCell(1).setCellValue(event.getTitle());
				row.createCell(2).setCellValue(event.getEventDate().get(Calendar.DAY_OF_MONTH) + "/" + event.getEventDate().get(Calendar.MONTH) + "/" + event.getEventDate().get(Calendar.YEAR));
				
				row.createCell(3).setCellValue(event.getEventData());
				row.createCell(4).setCellValue(event.getVillage().getVillageName());	
			}
			
			 sheet.autoSizeColumn(0);
		     sheet.autoSizeColumn(1);
		     sheet.autoSizeColumn(2);
		     sheet.autoSizeColumn(3);
		     sheet.autoSizeColumn(4);
		
		
			
			workbook.write(opstream);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=Events.xlsx");
			workbook.close();
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(new InputStreamResource(new ByteArrayInputStream(opstream.toByteArray())));
			
		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on download Events");
		}
	}
	
	@Override
	public ResponseEntity<Object> downloadSchools() {
		try {
			
			String[] columns = {"School Id","School Name","Vilage","Starting Date","Phone Number", "Stream","Total Labs","Total Student","Requirements"};
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream opstream = new  ByteArrayOutputStream();
			
			List<School> schools = new ArrayList<>();
			schools = (List<School>) schoolRespository.findAll();
			
			Sheet sheet = workbook.createSheet("Schools");
			
			Row headerRow = sheet.createRow(0);
			
			for (int i = 0; i < columns.length; i++) 
			{
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
			}
			
			int rowIndex = 1;
			
			for (School school : schools) {
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(school.getSchoolId());
				row.createCell(1).setCellValue(school.getSchoolName());
				row.createCell(2).setCellValue(school.getVillage().getVillageName());
				
				row.createCell(3).setCellValue(school.getStartingDate().get(Calendar.DAY_OF_MONTH) + "/" + school.getStartingDate().get(Calendar.MONTH) + "/" + school.getStartingDate().get(Calendar.YEAR));
				row.createCell(4).setCellValue(school.getPhoneNumber());	
				row.createCell(5).setCellValue(school.getStream());	
				row.createCell(6).setCellValue(school.getTotalLabs());	
				row.createCell(7).setCellValue(school.getTotalStudent());
				row.createCell(8).setCellValue(school.getRequirements());
				
			}
			
			 sheet.autoSizeColumn(0);
		     sheet.autoSizeColumn(1);
		     sheet.autoSizeColumn(2);
		     sheet.autoSizeColumn(3);
		     sheet.autoSizeColumn(4);
		     sheet.autoSizeColumn(5);
		     sheet.autoSizeColumn(6);
		     sheet.autoSizeColumn(7);
		     sheet.autoSizeColumn(8);
			
			workbook.write(opstream);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=Schools.xlsx");
			workbook.close();
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(new InputStreamResource(new ByteArrayInputStream(opstream.toByteArray())));
			
		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on download School");
		}
	}
	
	@Override
	public ResponseEntity<Object> downloadSessions() {
		try {
			
			String[] columns = {"Session Id","Project","Session Date","Village"};
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream opstream = new  ByteArrayOutputStream();
			
			List<Session> sessions = new ArrayList<>();
			sessions = (List<Session>) sessionRepository.findAll();
			
			Sheet sheet = workbook.createSheet("Sessions");
			
			Row headerRow = sheet.createRow(0);
			
			for (int i = 0; i < columns.length; i++) 
			{
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
			}
			
			int rowIndex = 1;
			
			for (Session session : sessions) {
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(session.getSessionId());
				row.createCell(1).setCellValue(session.getProject().getProjectName());
				row.createCell(2).setCellValue(session.getSessionDate().get(Calendar.DAY_OF_MONTH) + "/" + session.getSessionDate().get(Calendar.MONTH) + "/" + session.getSessionDate().get(Calendar.YEAR));
				
				row.createCell(3).setCellValue(session.getVillage().getVillageName());	
			}
			
			 sheet.autoSizeColumn(0);
		     sheet.autoSizeColumn(1);
		     sheet.autoSizeColumn(2);
		     sheet.autoSizeColumn(3);
		
		
			
			workbook.write(opstream);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=Sessions.xlsx");
			workbook.close();
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(new InputStreamResource(new ByteArrayInputStream(opstream.toByteArray())));
			
		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on download Events");
		}
	}
}
