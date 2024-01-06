package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.BasePage;

import java.util.Properties;

@Log4j2
public class BaseStep extends BasePage {
    ConfigProperties configFile;
    public BaseStep(){}
    public void initDriver(){
        setDriver(TestSetup.setupWebDriver(configFile.getBrowser(), configFile.getHeadless()));
    }
    public void initWait(){
        setWait(TestSetup.setupWebDriverWait(getDriver()));
    }
    public void initConfig(String pathconfig){
        configFile = new ConfigProperties(ReaderPropertiesJsonFile.readPropertiesFromFile(pathconfig)) ;
    }
//    @Parameters({"browser"})
    @BeforeAll
    public void initWebDriverType(){
        String path="src/main/resources/config/configcucumber/config.properties";
        initConfig(path);
    }
    @Before
    private void setup() {
        initDriver();
        initWait();
        getDriver().manage().window().maximize();
    }
    @Given("je vais à la page de login {string}")
    public void logInto(String url) {
        getDriver().get(url);
    }
    @After
    public void Teardown() {
        log.info("Test Terminé");
        getDriver().quit();
    }
}
