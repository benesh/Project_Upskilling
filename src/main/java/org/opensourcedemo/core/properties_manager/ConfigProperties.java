package org.opensourcedemo.core.properties_manager;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.driver_manager.DriverType;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Log4j2
public class ConfigProperties {
    Properties propertie ;
    List<Employee> employees;
    public ConfigProperties(Properties parampropertie){
        propertie = parampropertie;
        GlobalConfig.GLOBALWAIT=getImplicitWait();
        log.info("Initialise properties config " + propertie.getProperty("title"));
    }
    public String getUrl(){
        return propertie.getProperty("URL");
    }
    public DriverType getBrowser(){
        return DriverType.valueOf(propertie.getProperty("browser"));
    }
    public String getPathLog(){return propertie.getProperty("pathlog");}
    public String getPathScreenshot(){return propertie.getProperty("pathscreenshot");}
    public String getPathReport(){return propertie.getProperty("pathreport");}
    public String geOptions(){ return propertie.getProperty("option_browser");}
    public String getDataEmployeeUserPath(){ return propertie.getProperty("option_browser");}
    public String getPathUserData(){return propertie.getProperty("pathuserdata");}
    public int getImplicitWait(){return Integer.parseInt(propertie.getProperty("implicitwait"));}
    public List<Employee> getEmployee() {
        if (employees==null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                employees = Arrays.asList(mapper.readValue(Paths.get(getPathUserData()).toFile(), Employee[].class));
            } catch (IOException e) {
                log.error(" Employee json cnnot be readed error log: " + e);
                throw new RuntimeException(e);
            }
        }
        return employees;
    }
}
