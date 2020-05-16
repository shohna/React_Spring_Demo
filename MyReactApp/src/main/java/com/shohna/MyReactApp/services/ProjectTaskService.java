package com.shohna.MyReactApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shohna.MyReactApp.Repositories.BacklogRepository;
import com.shohna.MyReactApp.Repositories.ProjectRepository;
import com.shohna.MyReactApp.Repositories.ProjectTaskRepository;
import com.shohna.MyReactApp.domain.Backlog;
import com.shohna.MyReactApp.domain.Project;
import com.shohna.MyReactApp.domain.ProjectTask;
import com.shohna.MyReactApp.exceptions.ProjectIdException;
import com.shohna.MyReactApp.exceptions.ProjectNotFoundException;

@Service
public class ProjectTaskService {
	@Autowired
	private BacklogRepository backlogrepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		try {
			Backlog backlog = backlogrepository.findByProjectIdentifier(projectIdentifier);
			
			projectTask.setBacklog(backlog);
			Integer BacklogSequence = backlog.getPtSequence();
			BacklogSequence++;
			backlog.setPtSequence(BacklogSequence);
			projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			if(projectTask.getPriority()==null) {
				projectTask.setPriority(3);
			}
			if(projectTask.getStatus()==""||projectTask.getStatus()==null) {
				projectTask.setStatus("TO_DO");
			}
			return projectTaskRepository.save(projectTask);

		}catch(Exception e) {
			throw new ProjectNotFoundException("Project not found");
		}
			}
	public Iterable<ProjectTask>findBacklogById(String id){
		
		Project project = projectRepository.findByProjectIdentifier(id);
		if(project == null) {
			throw new ProjectNotFoundException("Project with id "+id+" not found");
		}
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
		
	}
	public ProjectTask findPTbyProjectSequence(String backlog_id, String pt_id) {
		
		Backlog backlog = backlogrepository.findByProjectIdentifier(backlog_id);
		if(backlog == null) {
			throw new ProjectNotFoundException("Project with id "+backlog_id+" not found");
		}
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project Task "+pt_id+" not found");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project Task "+pt_id+" does not exist in project: "+backlog_id);
		}
		return projectTask;
	}
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {
		ProjectTask projectTask = findPTbyProjectSequence(backlog_id, pt_id);
		projectTask = updatedTask;
		
		return projectTaskRepository.save(projectTask);
		
	}
	
	public void deletePTByProjectSequence(String backlog_id, String pt_id) {
		ProjectTask projectTask = findPTbyProjectSequence(backlog_id, pt_id);
		projectTaskRepository.delete(projectTask);
	}
	
}
