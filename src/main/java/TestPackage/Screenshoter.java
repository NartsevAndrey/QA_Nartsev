package TestPackage;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshoter {
    @Attachment(value = "{1}", type = "image/png")
    public static byte[] allureScreenshot(WebDriver driver, String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void makeScreenshot(WebDriver driver) {
        Date date = new Date();
        allureScreenshot(driver, (new SimpleDateFormat("yyyy-mm-dd hh.mm.ss")).format(date));
    }
}
