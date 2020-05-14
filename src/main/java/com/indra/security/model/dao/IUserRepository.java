package com.indra.security.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indra.security.model.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>
{
	public User findByUsername(String userName);
	
	@Query("select u from User u where u.email=?1")
	public User findByEmail(String email);
}
