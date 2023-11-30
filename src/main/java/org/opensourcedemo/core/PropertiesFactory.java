package org.opensourcedemo.core;

import config.Porperties;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class PropertiesFactory {
    private ReaderPropertiesFile readproperty;
    public PropertiesFactory(String pathfileproperty){
        readproperty = new ReaderPropertiesFile(pathfileproperty);
        log.info("Initialize Factory Reading Property");
    }
    public PropertiesParent factoryProperty(){
        switch (readproperty.getTypecConfig()){
            case "CONFIG":
                return new ConfigProperties(readproperty.getProperties());
            case "USER":
                return new UserProperties(readproperty.getProperties());
            default:
                return null;
        }
    }
}
