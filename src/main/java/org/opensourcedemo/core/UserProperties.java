package org.opensourcedemo.core;

import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Log4j2
public class UserProperties extends PropertiesParent {

    Properties propertie = new Properties();

    public UserProperties(Properties parampropertie){
        propertie = parampropertie;
    }

    public String getUsername(){
        return propertie.getProperty("username");
    }
    public String getpassword(){
        return propertie.getProperty("password");
    }
    public String getuserfirstname(){
        return propertie.getProperty("firstname");
    }
    public String getusermiddletname(){
        return propertie.getProperty("middlename");
    }
    public String getuserlastname(){
        return propertie.getProperty("lastname");
    }

}
