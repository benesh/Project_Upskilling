package org.opensourcedemo.pagesobjects.time;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.pagesobjects.BasePage;

import java.util.List;

@Log4j2
public class ProjectReportSearch extends BasePage {
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
    public ProjectReportSearch(){
        PageFactory.initElements(getDriver(),this);
        invisibilityLoader();
        log.info("Initialze Project Report Search");
    }

    public ProjectReportSearch typeSearchProjectByName(String paramName){
        log.info("type hint for searching project ");
        typeKeys(inputprojectnamelement,paramName);
        return this;
    }

    public ProjectReportSearch clickRightOptionSearch(String namedescription){
        log.info("Click the forst option project");
        invisibilityLoader();
        WebElement optionElementToSelect = optionelementsearched.stream()
                .filter(element -> element.getText().equals(namedescription))
                        .findFirst().get();
        clickElement(optionElementToSelect);
        return this;
    }
    public ProjectReportSearch clickViewProject(){
        log.info("Click View Project");
        waitOfVisibilityOf(viewbuttonelement);
        clickElement(viewbuttonelement);
        return this;
    }
    public boolean verifyIftimesMatcheesTotal(){
        log.info("verify times matched total");
        Double timecumulated = listtimecell.stream()
                .mapToDouble(ob -> Double.parseDouble(ob.getText()))
                .reduce(0, Double::sum);
        String testTotal = totaltimeelement.getText();
        Double total =
                Double.parseDouble(testTotal.substring(testTotal.lastIndexOf(" ")));
        return timecumulated.equals(total);
    }

}
