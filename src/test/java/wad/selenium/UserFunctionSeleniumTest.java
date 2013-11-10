
package wad.selenium;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class UserFunctionSeleniumTest {
    private WebDriver driver;
    private String baseAddress;

    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
        this.baseAddress = "http://localhost:8090/WepaHT/app/menu";
    }
    
//    @Test
//    public void userCreationAndLoggingIn() {
//       WebElement element = driver.findElement(By.name("busStop"));
//       element.sendKeys("konemies");
//       element.submit();
//       
//       Assert.assertTrue(driver.getPageSource().contains("konemies"));
//    }
    
//    private void createUser(String name,String password,WebDriver driver){
//        driver.findElement(null)
//    }
}
