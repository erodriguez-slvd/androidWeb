package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;

public class IOSSafariTest implements IAbstractTest, IMobileUtils {
    @BeforeTest
    public void openSafari(){
        WelcomePageBase welcome= initPage(getDriver(), WelcomePageBase.class);
        welcome.openURL("https://www.ebay.com/");
    }

    //IOSSafariTest
    @AfterTest
    public void closeBrowser(){
        getDriver().quit();
    }

    @Test(suiteName = "Home Test")
    public void searchForAProductTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        String search="iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search), "The results do not match the searched product.");
    }
    @Test(suiteName = "Home Test")
    public void carouselTitlesTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        Assert.assertTrue(home.areCarouselTitlesPresent(), "Carrousel's titles are not present.");
    }
    @Test(suiteName = "Product Test")
    public void productDetailsTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        ProductDetailPageBase product =home.clickOnACarouselProduct();
        product.isProductTitlePresent();
        product.isProductPricePresent();
        Assert.assertTrue(product.isBuyNowBtnPresent(), "BuyNow button is not present on Product Details.");
    }
    @Test(suiteName = "Product Test")
    public void itemIdTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        ProductDetailPageBase product =home.clickOnACarouselProduct();
        Assert.assertTrue(product.isItemIdPresent(), "ItemID is not present on Product Details.");
    }

    @Test(suiteName = "Cart Test")
    public void addProductToCartTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        CartPageBase cart=product.clickOnAddToCartBtn();
        Assert.assertTrue(cart.isCheckoutBtnPresent(), "Checkout button is not present on Cart Page.");
    }
    @Test(suiteName = "Cart Test")
    public void deleteProductFromCartTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        CartPageBase cart=product.clickOnAddToCartBtn();
        cart.clickOnDeleteBtn();
        Assert.assertTrue(cart.isConfirmationMessagePresent(), "Deletion confirmation message is not shown.");
    }
    @Test(suiteName = "Cart Test")
    public void changeQuantityTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchIOSMobileWebContext();
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        CartPageBase cart=product.clickOnAddToCartBtn();
        String oldPrice= cart.getProductPrice();
        cart.selectQuantityOptions();
        pause(5L);
        String newPrice=cart.getProductPrice();
        Assert.assertFalse(oldPrice.equals(newPrice), "Total price has not changed.");
    }
}
