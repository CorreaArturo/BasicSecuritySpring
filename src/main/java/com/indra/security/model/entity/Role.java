package com.indra.security.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User
 * @author INDRA
 * @sice 11/05/2020
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, name = "name", length = 20)
	private String name;

	
	public Role() 
	{
		super();
	}
	
	public Role(Long id, String name) 
	{
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	
}
