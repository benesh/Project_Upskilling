package testsuite;

import BaseTest.BaseTest;
import app.getxray.xray.testng.annotations.Requirement;
import app.getxray.xray.testng.annotations.XrayTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDetailsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

@Log4j2
public class TestRemplirFormulaire extends BaseTest {
    Employee[] employees;
    @Parameters({"browser","datainput"})
    public TestRemplirFormulaire(String browser,String datainput){
        super();
        log.info("Initailize TestSecondreport");
        Properties prop = ReaderPropertiesJsonFile.readPropertiesFromFile(datainput);
        setPorpertiesSuite(prop);
        webDriverType = browser;
    }
    @DataProvider(name = "dataRemplirFormulaire")
    public Employee[][] dataProviderMethodFillForm(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
            employees = ReaderPropertiesJsonFile.readJsonEmployee(getPropertiesSuite().getProperty("pathuserdata"));
        }
        Employee[][] data = new Employee[1][2];

        if(getWebDriverType().equals(WebDriverType.FIREFOX)){
            data[0][0] = employees[1];
            data[0][1] = employees[2];
        } else if (getWebDriverType().equals(WebDriverType.CHROME)) {
            data[0][0] = employees[3];
            data[0][1] = employees[4];
        }else {
            data[0][0] = employees[5];
            data[0][1] = employees[6];
        }
        return data;
    }
    @Test(testName = "Remplir formulaire ", dataProvider = "dataRemplirFormulaire",
            groups = {"form"},description = "Remplir le formulaire et vérifier que les données sont bien sauvegardées")
    @XrayTest(key="TestHMR-100" ,summary = "remplir le fomrulaire",description = "Rempli le formulaire et verifier que les valeurs sont bien enregistrer",labels = "Core Info")
    @Requirement(key = "PRO-105")
    public void remplirFormulaire(Employee employe1, Employee employe2){

        // Act
        EmployeeDetailsPage pimListPage = new LoginPage()
                .inputUserName(employe1.getUser().getUsername())
                .inputPwd(employe1.getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(employe2.getFirstname())
                .typeMiddletName(employe2.getMiddlename())
                .typeLastName(employe2.getLastname())
                .clickSaveButton()
                .handlerSuccessAlert()
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
}
