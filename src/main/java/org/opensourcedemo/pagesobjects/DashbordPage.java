package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.pagesobjects.pimpages.PimPage;

import java.time.Duration;

@Log4j2
public class DashbordPage {

    @FindBy(css="a[href*='viewPimModule']")
    WebElement pimElement;
    WebDriver driver;
    WebDriverWait wait ;

    public DashbordPage(WebDriver paramDriver){
        driver = paramDriver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Initialize Dashbord Page");
    }

    public PimPage clickPimPage(){
        wait.until(ExpectedConditions.visibilityOfAllElements(pimElement));
        pimElement.click();
        return new PimPage(driver);
    }
}
