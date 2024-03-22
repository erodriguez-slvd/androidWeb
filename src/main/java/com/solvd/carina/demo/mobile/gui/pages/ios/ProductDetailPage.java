package com.solvd.carina.demo.mobile.gui.pages.ios;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase{
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1[class='x-item-title__mainTitle'] span[class='ux-textspans ux-textspans--BOLD']")
    private ExtendedWebElement productTitle;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == \"main\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[@name == 'USD']")
    private ExtendedWebElement productPrice;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"¡Cómpralo ahora!\"`]")
    private ExtendedWebElement buyNowBtn;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`label == \"Agregar al carro de compras\"`]")
    private ExtendedWebElement addToCartBtn;
    @ExtendedFindBy(iosClassChain = "button[id='TABS_SPR']")
    private ExtendedWebElement shippingBtn;
    @ExtendedFindBy(iosClassChain = "div[class='ux-layout-section__textual-display ux-layout-section__textual-display--itemId']")
    private ExtendedWebElement itemId;

    @Override
    public boolean isItemIdPresent() {
        return itemId.isPresent();
    }
    @Override
    public CartPageBase clickOnAddToCartBtn() {
        addToCartBtn.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public boolean isProductTitlePresent() {
        System.out.println(productTitle.getText());
        return productTitle.isPresent();
    }

    @Override
    public boolean isProductPricePresent() {
        System.out.println(productPrice.getAttribute("name"));
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
