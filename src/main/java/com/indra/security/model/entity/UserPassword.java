package com.indra.security.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passwords")
public class UserPassword implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "password", length = 60)
	private String password;
	
	private Boolean enabled;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	public UserPassword() 
	{
		super();
	}
	
	public UserPassword(Long id, String password, Boolean enabled, Date creationDate, Date updateDate) 
	{
		super();
		this.id = id;
		this.password = password;
		this.enabled = enabled;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
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

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
}
