package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage {

    @FindBy(xpath = "//a[@href='http://courses.ultimateqa.com/users/sign_in']")
    private WebElement logInExerciseLink;

    @FindBy(xpath = "//a[@href='/users/sign_up']")
    private WebElement createAccountLink;

    @FindBy(xpath = "//input[@id='user[first_name]']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='user[last_name]']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='user[email]']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='user[password]']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='user[terms]']")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//button[@class='button button-primary g-recaptcha']")
    private WebElement submitRegistrationButton;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInputField;

    @FindBy(xpath = "//button[@class='dropdown__toggle-button']")
    private WebElement myProfileNavigationMenu;

    @FindBy(xpath = "//li[@class='dropdown__menu-item']//a[@href='/account']")
    private WebElement myAccountDropDownItem;

    @FindBy(xpath = "//a[@href='/enrollments']")
    private WebElement myDashboardNavigationItem;

    public WebElement getLogInExerciseLink() {
        return logInExerciseLink;
    }

    public WebElement getCreateAccountLink() {
        return createAccountLink;
    }

    public WebElement getFirstNameInput() {
        return firstNameInput;
    }

    public WebElement getLastNameInput() {
        return lastNameInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getTermsCheckbox() {
        return termsCheckbox;
    }

    public WebElement getSubmitRegistrationButton() {
        return submitRegistrationButton;
    }

    public WebElement getSearchInputField() {
        return searchInputField;
    }

    public WebElement getMyProfileNavigationMenu() {
        return myProfileNavigationMenu;
    }

    public WebElement getMyAccountDropDownItem() {
        return myAccountDropDownItem;
    }

    public WebElement getMyDashboardNavigationItem() {
        return myDashboardNavigationItem;
    }
}
