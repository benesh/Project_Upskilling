package testsuite;


import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.driver_manager.Utils;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.PropertiesFactory;
import org.opensourcedemo.core.properties_manager.UserProperties;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCreateEmployee {
    WebDriver driver;
    String patconfig = "src/main/resources/entry_data/scenario1/config.properties";
    String pathdata = "src/main/resources/entry_data/scenario1/user_data.properties";
    ConfigProperties configproperties;
    UserProperties userproperties;
    @BeforeSuite
    public void setupAll(){
        PropertiesFactory propfactory = new PropertiesFactory();
        configproperties = (ConfigProperties) propfactory.factoryProperty(patconfig);
        userproperties = (UserProperties) propfactory.factoryProperty(pathdata);
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
                .inputUserName(userproperties.getRootUserName())
                .inputPwd(userproperties.getRootPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeInputFirstName(userproperties.getUserFirstName())
                .typeInputMiddletName(userproperties.getUserMiddleName())
                .typeInputLastName(userproperties.getUserLastName())
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
    public void quittingDriver(){
        Utils.quittingDrivere(driver);
    }


}
