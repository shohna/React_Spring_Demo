package com.shohna.MyReactApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shohna.MyReactApp.Repositories.ProjectRepository;
import com.shohna.MyReactApp.domain.Project;
import com.shohna.MyReactApp.exceptions.ProjectIdException;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project SaveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project Id " +project.getProjectIdentifier().toUpperCase()+ " already exists");
		}
		
	}

}
