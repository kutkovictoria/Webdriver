import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DriverTest {



    @Test
    public void regionalSettingsLinkChangeCurrencyToEUR() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebElement changeRegionalSettingsElement = driver.findElement(By.xpath("//div[@id='region']//a[@class='fancybox-region']"));
        changeRegionalSettingsElement.click();
        WebElement currencyElement = driver.findElement(By.xpath("//div[@id='fancybox-content']//div[@id='box-regional-settings']//table//td[contains(text(),'Currency')]//select[@name='currency_code']"));
        Select dropdown = new Select(currencyElement);
        dropdown.selectByVisibleText("Euros");
        WebElement saveButton = driver.findElement(By.xpath("//*[@id='box-regional-settings']//button[@type='submit']"));
        saveButton.click();

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        wait.until(ExpectedConditions.textToBe(cartQuantityLabelLocator, "1"));
//        Assert.assertEquals(cartQuantityLabel.getText(),"1");
        WebElement currencyHomePageElement = driver.findElement(By.xpath("//*[@id='region']//div[@class='currency']/span"));

        Assert.assertEquals(currencyHomePageElement.getText(), "EUR");
    }

    @Test
    public void alertsTest(){
        String expectedAlertText = "I am a JS Alert";
        String expectedResultText = "You clicked: Ok";
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.cssSelector("[onclick='jsAlert()']")).click();
        Alert alert = driver.switchTo().alert();
        softAssert.assertEquals(alert.getText(), expectedAlertText);
        alert.accept();

        driver.findElement(By.cssSelector("[onclick='jsConfirm()']")).click();
        alert = driver.switchTo().alert();
        alert.accept();
        softAssert.assertEquals(driver.findElement(By.id("result")).getText(),expectedResultText);
    }
    @Test
    public void selectTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByValue("1");
        Assert.assertEquals((dropdown.getFirstSelectedOption().getText()),"Option 1");
    }

    public void waitForJquery(WebDriver driver, int waitSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(10))
       .until((ExpectedCondition<Boolean>) d -> (Boolean)(((JavascriptExecutor) d).executeScript("return jQuery.active === 0")));
//        {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//               return (Boolean) js.executeScript("jQuery.active === 0");
//            }
    }
    @Test
    public void framesTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/nested_frames");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        System.out.println(driver.findElement(By.tagName("body")).getText());

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        System.out.println(driver.findElement(By.tagName("body")).getText());

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");

        driver.quit();
    }
    @Test
    public void shadowTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://apearce.github.io/react-shadow-root/");

        WebElement focusDemo = driver.findElement(By.cssSelector(".output>focus-demo"));

        SearchContext focusDemoShadowDom = focusDemo.getShadowRoot();

        focusDemoShadowDom.findElement(By.cssSelector("input[placeholder = 'Input inside shadow dom' ]")).sendKeys("Hello World");
        driver.quit();

    }


    @Test
    public void startBrowserTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        driver.get("https://litecart.stqa.ru");

        driver.findElement((By.xpath("//nav[@id='site-menu']//a[text()='Rubber Ducks']")));

        WebElement purpleDuck = driver.findElement(By.cssSelector("[alt='Purple Duck']"));
        purpleDuck.click();
        WebElement addToCartButton = driver.findElement(By.name("[name='add_cart_product'][type='submit']"));
        addToCartButton.click();
        By cartQuantityLabelLocator = By.cssSelector("span[class='quantity']");
        WebElement cartQuantityLabel = driver.findElement(cartQuantityLabelLocator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.textToBe(cartQuantityLabelLocator, "1"));
        Assert.assertEquals(cartQuantityLabel.getText(),"1");







//        WebElement searchInput = driver.findElement(By.name("query"));
//        searchInput.sendKeys("Red Duck");
//        searchInput.sendKeys(Keys.ENTER);
//
//        WebElement myElement = driver.findElement(By.id("box-search"));
//        //WebElement elementImage = driver.findElement(By.cssSelector("a[class='fancybox zoomable'][title='Red Duck']"));
//        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
//        Assert.assertEquals(allLinks.size(), 55);

        //driver.quit();

        
    }
}
