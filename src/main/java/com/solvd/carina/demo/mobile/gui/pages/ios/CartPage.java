package com.solvd.carina.demo.mobile.gui.pages.ios;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase{
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "button[data-test-id='cta-top']")
    private ExtendedWebElement checkoutBtn;
    @FindBy(css = "button[data-test-id='cart-remove-item']")
    private ExtendedWebElement deleteBtn;
    @FindBy(css = "h2.page-notice__title")
    private ExtendedWebElement confirmationMessage;
    @FindBy(css = "div[class='grid-item-quantity-lower'] input[data-test-id='qty-textbox']")
    private ExtendedWebElement quantityBtn;
    @FindBy(css = "div[class='item-price font-title-3'] span[class='text-display-span']")
    private ExtendedWebElement productPrice;

    @Override
    public boolean isCheckoutBtnPresent() {
        return checkoutBtn.isPresent();
    }

    @Override
    public void clickOnDeleteBtn() {
        deleteBtn.click();
    }

    @Override
    public boolean isConfirmationMessagePresent() {
        return confirmationMessage.isPresent();
    }

    @Override
    public void selectQuantityOptions() {
        String option="2";
        quantityBtn.click();
        quantityBtn.sendKeys(Keys.DELETE);
        quantityBtn.type(option+"\n");
    }

    @Override
    public String getProductPrice() {
        return productPrice.getText();
    }
}
