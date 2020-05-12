package com.shohna.MyReactApp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shohna.MyReactApp.domain.Project;
import com.shohna.MyReactApp.services.MapValidationErrorService;
import com.shohna.MyReactApp.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		ResponseEntity<?> errorMap = mapValidationService.MapValidationErrorService(result);
		if(errorMap!=null) return errorMap;
		
		Project project1 = projectService.SaveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{ProjectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String ProjectId){
		Project project = projectService.findProjectByIdentifier(ProjectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/{ProjectId}")
	public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String ProjectId){
		projectService.deleteProjectByIdentifier(ProjectId);
		return new ResponseEntity<String>("Project '"+ProjectId+"' deleted successfully.", HttpStatus.OK);
	}
	
	

}
