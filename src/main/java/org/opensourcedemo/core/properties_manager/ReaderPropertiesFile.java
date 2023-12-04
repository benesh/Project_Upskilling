package org.opensourcedemo.core.properties_manager;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ReaderPropertiesFile {
    Properties properties;
    public ReaderPropertiesFile(String configpath){
        initializeProperties(configpath);
    }
    private void initializeProperties(String configpath){
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(configpath);
             properties.load(fileInputStream);
            fileInputStream.close();
            log.info("Initilize ");
        }catch (IOException e){
            log.error(e);
        }
    }
    public Properties getProperties(){
        log.info("Get Properties");
        return properties;}
}
