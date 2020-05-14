package com.indra.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.indra.security.model.dao.IRoleRepository;
import com.indra.security.model.dao.IUserPasswordRepository;
import com.indra.security.model.dao.IUserRepository;
import com.indra.security.model.entity.Role;
import com.indra.security.model.entity.User;
import com.indra.security.model.entity.UserPassword;

@ComponentScan("com.indra.security.model.service")
@SpringBootTest
class MadPresentationApplicationTests {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	IUserPasswordRepository userPasswordRepository;
	
	@Test
	void contextLoads() 
	{
	}
	
	@Test
	public void saveUserTest() 
	{
//		User userEntity = new User(new Long(10),"ktus", "3106579410", "guachinango@gmail.com", Boolean.TRUE, new Date());
//		UserPassword userPasswordEntity = new UserPassword(new Long(11), passwordEncoder.encode("1234"), Boolean.TRUE, new Date(), new Date());
//		Role roleAdminEntity = new Role(new Long(12), "ROLE_ADMIN");
//		Role roleUserEntity = new Role(new Long(12), "ROLE_USER");
//		User entitySave = userRepository.save(userEntity);
//		roleRepository.save(roleAdminEntity);
//		roleRepository.save(roleUserEntity);
//		userPasswordRepository.save(userPasswordEntity);
//		assertTrue(userEntity.getEmail().equals(entitySave.getEmail()));
		
//		insert into users_passwords(user_id, password_id) values(1,1);
//		insert into roles_authorities(user_id, role_id) values(1,1);
//		insert into roles_authorities(user_id, role_id) values(1,2);		
	}	

}
