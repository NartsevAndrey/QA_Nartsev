package TestPackage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        WebDriver driver = TestsPreparation.getDriver();
        Screenshoter.makeScreenshot(driver);
    }
}

