
package wad.selenium;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class PageSeleniumTest {
    private WebDriver driver;
    private String baseAddress;

    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
        this.baseAddress = "http://localhost:8090/WepaHT/app/menu";
    }
    
    @Test
    public void menuPageIsVisible() {
        driver.get(baseAddress);
        Assert.assertTrue(driver.getPageSource().contains("Bus stop timetables"));
    }
    
    @Test
    public void registerPageVisible() {
        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("here"));
        element.click();
        Assert.assertTrue(driver.getPageSource().contains("Registeration"));
    }
    
    @Test
    public void loginPageVisible() {
        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Log in"));
        element.click();
        Assert.assertTrue(driver.getPageSource().contains("Log in here"));
    }
    
}
