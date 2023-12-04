package org.opensourcedemo.core.properties_manager;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class PropertiesFactory {
    private ReaderPropertiesFile readproperty;
    public PropertiesFactory(){
        log.info("Initialize Factory Reading Property");
    }
    public ConfigProperties factoryProperty(String pathfileproperty){
        readproperty = new ReaderPropertiesFile(pathfileproperty);
        return new ConfigProperties(readproperty.getProperties());
    }
}
