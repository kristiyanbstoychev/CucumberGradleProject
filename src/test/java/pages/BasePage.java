package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    //Locators
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private  WebElement hamburgerMenu;

    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private  WebElement logOutSideBarLink;

    @FindBy(xpath = "//a[@id='inventory_sidebar_link']")
    private  WebElement allItemsSideBarLink;

    @FindBy(xpath = "//a[@id='about_sidebar_link']")
    private  WebElement aboutSideBarLink;

    @FindBy(xpath = "//a[@id='reset_sidebar_link']")
    private  WebElement resetSideBarLink;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private  WebElement shoppingCart;


    public  WebElement getHamburgerMenu() {
        return hamburgerMenu;
    }

    public  WebElement getLogOutSideBarLink() {
        return logOutSideBarLink;
    }

    public  WebElement getAllItemsSideBarLink() {
        return allItemsSideBarLink;
    }

    public  WebElement getAboutSideBarLink() {
        return aboutSideBarLink;
    }

    public  WebElement getResetSideBarLink() {
        return resetSideBarLink;
    }

    public  WebElement getShoppingCart() {
        return shoppingCart;
    }

    //Tests
    public void classSpecificMethod() {
        //....
    }

}
