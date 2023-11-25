package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class EmployeeDatailPage {
    WebDriver driver ;
    WebDriverWait wait ;
    @FindBy(css = ".oxd-topbar-header-title")
    WebElement titleElement;
    @FindBy(css="button.oxd-button--secondary:nth-child(2)")
    WebElement savebuttonElemnt;

    @FindBy(css = "li.oxd-topbar-body-nav-tab:nth-child(2)")
    WebElement employeeListelement;

    public EmployeeDatailPage(WebDriver paramdriver){
        driver = paramdriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
        log.info("Initialize EmployeeDatail Page ");
    }

    public EmployeeDatailPage clicksavebutton(){
        wait.until(ExpectedConditions.visibilityOfAllElements(savebuttonElemnt));
        savebuttonElemnt.click();
        log.info("Save Employe");
        return this;
    }
    public PimPage clickemployeeList(){
        wait.until(ExpectedConditions.visibilityOfAllElements(employeeListelement));
        employeeListelement.click();
        log.info("Click sur Employe List ou PIM Page");
        return new PimPage(driver);
    }

    public String getTitle(){
        log.info("Getting Tittle");
        return titleElement.getText();
    }
}
