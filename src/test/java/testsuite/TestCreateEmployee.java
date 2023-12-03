package testsuite;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.driver_manager.Utils;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.PropertiesFactory;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Log4j2
public class TestCreateEmployee {
    WebDriver driver;
    ConfigProperties configproperties;
    List<Employee> employeeList;
    String patconfig = "src/main/resources/entry_data/scenario1/config.properties";
    @BeforeSuite
    public void setupAll(){
        PropertiesFactory propfactory = new PropertiesFactory();
        configproperties = propfactory.factoryProperty(patconfig);
        employeeList = configproperties.getEmployee();
        System.out.println("babababab");
    }
    @BeforeMethod
    public void setup(){
        driver = Utils.setup(configproperties);
    }
    @Test
    public void Testexecution(){
        //Arrange
        String title="PIM";
        String username="Admin";
        String password = "admin123";
        String firstname="Ben";
        String middlename="Omar";
        String lastname="Ba";

        //Act
        String titlegetted = new LoginPage(driver)
                .inputUserName(configproperties.getEmployee().get(0).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(0).getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .inputFirstName(configproperties.getEmployee().get(1).getFirstname())
                .inputMiddletName(configproperties.getEmployee().get(1).getMiddlename())
                .inputLastName(configproperties.getEmployee().get(1).getLastname())
                .clickSaveButton()
                .clicksavebutton()
                .getTitle();

        //Asserts
        Assert.assertEquals(titlegetted,title);
    }

   /* @Test
    public void testAddAmdinUser(){
        //Arrange
        String title="PIM";
        String username="Admin";
        String password = "admin123";
        String firstname="Virat";
        String middlename="";
        String lastname="minion";
        String adminuser="Kohli";
        String passwordAdmin ="viti123456";
        //Virat  Kohli


        AdminPage adminpage= new LoginPage(driver)
                .inputUserName(username)
                .inputPwd(password)
                .clickButtonLogin()
                .clickAdminPage()
                .clickAddAmdin()
                .inputUserRole()
                .inputEmployeeName(firstname +" "+ lastname)
                .inputSatus()
                .inputUserNameAdmin(adminuser)
                .inputPassword(passwordAdmin)
                .inputPassworConfirmation(passwordAdmin)
                .buttonSaveAdmin();
    }
*/


    @AfterMethod
    public void resulscreenshot(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            File scrFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String name = "screenshot.png";
            try {
                FileUtils.copyFile(scrFile,new File("test_output/screenshots/" + name));
            } catch (IOException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        }
        log.info("Quitting Driver");
        driver.quit();
    }
    @AfterMethod
    public void quittingDriver(){
        Utils.quittingDrivere(driver);
    }


}
