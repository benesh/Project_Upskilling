package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.pagesobjects.BasePage;

@Log4j2
public class PimPage extends BasePage {
    @FindBy(css="button.oxd-button--secondary:first-child")
    WebElement addButton;
    public PimPage(){
        PageFactory.initElements(getDriver(),this);
        log.info("Initialize PimPage");
    }
    public AddEmployeePage clickAddButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(addButton));
        clickElement(addButton);
        log.info("Click on Add Button");
        return new AddEmployeePage();
    }
}
