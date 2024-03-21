package com.solvd.carina.demo.mobile.gui.pages.ios;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase{
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`label == \"Completar la transacción\"`][1]")
    private ExtendedWebElement checkoutBtn;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`label == \"Eliminar - iRobot Roomba j7+ Self-Emptying Vacuum Cleaning Robot - Certified Refurbished!\"`]")
    private ExtendedWebElement deleteBtn;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == \"se eliminó de tu carro de compras.\"`]")
    private ExtendedWebElement confirmationMessage;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeTextField[`label == \"Cant.\"`]")
    private ExtendedWebElement quantityBtn;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"US $329.40\"`][2]")
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
