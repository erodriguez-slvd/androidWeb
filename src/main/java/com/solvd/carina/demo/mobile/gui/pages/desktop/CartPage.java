package com.solvd.carina.demo.mobile.gui.pages.desktop;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "button[data-test-id='cta-top']")
    private ExtendedWebElement checkoutBtn;
    @FindBy(css = "button[data-test-id='cart-remove-item']")
    private ExtendedWebElement deleteBtn;
    @FindBy(css = "div[id='confirmation-status']")
    private ExtendedWebElement confirmationMessage;
    @FindBy(css = "div[class='quantity'] select[data-test-id='qty-dropdown']")
    private ExtendedWebElement quantityBtn;
    @FindBy(css = "div[class='item-price font-title-3']")
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
        quantityBtn.select(option);
        quantityBtn.getSelectedValue();
    }
    @Override
    public String getProductPrice() {
        return productPrice.getText();
    }
}
