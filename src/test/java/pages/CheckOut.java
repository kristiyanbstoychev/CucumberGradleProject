package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOut extends BasePage {

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeFromCartButton;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private WebElement itemName;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private WebElement itemPrize;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkOutButton;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postCodeInput;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueCheckOutButton;

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishCheckOutButton;

    @FindBy(xpath = "//div[@id='checkout_complete_container']")
    private WebElement checkOutCompleteMessage;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private WebElement backToHomePageButton;

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getItemName() {
        return itemName;
    }

    public WebElement getItemPrize() {
        return itemPrize;
    }

    public WebElement getCheckOutButton() {
        return checkOutButton;
    }

    public WebElement getFirstNameInput() {
        return firstNameInput;
    }

    public WebElement getLastNameInput() {
        return lastNameInput;
    }

    public WebElement getPostCodeInput() {
        return postCodeInput;
    }

    public WebElement getRemoveFromCartButton() {
        return removeFromCartButton;
    }
    public WebElement getContinueCheckOutButton() {
        return continueCheckOutButton;
    }
    public WebElement getFinishCheckOutButton() {
        return finishCheckOutButton;
    }
    public WebElement getCheckOutCompleteMessage() {
        return checkOutCompleteMessage;
    }
    public WebElement getBackToHomePageButton() {
        return backToHomePageButton;
    }

}
