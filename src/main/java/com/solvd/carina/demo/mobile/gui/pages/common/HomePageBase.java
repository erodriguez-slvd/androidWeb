package com.solvd.carina.demo.mobile.gui.pages.common;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage implements IMobileUtils {
    public HomePageBase(WebDriver driver) {
        super(driver);
    }
    public abstract void searchForAProduct(String input) throws InterruptedException;
    public abstract boolean doResultsMatchSearch(String input);
}
