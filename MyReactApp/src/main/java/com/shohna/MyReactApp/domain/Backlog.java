package com.shohna.MyReactApp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer ptSequence = 0;
	@Column(updatable = false, unique = true)
	private String projectIdentifier;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id",nullable = false)
	@JsonIgnore
	private Project project;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
	@JsonIgnore
	private List<ProjectTask> projectTasks = new ArrayList<>();
	public Backlog() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Integer getPtSequence() {
		return ptSequence;
	}
	public void setPtSequence(Integer ptSequence) {
		this.ptSequence = ptSequence;
	}
	public List<ProjectTask> getProjectTasks() {
		return projectTasks;
	}
	public void setProjectTasks(List<ProjectTask> projectTasks) {
		this.projectTasks = projectTasks;
	}
	
	
	
	
	

}
