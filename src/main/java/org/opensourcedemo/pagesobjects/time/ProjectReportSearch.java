package org.opensourcedemo.pagesobjects.time;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;

import java.util.List;

@Log4j2
public class ProjectReportSearch extends PageObjectParent {
    @FindBy(css = "input[placeholder=\"Type for hints...\"]")
    WebElement inputprojectnamelement;
    @FindBy(css = "div.oxd-autocomplete-option>span")
    List<WebElement> optionelementsearched;
    @FindBy(css = "button.oxd-button--medium")
    WebElement viewbuttonelement;
    @FindBy(css = "div.col-alt")
    List<WebElement> listtimecell;
    @FindBy(css = "span.oxd-text--footer")
    WebElement totaltimeelement;
    public ProjectReportSearch(TestSetup paramtestsetup){
        super(paramtestsetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        log.info("Initialze Project Report Search");
    }

    public ProjectReportSearch typeSearchProjectByName(String paramName){
        log.info("type hint for searching project ");
        typeKeys(inputprojectnamelement,paramName);
        return this;
    }

    public ProjectReportSearch clickFirstOptionSearch(){
        log.info("Click the forst option project");
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        clickWithoutWait(optionelementsearched.getFirst());
        return this;
    }
    public ProjectReportSearch clickViewProject(){
        log.info("Click View Project");
        clickwithWait(viewbuttonelement);
        return this;
    }
    public boolean verifyIftimesMatcheesTotal(){
        log.info("verify times matched total");
        Double timecumulated = listtimecell.stream()
                .mapToDouble(ob -> Double.parseDouble(ob.getText()))
                .reduce(0, Double::sum);
        String testTotal =totaltimeelement.getText();
        Double total =
                Double.parseDouble(testTotal.substring(testTotal.lastIndexOf(" ")));
        return timecumulated.equals(total);
    }

}
