package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;


public class DesktopChromeTest extends BaseTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void testOpenPage() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        home.open();
        Assert.assertTrue(home.isPageOpened(), "Home page is not opened");
    }

    @Test(suiteName = "HomeTest")
    public void searchbarTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        String search = "iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search), "The results do not match the searched product.");
    }

    @Test(suiteName = "HomeTest")
    public void carouselTitlesTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        Assert.assertTrue(home.areCarouselTitlesPresent(), "Carrousel's titles are not present.");
    }

    @Test(suiteName = "Product Test")
    public void productDetailsTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        home.clickOnACarouselProduct();
        home.switchToWindow();
        ProductDetailPageBase product = initPage(getDriver(), ProductDetailPageBase.class);
        product.isProductTitlePresent();
        product.isProductPricePresent();
        Assert.assertTrue(product.isBuyNowBtnPresent(), "BuyNow button is not present on Product Details.");
    }

    @Test(suiteName = "Product Test")
    public void itemIdTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        home.switchToWindow();
        Assert.assertTrue(product.isItemIdPresent(), "ItemID is not present on Product Details.");
    }

    @Test(suiteName = "CartTest")
    public void addProductToCartTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        home.switchToWindow();
        CartPageBase cart = product.clickOnAddToCartBtn();
        Assert.assertTrue(cart.isCheckoutBtnPresent(), "Checkout button is not present on Cart Page.");
    }

    @Test(suiteName = "CartTest")
    public void deleteProductFromCartTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        home.switchToWindow();
        CartPageBase cart = product.clickOnAddToCartBtn();
        cart.clickOnDeleteBtn();
        Assert.assertTrue(cart.isConfirmationMessagePresent(), "Deletion confirmation message is not shown.");
    }

    @Test(suiteName = "CartTest")
    public void changeQuantityTest() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product = home.clickOnACarouselProduct();
        home.switchToWindow();
        CartPageBase cart = product.clickOnAddToCartBtn();
        String oldPrice = cart.getProductPrice();
        cart.selectQuantityOptions();
        String newPrice = cart.getProductPrice();
        Assert.assertNotEquals(newPrice, oldPrice, "Total price has not changed.");
    }
}
