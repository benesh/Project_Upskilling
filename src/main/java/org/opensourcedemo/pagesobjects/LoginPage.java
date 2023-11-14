package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Log4j2
public class LoginPage {
    WebDriver driver;

    @FindBy(css="input[name=\"username\"]")
    private WebElement inputUsername;

    @FindBy(id="input[name=\"password\"]")
    WebElement inputPassword;

    @FindBy(id="button[type=\"submit\"]")
    WebElement bontouLogin;

    public LoginPage(WebDriver paramDriver){
        driver = paramDriver;
        PageFactory.initElements(driver,this);
    }

    public LoginPage inputUserName(String parma_unsername ){
        log.info("Typing user name " + parma_unsername);
        inputUsername.sendKeys(parma_unsername);
        return this;
    }
    public LoginPage inputPwd(String parma_password ){
        log.info("Typing user Password ");
        inputPassword.sendKeys(parma_password);
        return this;
    }

    public DashbordPage clickButtonLogin(){
        bontouLogin.click();
        return new DashbordPage(driver);
    }








}
