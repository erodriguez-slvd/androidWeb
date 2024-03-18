package com.solvd.carina.demo.mobile.gui.pages.common;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CartPageBase extends AbstractPage implements IMobileUtils {
    public CartPageBase(WebDriver driver) {
        super(driver);
    }
    public abstract boolean isCheckoutBtnPresent();
    public abstract void clickOnDeleteBtn();
    public abstract boolean isConfirmationMessagePresent();
    public abstract void selectQuantityOptions();
    public abstract String getProductPrice();
}
