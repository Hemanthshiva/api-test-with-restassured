package com.tenx.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public class AutomationMyAccountPage extends BasePage {

    public AutomationMyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }




}
