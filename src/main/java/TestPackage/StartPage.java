package TestPackage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StartPage extends TestsPreparation {

    @Step("Click on the login button")
    public AuthorizationForm clickButtonLogin() {
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        // нажимаем "войти в аккаунт"
        driver.findElement(By.cssSelector(".header2-nav__user")).click();
        return new AuthorizationForm();
    }

    @Step("Check the text on the button")
    public void checkTextOnButton() {
        // проверяем, что название кнопки изменилось на "Мой профиль"
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        Assert.assertEquals(profile.getAttribute("textContent"), "Мой профиль");
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Check the login")
    public void checkLogin() {
        // чтобы в коде страницы отобразился login нужно один раз кликнуть на кнопку "Мой профиль"
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        profile.click();
        // проверяем, что указан верный логин (email)
        WebElement email = driver.findElement(By.cssSelector(".header2-user-menu__email"));
        Assert.assertEquals(email.getAttribute("textContent"), "adnartsev@yandex.ru");
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Click on the change city button")
    public void clickButtonChangeCity() {
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        // нажимаем кнопку с названием города
        driver.findElement(By.cssSelector("[class*='__region'] [class*='link__inner']")).click();
    }

    @Step("Enter the city")
    public void changeCity(String city) {
        // ждем пока появится форма с выбором региона
        WebElement form = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("[data-bem*='region-select-form']"))));

        // вводим название искомого города
        WebElement cityName = form.findElement(By.cssSelector("[class='input__control']"));
        cityName.click();
        cityName.sendKeys(city);

        // ждем пока нужный город отобразиться в списке
        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.attributeContains(
                        By.cssSelector("[class*='region-suggest__list-item']"),
                        "textContent", city));

        // нажимаем ENTER
        cityName.sendKeys(Keys.ENTER);

        // ждем пока скроется список городов
        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.invisibilityOf(
                        driver.findElement(By.cssSelector("[class*='suggestick-list']"))));
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        // нажимаем ENTER
        cityName.sendKeys(Keys.ENTER);
        // обновляем страницу
        driver.navigate().refresh();
    }

    @Step("Check the city")
    public void checkCity(String city) {
        // находим элемент, отображающий название города
        WebElement cityName = driver.findElement(By.cssSelector("[class*='__region'] [class*='link__inner']"));
        // проверяем, что отображается верный город
        Assert.assertEquals(cityName.getAttribute("textContent"), city);
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Click on the setting button")
    public UserProfile clickButtonUserProfile() {
        // кликаем на кнопку "Мой профиль"
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        profile.click();
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        // кликаем на кнопку "Настройки"
        driver.findElement(By.cssSelector("[class*='item_type_settings']")).click();
        return new UserProfile();
    }

    @Step("Go to the desired category")
    public GoodsListPage findItem(String s) {
        // вводим нужный запрос в поле для поиска
        WebElement search = driver.findElement(By.cssSelector("[class*='input__control']"));
        search.click();
        search.sendKeys(s);
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        search.sendKeys(Keys.ENTER);
        return new GoodsListPage();
    }
}