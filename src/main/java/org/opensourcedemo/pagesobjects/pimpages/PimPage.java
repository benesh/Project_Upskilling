package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;

@Log4j2
public class PimPage {
    TestSetup testsetup;

    @FindBy(css="button.oxd-button--secondary:first-child")
    WebElement addButton;

    public PimPage(TestSetup paramtestsetup){
        testsetup = paramtestsetup;
        testsetup.getFwait().ignoring(ElementClickInterceptedException.class, java.util.NoSuchElementException.class);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize PimPage");
    }

    public AddEmployeePage clickAddButton(){
        testsetup.getFwait().until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        log.info("Click on Add Button");
        return new AddEmployeePage(testsetup);
    }
}
