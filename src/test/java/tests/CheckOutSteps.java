package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CheckOut;
import pages.Login;
import utils.TestDataGeneration;

import static tests.BaseTest.getWait;
import static tests.GlobalVariables.driver;

public class CheckOutSteps {

    Login loginPage = PageFactory.initElements(driver, Login.class);
    CheckOut checkOut = PageFactory.initElements(driver, CheckOut.class);

    String itemNameAllItems;
    String itemPrizeAllItems;

    String itemNameShoppingCart;
    String itemPrizeShoppingCart;

    String itemNameCheckOut;
    String itemPrizeCheckOut;

    @Given("that a customer is successfully logged in")
    public void thatACustomerIsSuccessfullyLoggedIn() {
        loginPage.login();
    }

    @And("he successfully adds an item to his shopping cart")
    public void heSuccessfullyAddsAnItemToHisShoppingCart() {
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getAddToCartButton()));
        checkOut.getAddToCartButton().click();

        itemNameAllItems = checkOut.getItemName().getText();
        itemPrizeAllItems = checkOut.getItemPrize().getText();

        getWait().until(ExpectedConditions.visibilityOf(checkOut.getRemoveFromCartButton()));
        System.out.println("Item successfully added to cart");
    }

    @Then("the should be able to finish the checkout process")
    public void theShouldBeAbleToFinishTheCheckoutProcess() {
        checkOut.getShoppingCart().click();
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getCheckOutButton()));

        itemNameShoppingCart = checkOut.getItemName().getText();
        itemPrizeShoppingCart = checkOut.getItemPrize().getText();

        Assertions.assertEquals(itemNameAllItems, itemNameShoppingCart);
        Assertions.assertEquals(itemPrizeAllItems, itemPrizeShoppingCart);
        System.out.println("Shopping cart content verified");

        checkOut.getCheckOutButton().click();

        getWait().until(ExpectedConditions.visibilityOf(checkOut.getFirstNameInput()));
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getLastNameInput()));
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getPostCodeInput()));

        checkOut.getFirstNameInput().sendKeys(TestDataGeneration.firstName);
        checkOut.getLastNameInput().sendKeys(TestDataGeneration.lastName);
        checkOut.getPostCodeInput().sendKeys(TestDataGeneration.postCode);

        checkOut.getContinueCheckOutButton().click();
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getFinishCheckOutButton()));

        itemNameCheckOut = checkOut.getItemName().getText();
        itemPrizeCheckOut = checkOut.getItemPrize().getText();

        Assertions.assertEquals(itemNameAllItems, itemNameCheckOut);
        Assertions.assertEquals(itemPrizeAllItems, itemPrizeCheckOut);
        System.out.println("Checkout form submitted successfully");
        checkOut.getFinishCheckOutButton().click();

        getWait().until(ExpectedConditions.visibilityOf(checkOut.getCheckOutCompleteMessage()));
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getBackToHomePageButton()));

        System.out.println("Order details verified");

        checkOut.getBackToHomePageButton().click();
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getAddToCartButton()));
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getShoppingCart()));
        getWait().until(ExpectedConditions.visibilityOf(checkOut.getHamburgerMenu()));
        Assertions.assertEquals(1,2);
        System.out.println("Navigated back to the home page");
    }
}
