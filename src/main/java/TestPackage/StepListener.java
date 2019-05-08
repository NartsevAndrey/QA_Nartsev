package TestPackage;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.WebDriver;

public class StepListener implements StepLifecycleListener {

    @Override
    public void beforeStepStart(final StepResult result) {
        makeScreenshot(result);
    }

    @Override
    public void beforeStepStop(final StepResult result) {
        makeScreenshot(result);
    }

    private void makeScreenshot(final StepResult result) {
        Attachment att = new Attachment();
        att.setType("image/png");
        WebDriver driver = TestsPreparation.getDriver();
        att.setSource(Screenshoter.makeStepScreenshot(driver));
        result.withAttachments(att);
    }
}
