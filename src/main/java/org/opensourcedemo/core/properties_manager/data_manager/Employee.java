package org.opensourcedemo.core.properties_manager.data_manager;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Date;
public class Employee {
    @Getter
    private String firstname;
    @Getter
    private String middlename = null;
    @Getter
    private String lastname;
    @Getter
    private String gender = null;
    @Getter
    private Date birthdate = null;
    @Getter
    private User user;
    public String getNameComplete(){
        if (middlename!=null){
            return firstname+" "+middlename+" "+lastname;
        }else{
            return  firstname +" "+lastname;
        }
    }
    public String getNameWithoutMiddlename(){ return  firstname +" "+lastname;}
}