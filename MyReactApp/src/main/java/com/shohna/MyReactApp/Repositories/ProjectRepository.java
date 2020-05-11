package com.shohna.MyReactApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shohna.MyReactApp.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>{

	Project findByProjectIdentifier(String ProjectId);
	
	Iterable<Project> findAll();
	
	
	

}
