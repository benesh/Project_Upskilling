package testsuite;
import org.opensourcedemo.BaseTest.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.listerners.Mylisterner;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.myinfopage.MyInfoPage;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDatailsPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
public class TestCreateEmployee extends BaseTest {
    public TestCreateEmployee(){
        super("src/main/resources/entry_data/scenario1/config.properties");
    }
    @Test(testName = "Create Employee PIM" )
    public void TestCreateEmployeePIM(){
        logger = extent.createTest("Create Employee PIM");
        logger.assignAuthor("Ben Omar")
                .assignCategory("Sanity check");
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
    @Test()//dependsOnMethods = "TestCreateEmployeePIM",testName = "Creation Admin User")
    public void testCreateUserAdmin(){
        logger = extent.createTest("Creation Admin User")
                .assignAuthor("Ben Omar")
                .assignCategory("Sanity check")
                .assignDevice(configproperties.getBrowser().toString());
        //Arrange

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


    @Test(dependsOnMethods = "testCreateUserAdmin",testName = "Remplir le formaulaire ")
    public void fillLeFormInfo(){
        logger = extent.createTest("Remplir le formaulaire ")
                .assignAuthor("Ben Omar")
                .assignDevice(configproperties.getBrowser().toString())
                .assignCategory("Sanity check")
                ;
        //Arrange

        // Act
        EmployeeDatailsPage pimListPage = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(0).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(0).getUser().getPassword())
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

    @Test(testName = "Feuille de temps",dependsOnMethods = "testCreateUserAdmin")
    public void testFeuilledetemps(){
        logger = extent.createTest("Feuille de temps")
                .assignCategory("Feature Gestion Temps")
                .assignDevice(configproperties.getBrowser().toString())
                .assignAuthor("Ben Omar");
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
    @Test(testName = "Upload de document",dependsOnMethods = "testCreateUserAdmin")
    public void testFileUpload(){
        logger = extent.createTest("Upload de document")
                .assignDevice(configproperties.getBrowser().toString())
                .assignCategory("Upload fichier")
                .assignAuthor("Omzo");
        //Arrange
        String filepath = "target/screenshot/scenari1/testFileUpload20231217011929.png";
        String filename="screenshot.png";

        //Act
        MyInfoPage myinfopage = new LoginPage(testsetup)
                .inputUserName(configproperties.getEmployee().get(1).getUser().getUsername())
                .inputPwd(configproperties.getEmployee().get(1).getUser().getPassword())
                .clickButtonLogin()
                .clickMyInfoPage()
                .clickAddAttachement()
                .updaloadFile(filepath)
                .clickSaveButtonForFileUploaded()
                .handlerSuccessAlert()
                ;
        int index = myinfopage.verifyIfIsUploaded(filename);

        //Assert
        // Vérification de la présence du fichier parmis les noms de la liste
        Assert.assertTrue(index>=0);
        //Vérification de la date de l'upload qui doit celui del'exécution du test
        Assert.assertEquals(myinfopage.getFileDateUploaded(index),java.time.LocalDate.now().toString());
    }




}
