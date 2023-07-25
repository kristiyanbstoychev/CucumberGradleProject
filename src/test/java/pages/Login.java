package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;

public class Login extends BasePage {

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement userNameInputField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    public WebElement getUserNameInputField() {
        return userNameInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void login() {
        BaseTest.getWait().until(ExpectedConditions.visibilityOf(userNameInputField));
        userNameInputField.sendKeys("standard_user");
        passwordInputField.sendKeys("secret_sauce");
        loginButton.click();
        System.out.println("Login successful");
    }
}
