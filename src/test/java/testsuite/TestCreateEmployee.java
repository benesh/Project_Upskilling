package testsuite;


import org.opensourcedemo.BaseTest.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.listerners.Mylisterner;
import org.opensourcedemo.pagesobjects.DashbordPage;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(Mylisterner.class)
@Log4j2
public class TestCreateEmployee extends BaseTest {
    public TestCreateEmployee(){
        super();
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
}
