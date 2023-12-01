package org.opensourcedemo.core.properties_manager;

import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Log4j2
public class UserProperties extends PropertiesParent {

    Properties propertie = new Properties();

    public UserProperties(Properties parampropertie){
        propertie = parampropertie;
    }

    public String getUserName(){
        return propertie.getProperty("username");
    }
    public String getPassword(){
        return propertie.getProperty("password");
    }
    public String getUserFirstName(){
        return propertie.getProperty("firstname");
    }
    public String getUserMiddleName(){
        return propertie.getProperty("middlename");
    }
    public String getUserLastName(){
        return propertie.getProperty("lastname");
    }
    public String getRootUserName(){
        return propertie.getProperty("username_root");
    }public String getRootPassword(){
        return propertie.getProperty("password_root");
    }

}
