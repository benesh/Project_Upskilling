package org.opensourcedemo.core.properties_manager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.properties_manager.data_manager.ProjectDescription;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
public class ReaderPropertiesJsonFile {

    public static synchronized Properties readPropertiesFromFile(String configpath){
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
    public static synchronized Employee[] readJsonEmployee(String pathJson) {
        Employee[] employees;
        ObjectMapper mapper = new ObjectMapper();
        try {
            employees = mapper.readValue(
                    Paths.get(pathJson).toFile()
                    , Employee[].class
            );
        } catch (IOException e) {
            log.error(" Employee json cnnot be readed error log: " + e);
            throw new RuntimeException(e);
        }
        return employees;
    }
    public static synchronized ProjectDescription[] readJsonDataDescriptionProject(String pathJson) {
        ProjectDescription[] project;
        ObjectMapper mapper = new ObjectMapper();
        try {
            project = mapper.readValue(
                    Paths.get(pathJson).toFile()
                    , ProjectDescription[].class
            );
        } catch (IOException e) {
            log.error(" Employee json cnnot be readed error log: " + e);
            throw new RuntimeException(e);
        }
        return project;
    }

}
