package com.indra.security.model.dto;

import java.io.Serializable;

public class UserInfo implements Serializable
{
    private String userId;
    private String operatorId;
    private String typeAccount;
    private String isRuc;
    private String typeSubscription;
    private String email;
    private String phoneNumber;

    public UserInfo() 
    {
    	super();
    }
    
    public UserInfo(String userId, String operatorId, String typeAccount, String isRuc, String typeSubscription) 
    {
		super();
		this.userId = userId;
		this.operatorId = operatorId;
		this.typeAccount = typeAccount;
		this.isRuc = isRuc;
		this.typeSubscription = typeSubscription;
	}

	public UserInfo(Object[] result) 
    {
    	super();
        try 
        {
            userId              = result[0] != null ? result[0].toString() : "";
            operatorId          = result[1] != null ? result[1].toString() : "";
            typeAccount         = result[2] != null ? result[2].toString() : "";
            isRuc               = result[3] != null ? result[3].toString() : "";
            typeSubscription    = result[4] != null ? result[4].toString() : "";
        } 
        catch (Exception e) 
        {}
    }
	
	

    public UserInfo(String userId, String email, String phoneNumber) 
    {
		super();
		this.userId = userId;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() 
    {
        return userId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }
    
    public String getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(String operatorId) 
    {
        this.operatorId = operatorId;
    }

    public String getTypeAccount()
    {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) 
    {
        this.typeAccount = typeAccount;
    }

    public String getIsRuc() 
    {
        return isRuc;
    }

    public void setIsRuc(String isRuc) 
    {
        this.isRuc = isRuc;
    }

    public String getTypeSubscription() 
    {
        return typeSubscription;
    }

    public void setTypeSubscription(String typeSubscription) 
    {
        this.typeSubscription = typeSubscription;
    }

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
    
    
}
