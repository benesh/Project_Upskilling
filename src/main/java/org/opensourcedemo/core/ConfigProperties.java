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
        return Driver.valueOf(propertie.getProperty("browser"));
    }
    public String getPathLog(){return propertie.getProperty("patlog");}
    public String getPathScreenshot(){return propertie.getProperty("patscreenshot");}
    public String getPathReport(){return propertie.getProperty("pathreport");}
    public String geOptions(){ return propertie.getProperty("option_browser");}

}
