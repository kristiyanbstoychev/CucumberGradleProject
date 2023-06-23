package utils;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static tests.GlobalVariables.driver;
import static tests.GlobalVariables.localTempDirectoryLinux;
import static tests.GlobalVariables.localTempDirectoryWindows;
import static tests.GlobalVariables.operationSystem;

public class TestResultsWatcher implements TestWatcher {

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        driver.quit();
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        driver.quit();
    }

    //takes a screenshot, if a test fails and saves it in the system temp folder
    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        String screenshotFileName = "";
        if (operationSystem.contains("Windows")) {
            screenshotFileName = localTempDirectoryWindows + extensionContext.getDisplayName() + "_" + TestDataGeneration.formatDate("hh-mm-ss_dd.MM", "bg") + ".jpeg";
        }
        if (operationSystem.contains("Linux")) {
            screenshotFileName = localTempDirectoryLinux + extensionContext.getDisplayName() + "_" + TestDataGeneration.formatDate("hh-mm-ss_dd.MM", "bg") + ".jpeg";
        }

        takeSnapShot(driver, screenshotFileName);
        System.out.println("Test failed! Screenshot saved to: " + screenshotFileName);

        driver.quit();
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        driver.quit();
    }

    //method used for taking screenshots
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile = new File(fileWithPath);

        //Copy file at destination
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

