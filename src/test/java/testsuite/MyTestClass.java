package testsuite;

import io.cucumber.java.it.Data;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class MyTestClass extends BaseTest {

    @DataProvider(name = "myDataProvider")
    public Object[][] dataProviderMethod() {
        Object[][] data = new Object[2][3];
        data[0][0] = "Admin";
        data[0][1] = "admin123";
        data[0][2] = "ACME";
        data[1][0] = "Admin";
        data[1][1] = "admin123";
        data[1][2] = "COCA";

        return data;
    }
    public MyTestClass(){
        super("src/main/resources/entry_data/scenario1/config.properties");
    }

    @Test(dataProvider = "myDataProvider")
    public void testFeuilledetemps(String username,String password,String nameproject){
        logger = extent.createTest("Feuille de temps")
                .assignCategory("Feature Gestion Temps")
                .assignDevice(configproperties.getBrowser().toString())
                .assignAuthor("Ben Omar");

        //Act
        ProjectReportSearch pageproject = new LoginPage(testsetup)
                .inputUserName(username)
                .inputPwd(password)
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
}