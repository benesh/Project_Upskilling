package testsuite;

import lombok.extern.log4j.Log4j2;
import BaseTest.BaseTest;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Properties;

@Log4j2
public class TestCreateEmployee extends BaseTest {
    Employee[] employees;
    @Parameters({"browser","datainput"})
    public TestCreateEmployee(String browser,String datainput){
        super();
        log.info("Initailize TestSecondreport");
        Properties prop = ReaderPropertiesJsonFile.readPropertiesFromFile(datainput);
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
    @Test(testName = "Create Employee PIM",dataProvider = "dataCreateEmployee", groups = {"PIM_DOMAINE","createemp"}
    ,description = "Test de création d'un employee et iformation de base")
    public void testCreationEmployeePIM(Employee employees1, Employee employe2 ){
        //Arrange
        String title="PIM";
        //Act
        String titlegetted = new LoginPage()
                .inputUserName(employees1.getUser().getUsername())
                .inputPwd(employees1.getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(employe2.getFirstname())
                .typeMiddletName(employe2.getMiddlename())
                .typeLastName(employe2.getLastname())
                .clickSaveButton()
                .handlerSuccessAlert()
                        .clickSaveButton()
                                .getTitle();
        Assert.assertEquals(titlegetted,title);
    }
}
