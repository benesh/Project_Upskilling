package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Log4j2
public class BasePage {
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    By loaderFomBySelector = By.cssSelector("div.oxd-form-loader");
    public BasePage(){
        log.info("initialize Page Parent");
    }
    public static WebDriver getDriver(){
        return driver.get();
    }
    public void setDriver(WebDriver paramDriver){
        log.info("Set driver Thread");
        driver.set(paramDriver);
    }
    public void setWait(WebDriverWait param_wait){
        log.info("initiate wait");
        wait.set(param_wait);
    }
    public void removeWait(){
        log.info("remove wait");
        wait.remove();
    }
    public void removeThreadDriver(){
        driver.remove();
    }
    public static WebDriverWait getWait(){
        return wait.get();
    }


    public void clickwithWait(WebElement element){
        log.info("click in element");
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
    }
    public void clickElement(WebElement element){
        log.info("click element");
        element.click();
    }
    public void typeKeys(WebElement element,String paramstring){
        log.info("Send keys in input");
        element.sendKeys(paramstring);
    }
    public void typeKeysWithClearingBefore(WebElement element,String paramstring){
        log.info("Send keys in input with clearing before");
        element.clear();
        element.sendKeys(paramstring);
    }
    public void lamdaWaitIsDisplayed(WebElement element){
        log.info("Until element is displayed");
        getWait().until(d ->element.isDisplayed());
    }
    public void waitOfVisibilityOf(WebElement element){
        log.info("Until element is visible");
        getWait().until(ExpectedConditions.visibilityOf(element));
    }
    public void waitOfVisibilityOfListElement(List<WebElement> listElement){
        log.info("Until element is visible");
        getWait().until(ExpectedConditions.visibilityOfAllElements(listElement));
    }
    public void invisibilityLoader(){
        log.info("Invisibility of loader");
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(loaderFomBySelector));
    }
    public void refreshBasePage(){
        log.info("Refresh Page");
        getDriver().navigate().refresh();
    }
}
