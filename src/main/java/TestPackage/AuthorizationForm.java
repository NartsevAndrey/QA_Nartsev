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

        WebElement Login = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-login-form"))))
                .findElement(By.name("login"));
        // вводим логин
        Login.sendKeys(login);
        // нажимаем ENTER
        Login.sendKeys(Keys.ENTER);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        // ждем пока появится форма с вводом пароля
        WebElement Password = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-password-form"))))
                .findElement(By.name("passwd"));
        // вводим пароль
        Password.sendKeys(password);
        // нажимаем ENTER
        Password.sendKeys(Keys.ENTER);
    }
}