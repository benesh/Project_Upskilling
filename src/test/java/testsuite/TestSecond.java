package testsuite;

import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class TestSecond extends BaseTest {
    public TestSecond(){
        super("src/main/resources/entry_data/scenario1/config.properties");
    }

    @Test(testName = "Create Employee PIM" )
    public void TestCreateEmployeePIM(){
        //Arrange
        String title="PIM";
        //Act
        String titlegetted = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(0).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(0).getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(configproperties.getEmployee().get(1).getFirstname())
                .typeMiddletName(configproperties.getEmployee().get(1).getMiddlename())
                .typeLastName(configproperties.getEmployee().get(1).getLastname())
                .clickSaveButton()
                .clickSaveButton()
                .getTitle();
        Assert.assertEquals(titlegetted,title);
    }
}
