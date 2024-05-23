package com.solvd.carina.demo.mobile.gui.pages.common;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductDetailPageBase extends AbstractPage implements IMobileUtils {
    public ProductDetailPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isItemIdPresent();

    public abstract CartPageBase clickOnAddToCartBtn();

    public abstract boolean isProductTitlePresent();

    public abstract boolean isProductPricePresent();

    public abstract boolean isBuyNowBtnPresent();

    public abstract void switchToWindow();
}
