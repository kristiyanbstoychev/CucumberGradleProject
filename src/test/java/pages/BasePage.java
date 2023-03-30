package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    //Locators
    @FindBy(xpath = "//button[@data-testid='exampleId']")
    private static WebElement exampleWebElement;

    public static WebElement getExampleWebElement() {
        return exampleWebElement;
    }

    //Tests
    public void classSpecificMethod() {
        //....
    }

}
