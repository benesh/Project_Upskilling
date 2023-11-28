package testsuite;


import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.Driver;
import org.opensourcedemo.core.DriverFactory;
import org.opensourcedemo.core.Utils;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.adminpages.AdminPage;
import org.opensourcedemo.pagesobjects.adminpages.FormAddAdminUser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCreateEmployee {
    WebDriver driver;
    Driver typeDriver =Driver.FIREFOX;
    DriverFactory driverfactory;
    String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";



    @BeforeMethod
    public void setup(){
        driver = Utils.setup(typeDriver,URL);
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
                .inputUserName(username)
                .inputPwd(password)
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeInputFirstName(firstname)
                .typeInputMiddletName(middlename)
                .typeInputLastName(lastname)
                .clickSaveButton()
                .clicksavebutton()
                .getTitle();

        //Asserts
        Assert.assertEquals(titlegetted,title);
    }

    @Test
    public void testAddAmdinUser(){
        //Arrange
        String title="PIM";
        String username="Admin";
        String password = "admin123";
        String firstname="Ben";
        String middlename="Omar";
        String lastname="Ba";
        String adminuser="omzo";
        String passwordAdmin ="viti123456";


        AdminPage adminpage= new LoginPage(driver)
                .inputUserName(username)
                .inputPwd(password)
                .clickButtonLogin()
                .clickAdminPage()
                .clickAddAmdin()
                .inputUserRole()
                .inputEmployeeName(firstname +" "+ middlename +" "+ lastname)
                .inputSatus()
                .inputUserNameAdmin(adminuser)
                .inputPassword(passwordAdmin)
                .inputPassworConfirmation(passwordAdmin)
                .buttonSaveAdmin();
    }


    @AfterMethod
    public void quittingDriver(){
        driver.quit();
    }


}
