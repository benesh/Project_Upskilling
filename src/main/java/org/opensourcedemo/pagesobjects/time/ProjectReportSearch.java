package org.opensourcedemo.pagesobjects.time;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;

import java.util.List;

import static io.opentelemetry.sdk.metrics.Aggregation.sum;

@Log4j2
public class ProjectReportSearch extends PageObjectParent {
    @FindBy(css = "input[placeholder=\"Type for hints...\"]")
    WebElement inputprojectnamelement;
    @FindBy(css = "div.oxd-autocomplete-option[role=\"option\"]")
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
        log.info("Initialze Project Report Search");
    }

    public ProjectReportSearch typeSearchProjectByName(String paramName){
        log.info("type hint for searching project ");;
        typeKeys(inputprojectnamelement,paramName);
        return this;
    }

    public ProjectReportSearch clickFirstOptionSearch(){
        log.info("Click the forst option project");
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        clickwithWait(optionelementsearched.getFirst());
        return this;
    }
    public ProjectReportSearch clickViewProject(){
        log.info("Click View Project");
        clickwithWait(viewbuttonelement);
        return this;
    }
    public boolean verifyIftimesMatcheesTotal(){
        log.info("verify times matched total");
        Long timecumulated = listtimecell.stream()
                .mapToLong(ob -> Long.parseLong(ob.getText()) +Long.parseLong(ob.getText()))
                .reduce(0, Long::sum);
        String testTotal =totaltimeelement.getText();
        Long total =
                Long.parseLong(testTotal.substring(testTotal.lastIndexOf(" "),testTotal.length() -1));
        return timecumulated.equals(total);
    }

}
