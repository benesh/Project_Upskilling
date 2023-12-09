package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import org.opensourcedemo.pagesobjects.PageObjectParent;

import java.time.Duration;

@Log4j2
public class AddAdminPage extends PageObjectParent {
    @FindBy(css = "")
    WebElement inputusername;
    @FindBy(css = "")
    WebElement inputpassword;

    public AddAdminPage(TestSetup param_testSetup){
        super(param_testSetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize Page Add Admin Page");
    }
}
