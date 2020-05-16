package com.shohna.MyReactApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shohna.MyReactApp.Repositories.BacklogRepository;
import com.shohna.MyReactApp.Repositories.ProjectRepository;
import com.shohna.MyReactApp.domain.Backlog;
import com.shohna.MyReactApp.domain.Project;
import com.shohna.MyReactApp.exceptions.ProjectIdException;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired 
	private BacklogRepository backlogRepository;
	
	
	public Project SaveOrUpdateProject(Project project) {
		
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId() == null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			if(project.getId() != null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project Id " +project.getProjectIdentifier().toUpperCase()+ " already exists");
		}	
	}
	
	public Project findProjectByIdentifier(String ProjectId) {
		Project project = projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdException("Project Id " +ProjectId+ " does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String ProjectId) {
		Project project = projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Cannot delete project "+ProjectId+" .It does not exist");
		}
		projectRepository.delete(project);
	}
	
	
}
