package testsuite;

import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

@Log4j2
public class TestSecondreport extends BaseTest {

    public TestSecondreport(){
        super();
        log.info("Initailize TestCreateEmploye");
        String path = "src/main/resources/entry_data/scenario1/data.properties";
        Properties prop;
        prop = ReaderPropertiesFile.readPropertiesFromFile(path);
        initializeConfigSuite(prop);
    }

    @Test
    public void TesAssert1(){
        log.info("Initailize TesAssert1");
        Assert.assertTrue(true);
    }
    @Test
    public void TesAssert2(){
        log.info("Initailize TesAssert2");
        Assert.assertTrue(true);
    }
    @Test
    public void TesAssert3(){
        log.info("Initailize TesAssert3");
        Assert.assertTrue(true);
    }
}
