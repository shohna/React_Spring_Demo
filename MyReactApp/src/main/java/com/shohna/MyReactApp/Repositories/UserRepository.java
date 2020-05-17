package com.shohna.MyReactApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shohna.MyReactApp.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User getById(Long id);
}
