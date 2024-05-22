package com.solvd.carina.demo.mobile.gui.pages.desktop;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import java.util.Iterator;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase {
    @FindBy(xpath = "//h1/descendant::span[@class='ux-textspans ux-textspans--BOLD']")
    private ExtendedWebElement productTitle;

    @FindBy(css = ".x-price-primary")
    private ExtendedWebElement productPrice;

    @FindBy(css = "#binBtn_btn_1")
    private ExtendedWebElement buyNowBtn;

    @FindBy(css = "div[data-testid='x-atc-action'] a[data-testid='ux-call-to-action']")
    private ExtendedWebElement addToCartBtn;

    @FindBy(css = "button[id='TABS_SPR']")
    private ExtendedWebElement shippingBtn;

    @FindBy(css = "div[class='ux-layout-section__textual-display ux-layout-section__textual-display--itemId']")
    private ExtendedWebElement itemId;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isItemIdPresent() {
        return itemId.isPresent();
    }

    @Override
    public CartPageBase clickOnAddToCartBtn() {
        addToCartBtn.click();
        return initPage(driver, CartPageBase.class);
    }

    @Override
    public boolean isProductTitlePresent() {
        return productTitle.isPresent();
    }

    @Override
    public boolean isProductPricePresent() {
        return productPrice.isPresent();
    }

    @Override
    public boolean isBuyNowBtnPresent() {
        return buyNowBtn.isPresent();
    }

    @Override
    public void switchToWindow() {
        Set<String> handles = driver.getWindowHandles();
        Iterator it = handles.iterator();
        String parent = (String) it.next();
        if (((String) it.next()).isEmpty()) {
            driver.switchTo().window(parent);
        } else {
            String child = (String) it.next();
            driver.switchTo().window(child);
        }
    }
}
