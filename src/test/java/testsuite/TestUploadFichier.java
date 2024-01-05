package testsuite;

import BaseTest.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.properties_manager.data_manager.Employee;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.myinfopage.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

@Log4j2
public class TestUploadFichier extends BaseTest {
    Employee[] employees;
    @Parameters({"browser","datainput"})
    public TestUploadFichier(String browser,String datainput){
        super();
        log.info("Initailize TestSecondreport");
        Properties prop = ReaderPropertiesJsonFile.readPropertiesFromFile(datainput);
        setPorpertiesSuite(prop);
        webDriverType = browser;
    }

    @DataProvider(name = "dataproviderUploadFichier")
    public Object[][] dataProviderMethodFileUpload(){
        log.info("Load Dayaprovider For Login");
        if (employees==null){
            employees = ReaderPropertiesJsonFile.readJsonEmployee(getPropertiesSuite().getProperty("pathuserdata"));
        }
        Object[][] data = new Object[1][3];

        if(getWebDriverType().equals(WebDriverType.FIREFOX)){
            data[0][0] = employees[1];
        } else if (getWebDriverType().equals(WebDriverType.CHROME)) {
            data[0][0] = employees[3];
        }else {
            data[0][0] = employees[5];
        }

        data[0][1] = getPropertiesSuite().getProperty("pathfiletoupload");
        data[0][2] = getPropertiesSuite().getProperty("pathfiletoupload")
                .substring(getPropertiesSuite().getProperty("pathfiletoupload").lastIndexOf("/") +1
                );
        return data;
    }
    @Test(testName = "Upload de document",dataProvider = "dataproviderUploadFichier",
            groups = {"MY_INFO","uppload"},description = "Upload de documentet vérification si le document est bien suavegardé")
    public void testFileUpload(Employee employe,String fileUpload,String fileName){
        //Arrange

        //Act
        MyInfoPage myinfopage = new LoginPage()
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
