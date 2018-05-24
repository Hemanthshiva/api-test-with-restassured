package com.tenx.test.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public class AutomationHomePage extends BasePage {

    public AutomationHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,  this);
    }

    @FindBy(xpath = "//a[contains(text(),'Sign in')]") private WebElement signInLink;
    @FindBy(id = "email") private WebElement emailInput;
    @FindBy(id = "passwd") private WebElement passwordInput;
    @FindBy(id = "SubmitLogin") private WebElement signInButton;


    public AutomationMyAccountPage login(String userName, String password){
        signInLink.click();
        if (emailInput.isDisplayed()) {
            emailInput.sendKeys(userName);
            passwordInput.sendKeys(password);
            signInButton.click();
        } else {
            Assert.fail();
        }
        return new AutomationMyAccountPage(driver);
    }


    //input[@id='search_query_top']
}
