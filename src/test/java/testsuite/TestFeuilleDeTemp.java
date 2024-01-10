package testsuite;

import BaseTest.BaseTest;
import app.getxray.xray.testng.annotations.Requirement;
import app.getxray.xray.testng.annotations.XrayTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.properties_manager.data_manager.ProjectDescription;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

@Log4j2
public class TestFeuilleDeTemp extends BaseTest {
    Employee[] employees;
    @Parameters({"browser","datainput"})
    public TestFeuilleDeTemp(String browser,String datainput){
        super();
        log.info("Initailize TestSecondreport");
        Properties prop = ReaderPropertiesJsonFile.readPropertiesFromFile(datainput);
        setPorpertiesSuite(prop);
        webDriverType = browser;
    }

    @DataProvider(name = "dataFeuilledeTemps")
    public Object[][] dataProviderMethodFeuillDetemps(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
            employees = ReaderPropertiesJsonFile.readJsonEmployee(getPropertiesSuite().getProperty("pathuserdata"));
        }
        ProjectDescription[] projetdata;
        Object[][] data ;
        projetdata = ReaderPropertiesJsonFile
                .readJsonDataDescriptionProject(getPropertiesSuite().getProperty("pathdataproject"));
        data = new Object[projetdata.length][2];
        int index = 0;
        for( ProjectDescription projet : projetdata){
            data[index][1] = projet;

            if(getWebDriverType().equals(WebDriverType.FIREFOX)){
                data[index][0] = employees[1];
            } else if (getWebDriverType().equals(WebDriverType.CHROME)) {
                data[index][0] = employees[3];
            }else {
                data[index][0] = employees[5];
            }
            index++;
        }
        return data;
    }

    @Test(testName = "Feuille temps projet",dataProvider = "dataFeuilledeTemps", groups = {"TIME","feuilletemp"}
            ,description = "Vérification des temps répartie avec le total")
    @XrayTest(key="TestHMR-107" ,summary = "Test Feuille de temps",description = "Verifier que le total des temps correspond",labels = "Core Time")
    @Requirement(key = "PRO-111")
    public void testFeuilledetemps(Employee adminUser, ProjectDescription projectdata ){
        //Arrange

        //Act
        ProjectReportSearch pageproject = new LoginPage()
                .inputUserName(adminUser.getUser().getUsername())
                .inputPwd(adminUser.getUser().getPassword())
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
}
