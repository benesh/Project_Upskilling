package org.opensourcedemo.core;

import config.Porperties;
import lombok.extern.log4j.Log4j2;

import java.awt.event.PaintEvent;
import java.util.Properties;

@Log4j2
public class ConfigProperties extends PropertiesParent {
    Properties propertie ;

    public ConfigProperties(Properties parampropertie){
        propertie = parampropertie;
        log.info("Initialise properties config " + propertie.getProperty("TITLE"));
    }

    public String getUrl(){
        return propertie.getProperty("URL");
    }
    public Driver getBrowser(){
        return Driver.valueOf(propertie.getProperty("BROWSER"));
    }
    public String getPathLog(){return propertie.getProperty("PATHLOG");}
    public String getPathScreenshot(){return propertie.getProperty("PATHSCREENSHOT");}
    public String getPathReport(){return propertie.getProperty("PATHREPORT");}
    public String geOptions(){ return propertie.getProperty("OPTION_BROWSER");}

}
