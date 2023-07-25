package tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CSVHandler;
import utils.TestDataGeneration;
import utils.TimeUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static tests.GlobalVariables.browserForTestingEnvVariableName;
import static tests.GlobalVariables.cfuiURL;
import static tests.GlobalVariables.csvEmailsList;
import static tests.GlobalVariables.csvNicknamesList;
import static tests.GlobalVariables.defaultBrowser;
import static tests.GlobalVariables.defaultDevice;
import static tests.GlobalVariables.deviceForTestingEnvVariableName;
import static tests.GlobalVariables.driver;
import static tests.GlobalVariables.getEnvVariable;
import static tests.GlobalVariables.isDebugModeEnabled;
import static tests.GlobalVariables.isMobile;
import static tests.GlobalVariables.localTempDirectoryLinux;
import static tests.GlobalVariables.localTempDirectoryWindows;
import static tests.GlobalVariables.operationSystem;
import static tests.GlobalVariables.urlForTestingEnvVariable;
import static tests.GlobalVariables.webDriverManager;

//Execute tests in alphabetical order
@TestMethodOrder(MethodOrderer.MethodName.class)

public class BaseTest {

    //Executed before each test
    //use Cucumbers Before annotation
    @Before
    public void doBeforeEachTest() {
        pickBrowser(getEnvVariable(browserForTestingEnvVariableName, defaultBrowser));

        if (getEnvVariable(browserForTestingEnvVariableName, defaultBrowser).equals("mobile")) {
            isMobile = true;
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.navigate().to(getEnvVariable(urlForTestingEnvVariable, cfuiURL));

        //Sets the content of email and onsite notifications
        GlobalVariables.setEmailAndNotificationsContent();

        //Reads a couple of csv files, storing emails and nicknames of users, needed for testing
        CSVHandler.readCSVFileAndSaveContentAsListOfStrings("testData/emails.csv", csvEmailsList);
        CSVHandler.readCSVFileAndSaveContentAsListOfStrings("testData/nicknames.csv", csvNicknamesList);
    }

    //Picks which browser and browser options should be used for testing
    public static void pickBrowser(String browser) {
        switch (browser) {
            case "chrome" -> {
                webDriverManager = WebDriverManager.chromedriver();
                webDriverManager.setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--ignore-certificate-errors");
                //if tests are executed in debug mode, start the browser in a non-headless window
                if (!isDebugModeEnabled) {
                    chromeOptions.addArguments("--headless", "--window-size=1920,1200");
                }
                chromeOptions.addArguments("--allow-running-insecure-content");
                chromeOptions.addArguments("--lang=en_US");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("-disable-features=NetworkService");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                webDriverManager = WebDriverManager.firefoxdriver();
                webDriverManager.setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--incognito");
                if (!isDebugModeEnabled) {
                    firefoxOptions.addArguments("--headless", "--window-size=1920,1200");
                }
                firefoxOptions.addArguments("--lang=en_US");
                firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "safari" -> {
                webDriverManager = WebDriverManager.safaridriver();
                webDriverManager.setup();
                SafariOptions safariOptions = new SafariOptions();
            }
            case "mobile" -> {
                webDriverManager = WebDriverManager.chromedriver();
                webDriverManager.setup();
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", getEnvVariable(deviceForTestingEnvVariableName, defaultDevice));
                ChromeOptions mobileOptions = new ChromeOptions();
                mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                mobileOptions.addArguments("--incognito");
                mobileOptions.addArguments("--ignore-certificate-errors");
                if (!isDebugModeEnabled) {
                    mobileOptions.addArguments("--headless");
                }
                mobileOptions.addArguments("--allow-running-insecure-content");
                mobileOptions.addArguments("--lang=en_US");
                mobileOptions.addArguments("--disable-gpu");
                mobileOptions.addArguments("-disable-features=NetworkService");
                mobileOptions.addArguments("--no-sandbox");
                mobileOptions.addArguments("--remote-allow-origins=*");
                mobileOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver = new ChromeDriver(mobileOptions);
            }
        }
    }

    //closes the browser, when the test is finished and takes a screenshot if a test fails
    @After
    public void afterMethod(Scenario scenario) {
        if(scenario.isFailed()) {
            String screenshotFileName = "";
            if (operationSystem.contains("Windows")) {
                screenshotFileName = localTempDirectoryWindows + scenario.getName() + "_" + TestDataGeneration.formatDate("hh-mm-ss_dd.MM", "bg") + ".jpeg";
            }
            if (operationSystem.contains("Linux")) {
                screenshotFileName = localTempDirectoryLinux + scenario.getName() + "_" + TestDataGeneration.formatDate("hh-mm-ss_dd.MM", "bg") + ".jpeg";
            }

            takeScreenshot(driver, screenshotFileName);
            System.out.println("Test failed! Screenshot saved to: \n" + screenshotFileName);
        }
        driver.quit();
    }

    public static void takeScreenshot(WebDriver webdriver, String fileWithPath) {
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
    
    //Scrolls the page to a desired element
    public static void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        TimeUtils.waitForSeconds(3);
    }

    public static WebDriverWait getWait(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebDriverWait getWait() {
        return getWait(15);
    }
}
