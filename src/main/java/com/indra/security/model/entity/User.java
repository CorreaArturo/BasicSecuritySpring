package com.indra.security.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User
 * @author INDRA
 * @sice 11/05/2020
 */
@Entity
@Table(name = "users")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, name = "user_name", length = 20)
	private String username;
	
	@Column(unique=true, name = "phone_number")
	private String phoneNumber;
	
	@Column(unique=true, name = "email")
	private String email;
	
	private Boolean enabled;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="roles_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"user_id","role_id"})})
	private List<Role> roles;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="users_passwords", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="password_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"user_id","password_id"})})
	private List<UserPassword> paswordList;	
	
	private String password;
	
	public User() 
	{
		super();
	}
	
	public User(String username, String phoneNumber, String email, Boolean enabled, Date creationDate, List<Role> roles) 
	{
		super();
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.enabled = enabled;
		this.creationDate = creationDate;
		this.roles = roles;
	}
	
	

	public User(String username, String phoneNumber, String email, Boolean enabled, Date creationDate) 
	{
		super();
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.enabled = enabled;
		this.creationDate = creationDate;
	}

	public User(Long id, String username, String phoneNumber, String email, Boolean enabled, Date creationDate) 
	{
		super();
		this.id = id;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.enabled = enabled;
		this.creationDate = creationDate;
	}

	public User(Long id, String username, String phoneNumber, String email, Boolean enabled, Date creationDate, List<Role> roles, List<UserPassword> paswordList) 
	{
		super();
		this.id = id;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.enabled = enabled;
		this.creationDate = creationDate;
		this.roles = roles;
		this.paswordList = paswordList;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Boolean getEnabled() 
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public Date getCreationDate() 
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate) 
	{
		this.creationDate = creationDate;
	}

	public List<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(List<Role> roles) 
	{
		this.roles = roles;
	}

	public List<UserPassword> getPaswordList()
	{
		return paswordList;
	}

	public void setPaswordList(List<UserPassword> paswordList)
	{
		this.paswordList = paswordList;
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	
}
