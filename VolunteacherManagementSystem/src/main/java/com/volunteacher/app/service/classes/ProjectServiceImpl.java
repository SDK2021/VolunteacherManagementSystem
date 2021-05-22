package com.volunteacher.app.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.Project;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.Volunteacher;
import com.volunteacher.app.repository.KidRepository;
import com.volunteacher.app.repository.ProjectRepository;
import com.volunteacher.app.repository.SessionRepository;
import com.volunteacher.app.repository.UserRepository;
import com.volunteacher.app.repository.VolunteacherRepository;
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
	
	@Autowired
	VolunteacherRepository volunteacherRepository;
	
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
				return ResponseEntity.status(HttpStatus.OK).body(projectList);
			} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch projects list");
		}
	}
	
	@Override
	public ResponseEntity<Object> projectList(int page) 
	{
		try 
		{
			List<Project> pageprojectList = (List<Project>) projectRepository.findAll(Sort.by("creationDate").descending());
			return ResponseEntity.status(HttpStatus.OK).body(pageprojectList);
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
	public ResponseEntity<Object> updateProject(Project project, int id,String[] vIds,String[] kIds) 
	{
		try {
			Project updateProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
			updateProject.setProjectName(project.getProjectName());
			updateProject.setProjectData(project.getProjectData());
			updateProject.setStartingDate(project.getStartingDate());
			updateProject.setCreationDate(project.getCreationDate());
			updateProject.setCreationTime(project.getCreationTime());
			if(vIds.length > 0)
				updateProject.setUsers(this.createVolunteachersList(vIds));
			if(kIds.length > 0)
				updateProject.setKids(this.createKidsList(kIds));
			updateProject.setEndingDate(project.getCreationDate());
			updateProject.setDescription(project.getDescription());
			updateProject.setPhoto(project.getPhoto());
			
			projectRepository.save(updateProject);
			updateProject.setTotalKids(projectRepository.TotalKidsByProject(id));
			updateProject.setTotalSessions(projectRepository.TotalSessionByProject(id));
			updateProject.setTotalVolunteachers(projectRepository.TotalVolunteachersByProject(id));
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
		try {
			projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
			projectRepository.deleteProjectsKids(id);
			projectRepository.deleteProjectsUsers(id);
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
	public ResponseEntity<Object> totalEventByProject(int projectId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(projectRepository.totalEventByProject(projectId));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total events of project");
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
	
	
	@Override
	public ResponseEntity<Object> getReaminingUser(int projectId) 
	{
		this.users = new ArrayList<>();
		try {
			List<Volunteacher> volunteachers = (List<Volunteacher>)volunteacherRepository.findAll();
			Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project not found"));
			
			for (Volunteacher volunteacher : volunteachers) {
				int flag = 0;
				for (User user : project.getUsers()) {
					if(user.getUserId() == volunteacher.getUser().getUserId())
					{
						System.out.println(user.getUserId());
						flag=1;
						break;
					}
				}
				if(flag == 0)
				{
					users.add(volunteacher.getUser());
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(users);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total remaining user of project");
		}
	}
	
	@Override
	public ResponseEntity<Object> getReaminingKids(int projectId)
	{
		this.kids = new ArrayList<>();
		try {
			List<Kid> kidslist = (List<Kid>)kidRepository.findAll();
			Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project not found"));
			
			for (Kid kid : kidslist) 
			{
				int flag = 0;
				for (Kid k : project.getKids()) {
					if(k.getKidId() == kid.getKidId())
					{
						flag=1;
						break;
					}
				}
				if(flag == 0)
				{
					kids.add(kid);
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(kids);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total remaining user of project");
		}
	}
}
