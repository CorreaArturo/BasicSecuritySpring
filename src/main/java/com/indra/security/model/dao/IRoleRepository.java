package com.indra.security.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indra.security.model.entity.Role;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long>
{

}
