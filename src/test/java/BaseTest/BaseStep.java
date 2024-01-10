package BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.BasePage;

import java.util.Properties;

@Log4j2
public class BaseStep {
    ConfigProperties configFile;
    public BasePage basePage;
    public BaseStep(){
        String path="src/main/resources/config/configcucumber/config.properties";
        initConfig(path);
        basePage = new BasePage();
    }
    @Before
    public void setup() {
        log.info("setup Scenario");
        basePage.setDriver(TestSetup.setupWebDriver(configFile.getBrowser(), configFile.getHeadless()));
        basePage.setWait(TestSetup.setupWebDriverWait(basePage.getDriver()));
        basePage.getDriver().manage().window().maximize();
    }
    public void initDriver(){
        basePage.setDriver(TestSetup.setupWebDriver(configFile.getBrowser(), configFile.getHeadless()));
    }
    public void initWait(){
        basePage.setWait(TestSetup.setupWebDriverWait(basePage.getDriver()));
    }
    public void initConfig(String pathconfig){
        configFile = new ConfigProperties(ReaderPropertiesJsonFile.readPropertiesFromFile(pathconfig)) ;
    }
    @Given("je vais a la page de login {string}")
    public void je_vais_a_la_page_de_login(String url) {
        log.info("Go tu Url: "+url);
        basePage.getDriver().get(url);
    }
    @After
    public void Teardown() {
        log.info("Test Terminé");
        basePage.getDriver().quit();
    }
}
