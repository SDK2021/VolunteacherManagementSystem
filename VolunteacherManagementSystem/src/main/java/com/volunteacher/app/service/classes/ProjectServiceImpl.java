package com.volunteacher.app.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.Project;
import com.volunteacher.app.model.User;
import com.volunteacher.app.repository.KidRepository;
import com.volunteacher.app.repository.ProjectRepository;
import com.volunteacher.app.repository.SessionRepository;
import com.volunteacher.app.repository.UserRepository;
import com.volunteacher.app.service.interfaces.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	KidRepository kidRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	List<User> users;
	
	List<Kid> kids;
	
	@Override
	public ResponseEntity<Object> addProject(Project project,String[] vIds,String[] kIds) 
	{
		if(projectRepository.findByProjectName(project.getProjectName()) != null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project already Exist");
		}
		try {
			project.setUsers(this.createVolunteachersList(vIds));
			project.setKids(this.createKidsList(kIds));
			project.setTotalKids(this.createKidsList(kIds).size());
			project.setTotalVolunteachers(this.createVolunteachersList(vIds).size());
			project.setTotalSessions(sessionRepository.totalSessionByProject(project.getProjectId()));
			Project saveProject = projectRepository.save(project);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveProject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Project Creation");
		}
	}

	@Override
	public ResponseEntity<Object> AllprojectList() 
	{
		try {
			List<Project> projectList = (List<Project>) projectRepository.findAll();
			
			if(projectList.size() < 1)
			{
				throw new ResourceNotFoundException("Project list not found");
			}
			else 
			{
				return ResponseEntity.status(HttpStatus.OK).body(projectList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch projects list");
		}
	}
	
	@Override
	public ResponseEntity<Object> projectList(int page) 
	{
		try {
			List<Project> projectList = (List<Project>) projectRepository.findAll();
			
			if(projectList.size() < 1)
			{
				throw new ResourceNotFoundException("Project list not found");
			}
			else 
			{
				Pageable pageable = PageRequest.of(page, 5, Sort.by("creationDate").descending());
				Page<Project> pageprojectList = (Page<Project>) projectRepository.findAll(pageable);
				return ResponseEntity.status(HttpStatus.OK).body(pageprojectList.getContent());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch projects");
		}
	}


	@Override
	public ResponseEntity<Object> projectById(int id) 
	{
		try {
			Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(project);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch project for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> updateProject(Project project, int id) 
	{
		Project updateProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
		
		updateProject.setProjectName(project.getProjectName());
		updateProject.setProjectData(project.getProjectData());
		updateProject.setStartingDate(project.getStartingDate());
		updateProject.setCreationDate(project.getCreationDate());
		updateProject.setCreationTime(project.getCreationTime());
		updateProject.setUsers(project.getUsers());
		updateProject.setKids(project.getKids());
		updateProject.setEndingDate(project.getCreationDate());
		updateProject.setPhoto(project.getPhoto());
		
		try {
			projectRepository.save(updateProject);
			updateProject.setTotalKids(projectRepository.TotalKidsByProject(id));
			updateProject.setTotalSessions(projectRepository.TotalSessionByProject(id));
			updateProject.setTotalVolunteachers(projectRepository.TotalVolunteachersByProject(id));
			System.out.println(projectRepository.TotalSessionByProject(id));
			projectRepository.save(updateProject);
			return ResponseEntity.status(HttpStatus.OK).body(updateProject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Project for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteProject(int id) 
	{
		projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
		
		try {
			projectRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Project for id:" +id);
		}
	}

	@Override
	public int TotalNumberProjectByUser(int id) 
	{
		return projectRepository.TotalProjectByUser(id);
	}

	@Override
	public ResponseEntity<Object> allProjectList() 
	{
		try {
			List<Project> projectList = (List<Project>) projectRepository.findAll();
			
			if(projectList.size() < 1)
			{
				throw new ResourceNotFoundException("Project list not found");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(projectList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch projects");
		}
	}

	@Override
	public ResponseEntity<Object> projectNumbersByUser(int userId) {
		try {
			List<Integer> projectNumbers = projectRepository.projectNumberByUser(userId);
			return ResponseEntity.status(HttpStatus.OK).body(projectNumbers);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetcjing project numbers");
		}
	}

	@Override
	public ResponseEntity<Object> totalSessionsByProject(int projectId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(projectRepository.TotalSessionByProject(projectId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total sessions of project");
		}
	}

	@Override
	public ResponseEntity<Object> totalKidsByProject(int projectId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(projectRepository.TotalKidsByProject(projectId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total kids of project");
		}
	}

	@Override
	public ResponseEntity<Object> totalVolunteachersByProject(int projectId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(projectRepository.TotalVolunteachersByProject(projectId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total kids of project");
		}
	}
	
	@Override
	public List<User> createVolunteachersList(String[] ids)
	{
		this.users = new ArrayList<User>();
		for (int i = 0;i<ids.length;i++) {
			User user = this.userRepository.findById(Long.parseLong(ids[i])).orElseThrow(()-> new ResourceNotFoundException("VT id not found for id: "));
			this.users.add(user);
		}
		System.out.println(this.users + " " + this.users.size());
		return this.users;
	}
	
	@Override
	public List<Kid> createKidsList(String[] ids)
	{
		System.out.println("Hello I am Kids list" + ids.length);
		this.kids = new ArrayList<Kid>();
		for (int i = 0;i<ids.length;i++) {
			Kid kid = this.kidRepository.findById(Long.parseLong(ids[i])).orElseThrow(()-> new ResourceNotFoundException("VT id not found for id: "));
			this.kids.add(kid);
		}
		System.out.println(this.kids + " " + this.kids.size());
		return this.kids;
	}
}
