package com.solvd.carina.demo.mobile.gui.pages.ios;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase{
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class='vi-title__main']  h1")
    private ExtendedWebElement productTitle;
    @FindBy(css = "div[class='x-price-primary'] ")
    private ExtendedWebElement productPrice;
    @FindBy(css = "a[id='vi-bin-button']")
    private ExtendedWebElement buyNowBtn;
    @FindBy(css = "a#vi-cart-button")
    private ExtendedWebElement addToCartBtn;
    @FindBy(css = "a[id='vi-view-in-cart-button']")
    private ExtendedWebElement viewInCartBtn;
    @FindBy(css = "dl[class='ux-labels-values col-12 ux-labels-values--itemNumber']")
    private ExtendedWebElement itemId;

    @Override
    public boolean isItemIdPresent() {
        return itemId.isPresent();
    }
    @Override
    public CartPageBase clickOnAddToCartBtn() {
        if (addToCartBtn.isElementPresent()){
            addToCartBtn.click();
            pause(3L);
            viewInCartBtn.click();
        }else{
            viewInCartBtn.click();
        }
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public boolean isProductTitlePresent() {
        return productTitle.isPresent();
    }

    @Override
    public boolean isProductPricePresent() {
        return  productPrice.isPresent();
    }

    @Override
    public boolean isBuyNowBtnPresent() {
        swipe(productTitle,1);
        return buyNowBtn.isPresent();
    }

    @Override
    public void switchToWindow() {
        Set<String> handles=driver.getWindowHandles();
        Iterator it=handles.iterator();
        String parent= (String) it.next();
        if (((String) it.next()).isEmpty()){
            driver.switchTo().window(parent);
        }else {
            String child=(String) it.next();
            driver.switchTo().window(child);
        }
    }
}
