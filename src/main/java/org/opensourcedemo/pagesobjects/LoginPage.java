package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Log4j2
public class LoginPage extends BasePage {
    @FindBy(css="input[name=\"username\"]")
    private WebElement inputUsername;

    @FindBy(css="input[name=\"password\"]")
    WebElement inputPassword;

    @FindBy(css="button[type=\"submit\"]")
    WebElement bontouLogin;

    public LoginPage(){
        log.info("Initialize Login Page");
        PageFactory.initElements(getDriver(),this);
    }
    public LoginPage inputUserName(String parma_unsername ){
        log.info("Typing user name " + parma_unsername);
        waitOfVisibilityOf(inputUsername);
        typeKeys(inputUsername,parma_unsername);
        return this;
    }
    public LoginPage inputPwd(String parma_password ){
        log.info("Typing user Password ");
        waitOfVisibilityOf(inputPassword);
        typeKeys(inputPassword,parma_password);
        return this;
    }
    public DashbordPage clickButtonLogin(){
        lamdaWaitIsDisplayed(bontouLogin);
        clickElement(bontouLogin);
        invisibilityLoader();
        return new DashbordPage();
    }
}
