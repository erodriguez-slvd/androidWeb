package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AndroidChromeTest extends BaseTest {
    @Test(suiteName = "HomeTest")
    public void searchbarTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        String search = "iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search), "The results do not match the searched product.");
    }

    @Test(suiteName = "HomeTest")
    public void carouselTitlesTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        Assert.assertTrue(home.areCarouselTitlesPresent(), "Carrousel's titles are not present.");
    }

    @Test(suiteName = "ProductTest")
    public void productDetailsTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        product.switchToWindow();
        product.isProductTitlePresent();
        product.isProductPricePresent();
        Assert.assertTrue(product.isBuyNowBtnPresent(), "BuyNow button is not present on Product Details");
    }

    @Test(suiteName = "ProductTest")
    public void itemIdTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        product.switchToWindow();
        Assert.assertTrue(product.isItemIdPresent(), "ItemID is not present on Product Details");
    }

    @Test(suiteName = "CartTest")
    public void addProductToCartTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        product.switchToWindow();
        CartPageBase cart = product.clickOnAddToCartBtn();
        Assert.assertTrue(cart.isCheckoutBtnPresent(), "Checkout button is not present on Cart Page");
    }

    @Test(suiteName = "CartTest")
    public void deleteProductFromCartTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        product.switchToWindow();
        CartPageBase cart = product.clickOnAddToCartBtn();
        cart.clickOnDeleteBtn();
        Assert.assertTrue(cart.isConfirmationMessagePresent(), "Deletion confirmation message is not shown.");
    }

    @Test(suiteName = "CartTest")
    public void changeQuantityTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        product.switchToWindow();
        CartPageBase cart = product.clickOnAddToCartBtn();
        String oldPrice = cart.getProductPrice();
        cart.selectQuantityOptions();
        pause(5L);
        String newPrice = cart.getProductPrice();
        Assert.assertNotEquals(newPrice, oldPrice, "Total price has not changed.");
    }
}
