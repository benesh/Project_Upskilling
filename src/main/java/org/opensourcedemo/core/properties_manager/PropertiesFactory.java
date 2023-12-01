package org.opensourcedemo.core.properties_manager;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class PropertiesFactory {
    private ReaderPropertiesFile readproperty;
    public PropertiesFactory(){
        log.info("Initialize Factory Reading Property");
    }
    public PropertiesParent factoryProperty(String pathfileproperty){
        readproperty = new ReaderPropertiesFile(pathfileproperty);
        switch (readproperty.getTypeConfig()){
            case "CONFIG":
                return new ConfigProperties(readproperty.getProperties());
            case "USER":
                return new UserProperties(readproperty.getProperties());
            default:
                return null;
        }
    }
}
