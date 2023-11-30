package org.opensourcedemo.core;

import lombok.extern.log4j.Log4j2;

import java.awt.event.PaintEvent;
import java.util.Properties;

@Log4j2
public class ConfigProperties extends PropertiesParent {
    Properties propertie ;

    public ConfigProperties(Properties parampropertie){
        propertie = parampropertie;
    }

    public String getUrl(){
        return propertie.getProperty("URL");
    }
    public Driver getBrowser(){
        return propertie.getProperty("BROWSER");
    }
}
