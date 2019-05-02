package TestPackage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationForm extends TestsPreparation {

    @Step("Enter login")
    public void enterLogin(String login) {
        // вводим логин
        WebElement Login = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-login-form"))))
                .findElement(By.name("login"));
        Login.sendKeys(login);
        Screenshoter.makeScreenshot(driver); // делаем скриншот
        Login.sendKeys(Keys.ENTER);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        // вводим пароль
        WebElement Password = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-password-form"))))
                .findElement(By.name("passwd"));
        Password.sendKeys(password);
        Screenshoter.makeScreenshot(driver); // делаем скриншот
        Password.sendKeys(Keys.ENTER);
    }
}