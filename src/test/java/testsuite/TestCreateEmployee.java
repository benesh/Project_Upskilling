package testsuite;
import org.apache.logging.log4j.Logger;
import org.opensourcedemo.BaseTest.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.listerners.Mylisterner;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.myinfopage.MyInfoPage;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDatailsPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.security.AlgorithmParameterGenerator;

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
        Assert.assertEquals(titlegetted,title);
    }
    @Test(dependsOnMethods = "TestCreateEmployeePIM")
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
                        .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                                        .clickButtonLogin()
                .getNameProfil();
        //Assert
        Assert.assertEquals(namelogin,configproperties.getEmployee().get(1).getNameWithoutMiddlename());
    }


    @Test()//dependsOnMethods = "testCreateUserAdmin")
    public void remplirLeFormulaire(){
        //Arrange

        // Act
        EmployeeDatailsPage pimListPage = new LoginPage(testsetup)
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
                .clickSaveButton()
                .clicktBloodListInput()
                .clickRancdomBloodtypeOption()
                .clickSaveButtonWithBloodType()
                .refreshPage();
        //Assert
        Assert.assertEquals(pimListPage.getBirtdaydate(),configproperties.getEmployee().get(2).getBirthdate());
        if (configproperties.getEmployee().get(2).getGender().equals("M")){
            Assert.assertTrue (pimListPage.isRadioButtonMaleSelected());
        }else {
            Assert.assertTrue (pimListPage.isRadioButtonFemalealeSelected());
        }
        Assert.assertNotNull(pimListPage.getBloodTypeValue());
    }

    @Test(testName = "Feuille de temps")
    public void testFeuilledetemps(){
        //Arrange
        String nameproject="ACME";
        //Act
        ProjectReportSearch pageproject = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                .clickButtonLogin()
                .clickTimePage()
                .clickReportMenulist()
                .clickReportProject()
                .typeSearchProjectByName(nameproject)
                .clickFirstOptionSearch()
                .clickViewProject();

        //Assert
        Assert.assertTrue(pageproject.verifyIftimesMatcheesTotal());
    }
    @Test
    public void testFileUpload(){
        //Arrange
        File filetoUptload= new File(" ");
        File uploadFile = new File("src/test/resources/selenium-snapshot.png");
        String filename="";
        String date = "";

        //Act
        MyInfoPage myinfopage = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                .clickButtonLogin()
                .clickMyInfoPage()
                .clickAddAttachement()
                .updaloadFile(filetoUptload)
                ;
        int index = myinfopage.verifyIfIsUploaded(filename);

        //Assert
        Assert.assertTrue(index>0);
        Assert.assertEquals(myinfopage.getFileDateUploaded(index),date);
    }




}
