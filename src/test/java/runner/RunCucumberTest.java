package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features"
        ,glue = {"steps"}
        ,plugin = {"pretty","html:target/reports/cucumber-reports.html","json:target/reports/cucumber.json"}
        ,tags = ("@aLancer")
        ,monochrome = true)
public class RunCucumberTest {

}
