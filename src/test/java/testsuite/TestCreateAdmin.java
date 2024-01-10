package testsuite;

import BaseTest.BaseTest;
import app.getxray.xray.testng.annotations.Requirement;
import app.getxray.xray.testng.annotations.XrayTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

@Log4j2
public class TestCreateAdmin extends BaseTest {
    Employee[] employees;
    @Parameters({"browser","datainput"})
    public TestCreateAdmin(String browser,String datainput){
        super();
        log.info("Initailize TestSecondreport");
        Properties prop = ReaderPropertiesJsonFile.readPropertiesFromFile(datainput);
        setPorpertiesSuite(prop);
        webDriverType = browser;
    }

    @DataProvider(name = "dataCreateEmployee")
    public Employee[][] dataproviderMethod(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
            employees = ReaderPropertiesJsonFile.readJsonEmployee(getPropertiesSuite().getProperty("pathuserdata"));
        }
        Employee[][] data = new Employee[1][2];
        data[0][0] = employees[0];
        if(getWebDriverType().equals(WebDriverType.FIREFOX)){
            data[0][1] = employees[1];
        } else if (getWebDriverType().equals(WebDriverType.CHROME)) {
            data[0][1] = employees[3];
        }else {
            data[0][1] = employees[5];
        }
        return data;
    }
    @Test(groups ={"PIM_DOMAINE","adminUser"},dataProvider = "dataCreateEmployee"
            ,description = "Test création d'un utilisateur de role Admin pour l'employé créé précédemment")
    @XrayTest(key="TestHMR-101" ,summary = "Creer un employee",description = "Creation d'un employee et vérifier que le compte est créer ",labels = "Core PIM")
    @Requirement(key = "PRO-106")
    public void testCreationUserAdmin(Employee defaultAdminUser, Employee adminUserToCreate){
        //Arrange

        //Virat  Kohli
        String namelogin = new LoginPage()
                .inputUserName(defaultAdminUser.getUser().getUsername())
                .inputPwd(defaultAdminUser.getUser().getPassword())
                .clickButtonLogin()
                .clickAdminPage()
                .clickAddAmdin()
                .setUserToAdminRole()
                .typeEmployeeName(adminUserToCreate.getNameComplete())
                .setSatusUserAccountToEnable()
                .inputUserAdminName(adminUserToCreate.getUser().getUsername())
                .inputPassword(adminUserToCreate.getUser().getPassword())
                .inputPassworConfirmation(adminUserToCreate.getUser().getPassword())
                .buttonSaveAdmin()
                .handlerSuccessAlert()              //Vérifie la creation de user admin
                .clickToProfil()
                .logOutbutton()
                .inputUserName(adminUserToCreate.getUser().getUsername())
                .inputPwd(adminUserToCreate.getUser().getPassword())
                .clickButtonLogin()
                .getNameProfil();
        //Assert
        Assert.assertEquals(namelogin,adminUserToCreate.getNameWithoutMiddlename());
    }

}
