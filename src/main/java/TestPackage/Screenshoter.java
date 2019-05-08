package TestPackage;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshoter {
    private static final String PATH_TO_SCREENSHOTS = new File("screenshots").getAbsolutePath();

    @Attachment(value = "{1}", type = "image/png")
    public static byte[] allureScreenshot(WebDriver driver, String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void makeErrorScreenshot(WebDriver driver) {
        Date date = new Date();
        allureScreenshot(driver, "error " + (new SimpleDateFormat("yyyy-mm-dd hh.mm.ss")).format(date));
    }

    public static String makeStepScreenshot(WebDriver driver) {
        Date date = new Date();
        String name = PATH_TO_SCREENSHOTS + "\\" +
                (new SimpleDateFormat("yyyy-mm-dd hh.mm.ss")).format(date)+ ".png";
        File temporaryFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            ImageIO.write(ImageIO.read(temporaryFile),"png", new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
