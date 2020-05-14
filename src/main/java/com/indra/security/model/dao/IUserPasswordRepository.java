package com.indra.security.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indra.security.model.entity.UserPassword;

@Repository
public interface IUserPasswordRepository extends CrudRepository<UserPassword, Long>
{

}
