package com.indra.security.request;


/**
 * MadRequestHeader.
 * @author INDRA
 * @since 11/05/2020
 */
public class MadRequestHeader 
{
    private String min;
    private String wvName;
    private String wvVersion;
    private String uuid;

    /**
     * Constructor
     */
    public MadRequestHeader() 
    {
    	super();
    }

    public MadRequestHeader(String min, String wvName, String wvVersion, String uuid)
    {
    	super();
        this.min        = min;
        this.wvName     = wvName.startsWith("/") ? wvName.substring(1) : wvName;
        this.wvVersion  = wvVersion;
        this.uuid       = uuid;
    }

    public String getMin() 
    {
        return min;
    }

    public String getWvName() 
    {
        return wvName;
    }

    public String getWvVersion() 
    {
        return wvVersion;
    }

    public String getUuid() 
    {
        return uuid;
    }

    @Override
    public String toString() 
    {
        return "min: " + min + " - wvVersion: " + wvVersion + " - uuid: " + uuid;
    }

    
}
