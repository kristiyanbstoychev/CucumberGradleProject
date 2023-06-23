package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TimeUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import utils.TestResultsWatcher;
import utils.CSVHandler;

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
import static tests.GlobalVariables.urlForTestingEnvVariable;
import static tests.GlobalVariables.webDriverManager;

//Execute tests in alphabetical order
@TestMethodOrder(MethodOrderer.MethodName.class)

//Enables the TestWatcher class, which monitors the test results (currently used only for taking screenshots if a test fails)
@ExtendWith(TestResultsWatcher.class)
public class ExampleBaseTest {

    //Executed before each test
    @BeforeEach
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
            case "chrome": {
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
                break;
            }
            case "firefox": {
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
                break;
            }
            case "safari": {
                webDriverManager = WebDriverManager.safaridriver();
                webDriverManager.setup();
                SafariOptions safariOptions = new SafariOptions();
                break;
            }
            case "mobile": {
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
                break;
            }
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
