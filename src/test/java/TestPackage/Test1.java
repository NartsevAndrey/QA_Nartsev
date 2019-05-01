package TestPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test1 extends TestsPreparation {
    @Test
    public void test1() {
        // нажимаем "войти в аккаунт"
        driver.findElement(By.cssSelector(".header2-nav__user")).click();

        // вводим логин
        WebElement login = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-login-form"))))
                .findElement(By.name("login"));
        login.sendKeys("adnartsev@yandex.ru");
        login.sendKeys(Keys.ENTER);

        // вводим пароль
        WebElement password = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-password-form"))))
                .findElement(By.name("passwd"));
        password.sendKeys("fylh.if");
        password.sendKeys(Keys.ENTER);

        // проверяем, что название кнопки изменилось на "Мой профиль"
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        Assert.assertEquals(profile.getAttribute("textContent"), "Мой профиль");

        // проверяем, что указан верный логин (email)
        // чтобы в коде страницы отобразился login нужно один раз кликнуть на кнопку "Мой профиль"
        profile.click();
        WebElement email = driver.findElement(By.cssSelector(".header2-user-menu__email"));
        Assert.assertEquals(email.getAttribute("textContent"), "adnartsev@yandex.ru");
    }
}