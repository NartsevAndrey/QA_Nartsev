package TestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestsPreparation {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void preparation() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://beru.ru");
    }

    @AfterMethod
    public void clear() {
        // вылогиниваемся
        WebElement profile = driver.findElement(By.cssSelector(".header2-nav__user [class*='__text']"));
        // если был выполнен вход в систему
        if (profile.getAttribute("textContent").equals("Мой профиль")) {
            // кликаем на кнопку "Мой профиль"
            profile.click();
            // кликаем на кнопку "Выход"
            driver.findElement(By.cssSelector("[class*='item_type_logout']")).click();
        }
        // закрываем драйвер
        driver.quit();
    }

    // вспомогательная функция, оставляющая в строке только числа, и приводящая строку к целому числу
    public int parser(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)))
                res.append(s.charAt(i));
        return Integer.parseInt(new String(res));
    }
}