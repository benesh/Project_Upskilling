package org.opensourcedemo.core.properties_manager;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.driver_manager.Driver;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;

import java.util.Properties;

@Log4j2
public class ConfigProperties extends PropertiesParent {
    Properties propertie ;
    @Getter
    Employee employee;
    public ConfigProperties(Properties parampropertie){
        propertie = parampropertie;
        log.info("Initialise properties config " + propertie.getProperty("title"));
    }
    public String getUrl(){
        return propertie.getProperty("URL");
    }
    public Driver getBrowser(){
        return Driver.valueOf(propertie.getProperty("browser"));
    }
    public String getPathLog(){return propertie.getProperty("patlog");}
    public String getPathScreenshot(){return propertie.getProperty("patscreenshot");}
    public String getPathReport(){return propertie.getProperty("pathreport");}
    public String geOptions(){ return propertie.getProperty("option_browser");}
    public String getDataEmployeeUserPath(){ return propertie.getProperty("option_browser");}
}
