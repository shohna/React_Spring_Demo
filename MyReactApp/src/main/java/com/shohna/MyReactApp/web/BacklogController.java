package com.shohna.MyReactApp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shohna.MyReactApp.Repositories.ProjectTaskRepository;
import com.shohna.MyReactApp.domain.ProjectTask;
import com.shohna.MyReactApp.services.MapValidationErrorService;
import com.shohna.MyReactApp.services.ProjectService;
import com.shohna.MyReactApp.services.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id){
		
	ResponseEntity<?> ErrorMap = mapValidationErrorService.MapValidationErrorService(result);
	if(ErrorMap!=null) return ErrorMap;
	
	ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
	return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{backlog_id}")
	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
		return projectTaskService.findBacklogById(backlog_id);
	}
	@GetMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> findPTbyProjectSequence(@PathVariable String backlog_id, @PathVariable String pt_id){
		ProjectTask projectTask = projectTaskService.findPTbyProjectSequence(backlog_id, pt_id);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id, @PathVariable String pt_id){
	
	ResponseEntity<?> ErrorMap = mapValidationErrorService.MapValidationErrorService(result);
	if(ErrorMap!=null) return ErrorMap;
	
	ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pt_id);
	return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> deleteProject(@PathVariable String backlog_id, @PathVariable String pt_id){
		projectTaskService.deletePTByProjectSequence(backlog_id, pt_id);
		return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully", HttpStatus.OK);
	}
	
	
	
	
}