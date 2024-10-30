import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DriverTest {

    @Test
    public void startBrowserTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://litecart.stqa.ru");

        WebElement searchInput = driver.findElement(By.name("query"));
        searchInput.sendKeys("Red Duck");
        searchInput.sendKeys(Keys.ENTER);

        WebElement myElement = driver.findElement(By.id("box-search"));
        //WebElement elementImage = driver.findElement(By.cssSelector("a[class='fancybox zoomable'][title='Red Duck']"));
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        Assert.assertEquals(allLinks.size(), 51);

        //driver.quit();
    }
}
