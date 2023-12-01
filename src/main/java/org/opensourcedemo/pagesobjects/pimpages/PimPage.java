package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;

@Log4j2
public class PimPage {
    WebDriver driver;
    WebDriverWait wait ;

    @FindBy(css="button.oxd-button--secondary:first-child")
    WebElement addButton;

    public PimPage(WebDriver paramDriver){
        driver = paramDriver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
        log.info("Initialize PimPage");
    }

    public AddEmployeePage clickAddButton(){
        wait.until(ExpectedConditions.visibilityOfAllElements(addButton));
        addButton.click();
        log.info("Click on Add Button");
        return new AddEmployeePage(driver);
    }
}
