package utils;
import org.openqa.selenium.WebElement;
import java.util.List;

import static tests.ExampleBaseTest.scrollIntoView;

public class TimeUtils {

    public static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickAvailableElementFromList(List<WebElement> webElementList) {
        int i = 0;
        while (!webElementList.get(i).isDisplayed()) {
            System.out.println("************* element not clickable...retrying *************");
            i++;
            if (i > 5) {
                System.out.println("************* element cannot be found *************");
                break;
            }
        }
        scrollIntoView(webElementList.get(i));

        webElementList.get(i).click();
    }

    public static void clickAvailableElement(WebElement webElement) {
        int i = 0;
        while (!webElement.isDisplayed()) {
            System.out.println("element not clickable");
            i++;
            if (i >= 5) {
                System.out.println("element cannot be found");
                break;
            }
        }
        scrollIntoView(webElement);
        webElement.click();
    }

}
