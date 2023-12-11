package testsuite;
import org.opensourcedemo.BaseTest.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.listerners.Mylisterner;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(Mylisterner.class)
@Log4j2
public class TestCreateEmployee extends BaseTest {

    public TestCreateEmployee(){
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


                /*.clickswitchCreateLoginDetails()
                .typeUsername(configproperties.getEmployee().get(1).getUser().getUsername())
                .clickRadioButtonStatus()
                .typePassword(configproperties.getEmployee().get(1).getUser().getPassword())
                .typePasswordConfirmation(configproperties.getEmployee().get(1).getUser().getPassword())
                .clickSaveButton()
                .clickSaveButton()
                .clickToProfil()
                .logOutbutton()
                        .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                                        .clickButtonLogin()
                .getNameProfil();*/
        //Asserts
        Assert.assertEquals(titlegetted,title);
    }
    @Test
    public void testCreateUserAdmin(){
        //Arrange
        String title="PIM";
        //Virat  Kohli
        String namelogin = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(0).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(0).getUser().getPassword())
                .clickButtonLogin()
                .clickAdminPage()
                .clickAddAmdin()
                .setUserToAdminRole()
                .typeEmployeeName(configproperties.getEmployee().get(1).getNameComplete())
                .setSatusUserAccountToEnable()
                .inputUserNameAdmin(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPassword(configproperties.getEmployee().get(1).getUser().getPassword())
                .inputPassworConfirmation(configproperties.getEmployee().get(1).getUser().getPassword())
                .buttonSaveAdmin()
                .clickToProfil()
                .logOutbutton()
                        .inputPwd(configproperties.getEmployee().get(1).getUser().getUsername())
                                .inputPwd(configproperties.getEmployee().get(1).getUser().getUsername())
                                        .clickButtonLogin()
                .getNameProfil();
        //Assert
        Assert.assertEquals(namelogin,configproperties.getEmployee().get(1).getNameWithoutMiddlename());
    }


    @Test
    public void remplirLeFormulaire(){
        //Arrange
        String titlegetted = "PIM";

        // Act
        String title = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(configproperties.getEmployee().get(2).getFirstname())
                .typeMiddletName(configproperties.getEmployee().get(2).getMiddlename())
                .typeLastName(configproperties.getEmployee().get(2).getLastname())
                .clickSaveButton()
                .typeBirthDate(configproperties.getEmployee().get(2).getBirthdate())
                .clickGender(configproperties.getEmployee().get(2).getGender())
                .clicktBloodListInput()
                .clickRancdomBloodtypeOption()
                .getTitle();
        //Assert
        Assert.assertEquals(titlegetted,title);
    }

    @Test
    public void Testtest() {
        //Arrange
        String titlegetted = "PIM";

        // Act
        String title = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                .clickButtonLogin()
                .getNameProfil();

    }



    }
