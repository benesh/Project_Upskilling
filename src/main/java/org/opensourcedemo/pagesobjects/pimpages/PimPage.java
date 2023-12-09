package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;

@Log4j2
public class PimPage extends PageObjectParent {
    @FindBy(css="button.oxd-button--secondary:first-child")
    WebElement addButton;
    public PimPage(TestSetup paramtestsetup){
        super(paramtestsetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize PimPage");
    }

    public AddEmployeePage clickAddButton(){
        testsetup.getWait().until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        log.info("Click on Add Button");
        return new AddEmployeePage(testsetup);
    }
}
