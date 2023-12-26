package testsuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.properties_manager.data_manager.ProjectDescription;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.myinfopage.MyInfoPage;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDatailsPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Listeners({org.opensourcedemo.listerners.ReportListerner.class, app.getxray.xray.testng.listeners.XrayListener.class})
@Log4j2
public class TestCreateEmployee extends BaseTest {

    Employee[] employees;
    public TestCreateEmployee(){
        super();
        log.info("Initailize TestCreateEmploye");
        String path = "src/main/resources/entry_data/scenario1/data.properties";
        Properties prop;
        prop = ReaderPropertiesFile.readPropertiesFromFile(path);
        initializeConfigSuite(prop);
    }
    @DataProvider(name = "dataCreateEmployee")
    public Employee[][] dataproviderMethod(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                employees = mapper.readValue(
                        Paths.get(propertiesSuite.getProperty("pathuserdata")).toFile()
                        ,Employee[].class
                );
            } catch (IOException e) {
                log.error(" Employee json cnnot be readed error log: " + e);
                throw new RuntimeException(e);
            }
        }
        Employee[][] data = new Employee[1][2];
        data[0][0] = employees[0];
        data[0][1] = employees[1];
        return data;
    }
    @Test(testName = "Create Employee PIM",dataProvider = "dataCreateEmployee", groups = {"group default"})
    public void TestCreateEmployeePIM( Employee employees1, Employee employe2 ){

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
        //Report Arrange

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
        data[0][0] = employees[1];
        data[0][1] = employees[2];
        return data;
    }

    @Test(testName = "Remplir le formaulaire ", dataProvider = "dataRemplirFormulaire",dependsOnMethods = "testCreateUserAdmin")
    public void remplirFormulaire(Employee employe1, Employee employe2){
        //Report Arrange

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
    @DataProvider(name = "dataFeuilledeTemps")
    public Object[][] dataProviderMethodFeuillDetemps(){
        log.info("Load Dayaprovider For Login");
        ProjectDescription[] projetdata;
        Object[][] data ;
        ObjectMapper mapper = new ObjectMapper();
        try {
            projetdata = mapper.readValue(
                            Paths.get(propertiesSuite.getProperty("pathdataproject"))
                                    .toFile(), ProjectDescription[].class
                    );
        } catch (IOException e) {
            log.error(" Employee json cnnot be readed error log: " + e);
            throw new RuntimeException(e);
        }
        data = new Object[projetdata.length][2];
        int index = 0;
        for( ProjectDescription projet : projetdata){
            data[index][1] = projet;
            data[index][0] = employees[0];
            index++;
        }
        return data;
    }
    @Test(testName = "Feuille de temps",dataProvider = "dataFeuilledeTemps",dependsOnMethods = "testCreateUserAdmin")
    public void testFeuilledetemps(Employee employe,ProjectDescription projectdata ){
        //Report Arrange

        //Arrange

        //Act
        ProjectReportSearch pageproject = new LoginPage(testsetup)
                .inputUserName(employe.getUser().getUsername())
                .inputPwd(employe.getUser().getPassword())
                .clickButtonLogin()
                .clickTimePage()
                .clickReportMenulist()
                .clickReportProject()
                .typeSearchProjectByName(projectdata.getAnachrome())
                .clickRightOptionSearch(projectdata.getDescription())
                .clickViewProject();

        //Assert
        Assert.assertTrue(pageproject.verifyIftimesMatcheesTotal());
    }
    @DataProvider(name = "dataproviderUploadFichier")
    public Object[][] dataProviderMethodFileUpload(){
        log.info("Load Dayaprovider For Login");
        Object[][] data = new Object[1][3];
        data[0][0] = employees[0];
        data[0][1] = propertiesSuite.getProperty("pathfiletoupload");
        data[0][2] = propertiesSuite.getProperty("pathfiletoupload")
                .substring(propertiesSuite.getProperty("pathfiletoupload").lastIndexOf("/") +1
                );
        return data;
    }
    @Test(testName = "Upload de document",dataProvider = "dataproviderUploadFichier",dependsOnMethods = "testCreateUserAdmin")
    public void testFileUpload(Employee employe,String fileUpload,String fileName){
        //Report Arrange

        //Arrange

        //Act
        MyInfoPage myinfopage = new LoginPage(testsetup)
                .inputUserName(employe.getUser().getUsername())
                .inputPwd(employe.getUser().getPassword())
                .clickButtonLogin()
                .clickMyInfoPage()
                .clickAddAttachement()
                .updaloadFile(fileUpload)
                .clickSaveButtonForFileUploaded()
                .handlerSuccessAlert()
                ;
        int index = myinfopage.verifyIfIsUploaded(fileName);

        //Assert
        // Vérification de la présence du fichier parmis les noms de la liste
        Assert.assertTrue(index>=0);
        //Vérification de la date de l'upload qui doit celui del'exécution du test
        Assert.assertEquals(myinfopage.getFileDateUploaded(index),java.time.LocalDate.now().toString());
    }

}
