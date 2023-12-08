package testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import org.opensourcedemo.listerners.Mylisterner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(Mylisterner.class)
public class TestExemple {

    @Test
    public void test1(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Wait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=\"username\"]")));
        WebElement inputLogin = driver.findElement(By.cssSelector("input[name=\"username\"]"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=\"username\"]")));
        WebElement inputPassword = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        wait.until(ExpectedConditions.visibilityOf(inputPassword));


        if(inputLogin.isDisplayed()){
            System.out.println("Password input is displayed");
        }else {
            System.out.println("PAswword input is not displayed");
        }

        driver.quit();

    }
    @Test
    public void test2(){
        System.out.println("Testing 2");
    }
    @Test
    public void test3(){
        System.out.println("Testing 2");
    }

}
