package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CreateAccountPage;
import utils.TestDataGeneration;

import static tests.BaseTest.getWait;
import static tests.GlobalVariables.driver;

public class CreateAccountSteps {
    CreateAccountPage createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);

    @Given("that the customer navigates to the registration page")
    public void thatTheCustomerNavigatesToTheRegistrationPage() {
        getWait().until(ExpectedConditions.visibilityOf(createAccountPage.getLogInExerciseLink()));
        createAccountPage.getLogInExerciseLink().click();
        getWait().until(ExpectedConditions.visibilityOf(createAccountPage.getCreateAccountLink()));
        createAccountPage.getCreateAccountLink().click();
        getWait().until(ExpectedConditions.visibilityOf(createAccountPage.getFirstNameInput()));
        System.out.println("Sign up page displayed");
    }

    @When("he inputs valid data and clicks the submit button")
    public void heInputsValidDataAndClicksTheSubmitButton() {
        createAccountPage.getFirstNameInput().sendKeys(TestDataGeneration.userName);
        createAccountPage.getLastNameInput().sendKeys(TestDataGeneration.userName);
        createAccountPage.getEmailInput().sendKeys("testUser123456@test.com");
        createAccountPage.getPasswordInput().sendKeys("test12345");
        createAccountPage.getTermsCheckbox().click();
        createAccountPage.getSubmitRegistrationButton().click();
        System.out.println("Registration successful");
    }

    @Then("he should be registered for the website")
    public void heShouldBeRegisteredForTheWebsite() {
        getWait().until(ExpectedConditions.visibilityOf(createAccountPage.getMyDashboardNavigationItem()));
        getWait().until(ExpectedConditions.visibilityOf(createAccountPage.getSearchInputField()));
        createAccountPage.getMyProfileNavigationMenu().click();
        getWait().until(ExpectedConditions.visibilityOf(createAccountPage.getMyAccountDropDownItem()));
        System.out.println("Customer successfully logged in");
    }
}
