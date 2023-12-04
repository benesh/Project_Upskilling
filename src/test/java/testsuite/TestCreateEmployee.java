package testsuite;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.PropertiesFactory;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.pagesobjects.DashbordPage;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.adminpages.AdminPage;
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
    TestSetup testsetup;
    ConfigProperties configproperties;
    String pathconfig = "src/main/resources/entry_data/scenario1/config.properties";
    @BeforeSuite
    public void setupAll(){
        PropertiesFactory propfactory = new PropertiesFactory();
        configproperties = propfactory.factoryProperty(pathconfig);
        testsetup = new TestSetup();
    }
    @BeforeMethod
    public void setup(){
        testsetup.setup(configproperties);
    }
    @Test
    public void Testexecution(){
        //Arrange
        String title="PIM";
        //Act
        String titlegetted = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(0).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(0).getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .inputFirstName(configproperties.getEmployee().get(1).getFirstname())
                .inputMiddletName(configproperties.getEmployee().get(1).getMiddlename())
                .inputLastName(configproperties.getEmployee().get(1).getLastname())
                .clickSaveButton()
                .clickSaveButton()
                .getTitle();
        //Asserts
        Assert.assertEquals(titlegetted,title);
    }

    @Test
    public void testCreateUserAdmin(){
        //Arrange
        String title="PIM";
        //Virat  Kohli


        DashbordPage tittlegetted = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(0).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(0).getUser().getPassword())
                .clickButtonLogin()
                .clickAdminPage()
                .clickAddAmdin()
                .inputUserRole()
                .inputEmployeeName(configproperties.getEmployee().get(1).getNameComplete())
                .inputSatus()
                .inputUserNameAdmin(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPassword(configproperties.getEmployee().get(1).getUser().getPassword())
                .inputPassworConfirmation(configproperties.getEmployee().get(1).getUser().getPassword())
                .buttonSaveAdmin()
                .clickToProfil()
                .logOutbutton()
                        .inputPwd(configproperties.getEmployee().get(1).getUser().getUsername())
                                .inputPwd(configproperties.getEmployee().get(1).getUser().getUsername())
                                        .clickButtonLogin();


        log.info(tittlegetted.getTittle());
    }


    @AfterMethod
    public void resulscreenshot(ITestResult result){
//        if (result.getStatus() == ITestResult.FAILURE){
            File scrFile =  ((TakesScreenshot)testsetup.getDriver()).getScreenshotAs(OutputType.FILE);
            String name = "screenshot.png";
            try {
                FileUtils.copyFile(scrFile,new File("test_output/screenshots/" + name));
            } catch (IOException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
//        }
//        log.info("Quitting Driver");
        testsetup.quitDriver();
    }
 /*   @AfterMethod
    public void quittingDriver(){
        testsetup.quitDriver();
    }*/

}
