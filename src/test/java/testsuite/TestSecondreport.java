package testsuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import BaseTest.BaseTest;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.DashbordPage;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
public class TestSecondreport extends BaseTest {
    Employee[] employees;
    @Parameters({"browser"})
    public TestSecondreport(String browser){
        super();
       log.info("Initailize TestSecondreport");
        String pathData="src/main/resources/scenario1/data.properties";
        Properties prop;
        prop = ReaderPropertiesJsonFile.readPropertiesFromFile(pathData);
        setPorpertiesSuite(prop);
        webDriverType = browser;
    }

    @DataProvider(name = "dataCreateEmployee")
    public Employee[][] dataproviderMethod(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
                employees = ReaderPropertiesJsonFile.readJsonEmployee(getPropertiesSuite().getProperty("pathuserdata"));
        }
        Employee[][] data = new Employee[1][2];

        data[0][0] = employees[0];
        if(getWebDriverType().equals(WebDriverType.FIREFOX)){
            data[0][1] = employees[1];
        } else if (getWebDriverType().equals(WebDriverType.CHROME)) {
            data[0][1] = employees[3];
        }else {
            data[0][1] = employees[5];
        }
        return data;
    }
    @Test(testName = "Test Assert 3",description = "Test Success de verification",groups = {"PIM","group1"})
    public void TesAssert1(){
        log.info("Initailize TesAssert1");
        Assert.assertTrue(true);
    }
    @Test(groups = {"PIM","group2"},description = "Test verification du fail doc")
    public void TesAssert2(){
        log.info("Initailize TesAssert2");
        Assert.assertTrue(true);
    }
    @Test(dataProvider = "dataCreateEmployee",groups = {"group3"},description = "Test verification du fail doc")
    public void TesAssert3(Employee employe1, Employee employe2){
        log.info("Initailize TesAssert3");
        DashbordPage page = new LoginPage()
                .inputUserName(employe1.getUser().getUsername())
                        .inputPwd(employe1.getUser().getPassword())
                                .clickButtonLogin();
        Assert.assertTrue(true);
        Assert.assertEquals(page.getTittle(),"Dashboard");
    }
}