package com.tenx.test.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public abstract class BasePage {
    public static WebDriver driver;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }
}
