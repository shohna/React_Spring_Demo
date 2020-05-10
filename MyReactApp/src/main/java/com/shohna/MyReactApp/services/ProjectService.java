package com.shohna.MyReactApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shohna.MyReactApp.Repositories.ProjectRepository;
import com.shohna.MyReactApp.domain.Project;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project SaveOrUpdateProject(Project project) {
		return projectRepository.save(project);
	}

}
