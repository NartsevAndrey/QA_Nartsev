package TestPackage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UserProfile extends TestsPreparation {

    @Step("Check the delivery city and the city in the upper left corner")
    public void checkDeliveryAddress() {
        WebElement cityName = driver.findElement(By.cssSelector("[class*='__region'] [class*='link__inner']"));
        WebElement deliveryAddress = driver.findElement(By.cssSelector("[class*='__region'] [class='link__inner']"));
        Assert.assertEquals(cityName.getAttribute("textContent"), deliveryAddress.getAttribute("textContent"));
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }
}