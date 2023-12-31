package org.opensourcedemo.core.properties_manager;

import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@Log4j2
public class ConfigProperties {
    Properties propertie ;
    public ConfigProperties(Properties parampropertie){
        propertie = parampropertie;
        GlobalConfig.EXPLICITWAIT = getExplicitWait();
        GlobalConfig.IMPLICITWAIT = getImplicitWait();
        GlobalConfig.ENV = propertie.getProperty("env");
        GlobalConfig.DOCUMENTTITLE = propertie.getProperty("ducomenttitle");
        GlobalConfig.REPORTNAME = propertie.getProperty("rapportname");
        GlobalConfig.HOSTNAME = getHostName();
        GlobalConfig.SOFTWARETEST = propertie.getProperty("softwaretest");
        GlobalConfig.USER = propertie.getProperty("user");
        GlobalConfig.PATHREPORT = getPathReport();
        GlobalConfig.HUB_URL = propertie.getProperty("url_hub");

        log.info("Initialise properties config " + propertie.getProperty("title"));
    }
    public String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    public WebDriverType getBrowser(){
        return WebDriverType.valueOf(propertie.getProperty("default_browser"));
    }
    public String getPathLog(){return propertie.getProperty("pathlog");}
    public String getPathScreenshot(){return propertie.getProperty("pathscreenshot");}
    public String getPathReport(){return propertie.getProperty("pathreport");}
    public String getHeadless(){ return propertie.getProperty("headless");}
    public int getExplicitWait(){return Integer.parseInt(propertie.getProperty("explicitwait"));}
    public int getImplicitWait(){return Integer.parseInt(propertie.getProperty("implicitwait"));}

}
