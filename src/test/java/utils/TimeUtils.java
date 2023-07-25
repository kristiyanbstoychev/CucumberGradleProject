package utils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static tests.BaseTest.getWait;
import static tests.BaseTest.scrollIntoView;

public class TimeUtils {
    public static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementToBeDisplayedList(int waitingTimeInSeconds, List<WebElement> webElement, int webElementIndex, String elementDescription) {
        for (int i = 1; i < waitingTimeInSeconds; i++) {
            try {
                Assertions.assertTrue(webElement.get(webElementIndex).isDisplayed());
                System.out.println(elementDescription);
                break;
            } catch (Exception e) {
                //                System.out.println("Couldn't find element, retrying. . . #" + i);
            }
            TimeUtils.waitForSeconds(1);
        }
    }

    //Finds the clickable element on the page, if more than one is present in the DOM
    public static void waitForClickableElement(List<WebElement> webElements, int numberOfElementsToBeChecked) {
        int elementNumber = 0;

        for (int i = 0; i < numberOfElementsToBeChecked; i++) {
            try {
                getWait(1).until(ExpectedConditions.elementToBeClickable(webElements.get(i)));
                elementNumber = i;
                break;
            } catch (Exception e) {
                System.out.println("Waiting for the element to be clickable");
            }
        }
        try {
            scrollIntoView(webElements.get(elementNumber));
            System.out.println("************* Element found *************");
            webElements.get(elementNumber).click();
        } catch (Exception e) {
            throw new ElementNotInteractableException("Element not displayed");
        }
    }
}
