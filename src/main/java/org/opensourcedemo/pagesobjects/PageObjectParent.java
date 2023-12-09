package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.adminpages.AdminPage;

@Log4j2
public class PageObjectParent {
    protected TestSetup testsetup;

    public PageObjectParent(TestSetup paramtestetup){
        log.info("initialize Page Parent ");
        testsetup = paramtestetup;
    }
    public void clickwithWait(WebElement element){
        log.info("click in element");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
    }
    public void clickWithoutWait(WebElement element){
        log.info("click wihtout waiting");
        element.click();
    }
    public void typeKeys(WebElement element,String paramstring){
        log.info("Send keys in input");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(paramstring);
    }
    public void typeKeysWithClearingBefore(WebElement element,String paramstring){
        log.info("Send keys in input with clearing before");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(paramstring);
    }
}
