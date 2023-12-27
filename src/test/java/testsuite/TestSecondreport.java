package testsuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.pagesobjects.DashbordPage;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
public class TestSecondreport extends BaseTest {
    Employee[] employees;
    public TestSecondreport(){
        super();
        log.info("Initailize TestSecondreport");
        String path = "src/main/resources/scenario_2/data.properties";
        Properties prop;
        prop = ReaderPropertiesFile.readPropertiesFromFile(path);
        initializeConfigSuite(prop);
    }

    @DataProvider(name = "dataCreateEmployee")
    public Employee[][] dataproviderMethod(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                employees = mapper.readValue(
                        Paths.get(propertiesSuite.getProperty("pathuserdata")).toFile()
                        ,Employee[].class
                );
            } catch (IOException e) {
                log.error(" Employee json cnnot be readed error log: " + e);
                throw new RuntimeException(e);
            }
        }
        Employee[][] data = new Employee[1][2];
        data[0][0] = employees[0];
        data[0][1] = employees[1];
        return data;
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
    @Test(dataProvider = "dataCreateEmployee")
    public void TesAssert3(Employee employe1, Employee employe2){
        log.info("Initailize TesAssert3");
        DashbordPage page = new LoginPage(testsetup)
                .inputUserName(employe1.getUser().getUsername())
                        .inputPwd(employe1.getUser().getPassword())
                                .clickButtonLogin();
        Assert.assertTrue(true);
        Assert.assertEquals(page.getTittle(),"Dashboard");
    }
}
