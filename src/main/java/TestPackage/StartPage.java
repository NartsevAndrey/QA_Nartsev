package TestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class StartPage extends TestsPreparation {

    public AuthorizationForm clickButtonLogin() {
        // нажимаем "войти в аккаунт"
        driver.findElement(By.cssSelector(".header2-nav__user")).click();
        return new AuthorizationForm();
    }

    public void checkTextOnButton() {
        // проверяем, что название кнопки изменилось на "Мой профиль"
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        Assert.assertEquals(profile.getAttribute("textContent"), "Мой профиль");
    }

    public void checkLogin() {
        // проверяем, что указан верный логин (email)
        // чтобы в коде страницы отобразился login нужно один раз кликнуть на кнопку "Мой профиль"
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        profile.click();
        WebElement email = driver.findElement(By.cssSelector(".header2-user-menu__email"));
        Assert.assertEquals(email.getAttribute("textContent"), "adnartsev@yandex.ru");
    }
}