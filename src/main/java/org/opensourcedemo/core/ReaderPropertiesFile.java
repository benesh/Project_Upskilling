package org.opensourcedemo.core;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ReaderPropertiesFile {
    Properties properties = new Properties();
    public ReaderPropertiesFile(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
            fileInputStream.close();
            log.info("Initilize ");
        }catch (IOException e){
            log.error(e);
        }
    }
    public Properties getProperties(){
        log.info("Getting Properties object");
        return properties;
    }
    public String getTypecConfig(){
        return properties.getProperty("TYPE");
    }


}
