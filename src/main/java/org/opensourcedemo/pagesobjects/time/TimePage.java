package org.opensourcedemo.pagesobjects.time;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.BasePage;

import java.util.List;

@Log4j2
public class TimePage extends BasePage {
    @FindBy(css = "li:nth-child(3)>span")
    WebElement reportelement;
    @FindBy(css = "a.oxd-topbar-body-nav-tab-link")
    List<WebElement> menulistReport;
    public TimePage(){
        PageFactory.initElements(getDriver(),this);
        log.info("Initailze Time Page");
    }
    public TimePage clickReportMenulist(){
        log.info("click Report");
        clickElement(reportelement);
        return this;
    }
    public ProjectReportSearch clickReportProject(){
        log.info("click report search");
        waitOfVisibilityOf(menulistReport.getFirst());
        clickElement(menulistReport.getFirst());
        return new ProjectReportSearch();
    }
}
