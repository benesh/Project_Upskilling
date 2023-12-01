package org.opensourcedemo.core.properties_manager;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ReaderPropertiesFile {
    ConfigProperties Config ;

    public ReaderPropertiesFile(String configpath){

    }

    private Properties initialieProperties(String configpath){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(configpath);
             properties.load(fileInputStream);
            fileInputStream.close();
            log.info("Initilize ");
        }catch (IOException e){
            log.error(e);
        }
        return properties;
    }
    private void initalizeEmployeeParser(){

    }


}
