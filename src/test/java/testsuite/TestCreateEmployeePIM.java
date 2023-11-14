package testsuite;


import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.Driver;
import org.opensourcedemo.core.DriverFactory;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCreateEmployeePIM {
    WebDriver driver;
    Driver typeDriver =Driver.FIREFOX;
    String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeMethod
    public void setup(){
        driver = new DriverFactory(typeDriver).getDriverFactory();
        driver.get(URL);
        driver.manage().window().maximize();

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
                .getTitle();

        //Asserts
        Assert.assertEquals(titlegetted,title);
    }

    @AfterMethod
    public void quittingDriver(){
        driver.quit();
    }


}
