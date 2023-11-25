package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
<<<<<<< HEAD
=======
import org.openqa.selenium.support.ui.ExpectedConditions;
>>>>>>> 0e867b9ab3595ed021875b7456a0c846b3231034
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class AdminPage {
<<<<<<< HEAD

    WebDriver driver;
    WebDriverWait wait ;

    @FindBy(css = "")
    WebElement inputusername;
    @FindBy(css = ".orangehrm-header-container>.oxd-button--medium")
    WebElement addbutton;

    public AdminPage(WebDriver param_driver){
        driver = param_driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Initialize Admin Page");
    }

    public AddAdminPage clickaddbutton(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        addbutton.click();
        log.info("Click to Add Admin Page");
        return new AddAdminPage(driver);
=======
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement addadminelement;

    public AdminPage(WebDriver paramdriver){
        driver = paramdriver;
        PageFactory.initElements(driver,this);
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Initialize Admin Page");

    }

    public FromulerAddingAdmin clickAddAmdin(){
        wait.until(ExpectedConditions.visibilityOfAllElements(addadminelement));
        addadminelement.click();
        log.info("Click for Adding Admin ");
        return new FromulerAddingAdmin(driver);
>>>>>>> 0e867b9ab3595ed021875b7456a0c846b3231034
    }
}
