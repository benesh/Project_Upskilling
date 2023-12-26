package org.opensourcedemo.core.properties_manager;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Log4j2
public class ConfigProperties {
    Properties propertie ;
    public ConfigProperties(Properties parampropertie){
        propertie = parampropertie;
        GlobalConfig.GLOBALWAIT= getExplicitWait();
        GlobalConfig.GLOBALPOLLING=getWaitPollingEvery();
        log.info("Initialise properties config " + propertie.getProperty("title"));
    }
    public String getUrl(){
        return propertie.getProperty("URL");
    }
    public WebDriverType getBrowser(){
        return WebDriverType.valueOf(propertie.getProperty("default_browser"));
    }
    public String getPathLog(){return propertie.getProperty("pathlog");}
    public String getPathScreenshot(){return propertie.getProperty("pathscreenshot");}
    public String getPathReport(){return propertie.getProperty("pathreport");}
    public String getHeadless(){ return propertie.getProperty("headless");}
    public String getPathUserData(){return propertie.getProperty("pathuserdata");}
    public int getExplicitWait(){return Integer.parseInt(propertie.getProperty("explicitwait"));}
    public int getWaitPollingEvery(){return Integer.parseInt(propertie.getProperty("pollingEvery"));}

}
