package testsuite;

import io.cucumber.java.it.Data;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.time.ProjectReportSearch;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class MyTestClass extends BaseTest {
    public MyTestClass(){
        super("src/main/resources/entry_data/scenario1/config.properties");
    }
    @DataProvider(name = "mydata")
    public Employee[][] dataproviderMethod(){
        Employee[][] data = new Employee[1][1];
        data[0][0] = configproperties.getEmployee().getFirst();
        data[0][1] = configproperties.getEmployee().get(1);
        return data;
    }

    @Test(testName = "Create Employee PIM" )
    public void TestCreateEmployeePIM(Employee[] employees){
        logger = extent.createTest("Create Employee PIM");
        logger.assignAuthor("Ben Omar")
                .assignCategory("Sanity check");
        //Arrange
        String title="PIM";
        //Act
        String titlegetted = new LoginPage(testsetup)
                .inputUserName(employees[0].getUser().getUsername())
                .inputPwd(employees[0].getUser().getPassword())
                .clickButtonLogin()
                .clickPimPage()
                .clickAddButton()
                .typeFirstName(employees[1].getFirstname())
                .typeMiddletName(employees[1].getMiddlename())
                .typeLastName(employees[1].getLastname())
                .clickSaveButton()
                .clickSaveButton()
                .getTitle();
        Assert.assertEquals(titlegetted,title);
    }
}