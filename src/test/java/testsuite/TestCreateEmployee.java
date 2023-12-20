package testsuite;
import org.opensourcedemo.BaseTest.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.myinfopage.MyInfoPage;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDatailsPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class TestCreateEmployee extends BaseTest {
    public TestCreateEmployee(){
        super("src/main/resources/entry_data/scenario1/config.properties");
    }
    @DataProvider(name = "dataCreateEmployee")
    public Employee[][] dataproviderMethod(){
        log.info("Load Dayaprovider For Login");
        Employee[][] data = new Employee[1][2];
        data[0][0] = configproperties.getEmployee().getFirst();
        data[0][1] = configproperties.getEmployee().get(1);
        return data;
    }
    @Test(testName = "Create Employee PIM",dataProvider = "dataCreateEmployee")
    public void TestCreateEmployeePIM( Employee employees1, Employee employe2 ){
        logger = extent.createTest("Create Employee PIM");
        logger.assignAuthor("Ben Omar")
                .assignCategory("Sanity check");
        //Arrange
        String title="PIM";
        //Act
        String titlegetted = new LoginPage(testsetup)
                .inputUserName(employees1.getUser().getUsername())
                .inputPwd(employees1.getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(employe2.getFirstname())
                .typeMiddletName(employe2.getMiddlename())
                .typeLastName(employe2.getLastname())
                .clickSaveButton()
                        .clickSaveButton()
                                .getTitle();
        Assert.assertEquals(titlegetted,title);
    }
    @Test(dependsOnMethods = "TestCreateEmployeePIM",testName = "Creation Admin User",dataProvider = "dataCreateEmployee")
    public void testCreateUserAdmin(Employee employees1, Employee employe2){
        logger = extent.createTest("Creation Admin User")
                .assignAuthor("Ben Omar")
                .assignCategory("Sanity check")
                .assignDevice(configproperties.getBrowser().toString());
        //Arrange

        //Virat  Kohli
        String namelogin = new LoginPage(testsetup)
                .inputUserName(employees1.getUser().getUsername())
                .inputPwd(employees1.getUser().getPassword())
                .clickButtonLogin()
                .clickAdminPage()
                .clickAddAmdin()
                .setUserToAdminRole()
                .typeEmployeeName(employe2.getNameComplete())
                .setSatusUserAccountToEnable()
                .inputUserNameAdmin(employe2.getUser().getUsername())
                .inputPassword(employe2.getUser().getPassword())
                .inputPassworConfirmation(employe2.getUser().getPassword())
                .buttonSaveAdmin()
                .clickToProfil()
                .logOutbutton()
                        .inputUserName(employe2.getUser().getUsername())
                                .inputPwd(employe2.getUser().getPassword())
                                        .clickButtonLogin()
                .getNameProfil();
        //Assert
        Assert.assertEquals(namelogin,employe2.getNameWithoutMiddlename());
    }

    @DataProvider(name = "dataRemplirFormulaire")
    public Employee[][] dataProviderMethodFillForm(){
        log.info("Load Dayaprovider For Login");
        Employee[][] data = new Employee[1][2];
        data[0][0] = configproperties.getEmployee().get(1);
        data[0][1] = configproperties.getEmployee().get(2);
        return data;
    }

    @Test(dependsOnMethods = "testCreateUserAdmin",testName = "Remplir le formaulaire ", dataProvider = "dataRemplirFormulaire")
    public void remplirFormulaire(Employee employe1, Employee employe2){
        logger = extent.createTest("Remplir le formaulaire ")
                .assignAuthor("Ben Omar")
                .assignDevice(configproperties.getBrowser().toString())
                .assignCategory("Sanity check")
                ;
        //Arrange

        // Act
        EmployeeDatailsPage pimListPage = new LoginPage(testsetup)
                .inputUserName(employe1.getUser().getUsername())
                .inputPwd(employe1.getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(employe2.getFirstname())
                .typeMiddletName(employe2.getMiddlename())
                .typeLastName(employe2.getLastname())
                .clickSaveButton()
                .typeBirthDate(employe2.getBirthdate())
                .clickGender(employe2.getGender())
                .clickSaveButton()
                .clicktBloodListInput()
                .clickRancdomBloodtypeOption()
                .clickSaveButtonWithBloodType()
                .refreshPage();
        //Assert
        Assert.assertEquals(pimListPage.getBirtdaydate(),employe2.getBirthdate());
        if (employe2.getGender().equals("M")){
            Assert.assertTrue (pimListPage.isRadioButtonMaleSelected());
        }else {
            Assert.assertTrue (pimListPage.isRadioButtonFemalealeSelected());
        }
        Assert.assertNotNull(pimListPage.getBloodTypeValue());
    }
    @DataProvider(name = "dataFeuilledeTmps")
    public Object[][] dataProviderMethodFeuillDetemps(){
        log.info("Load Dayaprovider For Login");
        Object[][] data = new Object[1][2];
        data[0][0] = configproperties.getEmployee().get(1);
        data[0][1] = configproperties.getEmployee().get(2);
        return data;
    }
    @Test(testName = "Feuille de temps",dependsOnMethods = "testCreateUserAdmin",dataProvider = "dataFeuilledeTmps")
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
