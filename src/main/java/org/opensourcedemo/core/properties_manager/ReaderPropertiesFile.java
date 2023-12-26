package org.opensourcedemo.core.properties_manager;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ReaderPropertiesFile {

    public static Properties readPropertiesFromFile(String configpath){
        Properties properties= new Properties();
        try {
            log.info("Initialize deserialize file properties");
            FileInputStream fileInputStream = new FileInputStream(configpath);
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (IOException e){
            log.error(e);
        }
        return properties;
    }
}
