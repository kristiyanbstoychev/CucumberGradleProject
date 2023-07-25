package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Login;

import static tests.BaseTest.getWait;
import static tests.GlobalVariables.driver;

public class LoginSteps {
    Login loginPage = PageFactory.initElements(driver, Login.class);

    @Given("that a customer navigates to the login page")
    public void thatACustomerNavigatesToTheLoginPage() {
        Login loginPage = PageFactory.initElements(driver, Login.class);
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getUserNameInputField()));
        System.out.println("Login page displayed");
    }

    @When("he inputs valid data and clicks the login button")
    public void heInputsValidDataAndClicksTheLoginButton() {
        loginPage.login();
    }

    @Then("he should be successfully logged in")
    public void heShouldBeSuccessfullyLoggedIn() {
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getHamburgerMenu()));
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getShoppingCart()));
    }
}
