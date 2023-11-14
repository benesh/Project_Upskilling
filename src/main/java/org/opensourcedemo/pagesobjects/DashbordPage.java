package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class DashbordPage {

    @FindBy(css="a[href*='viewPimModule']")
    WebElement pimElement;
    WebDriver driver;

    public DashbordPage(WebDriver paramDriver){
        driver = paramDriver;
        PageFactory.initElements(driver,this);
        log.info("Initialize Dashbord Page");
    }

    public PimPage clickPimPage(){
        pimElement.click();
        return new PimPage(driver);
    }
}
