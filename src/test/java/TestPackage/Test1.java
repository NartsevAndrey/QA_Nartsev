package TestPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends TestsPreparation {
    @Test
    public void test_1() {
        WebElement el = driver.findElement(By.cssSelector(".header2-nav__user"));
        el.click();
        WebElement logInFotm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-login-form"))));
        el = logInFotm.findElement(By.name("login"));
        logInFotm.findElement(By.name("login")).click();
        logInFotm.findElement(By.name("login")).sendKeys("adnartsev@yandex.ru");
        logInFotm.findElement(By.name("login")).sendKeys(Keys.ENTER);
        WebElement PassForm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-password-form"))));
        el = PassForm.findElement(By.name("passwd"));
        el.sendKeys("fylh.if");
        el.sendKeys(Keys.ENTER);
        WebElement note = driver.findElement(By.cssSelector("[class*='header2-nav__user']"));
        el = note.findElement(By.cssSelector("[class*='__text']"));
        Assert.assertEquals(el.getAttribute("textContent"), "Мой профиль");
        (new Actions(driver)).moveToElement(note).build().perform();
        el = driver.findElement(By.cssSelector("[class*='user-menu__email']"));
        Assert.assertEquals(el.getAttribute("textContent"), "adnartsev@yandex.ru");
    }
}