package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class AndroidChromeTest implements IAbstractTest, IMobileUtils {
    @BeforeSuite
    public void openEbayWeb() throws InterruptedException {
        WelcomePageBase welcome = initPage(getDriver(), WelcomePageBase.class);
        welcome.goToEbayWeb();
        Thread.sleep(5000);
    }
    @Test(suiteName = "HomeTest")
    public void searchbarTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        String search="iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search));
    }
    @Test(suiteName = "HomeTest")
    public void carouselTitlesTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        Assert.assertTrue(home.areCarouselTitlesPresent());
    }
    @Test(suiteName = "ProductTest")
    public void productDetailsTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product =home.clickOnACarouselProduct();
        product.switchToWindow();
        product.isProductTitlePresent();
        product.isProductPricePresent();
        Assert.assertTrue(product.isBuyNowBtnPresent());
    }
    @Test(suiteName = "ProductTest")
    public void itemIdTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product =home.clickOnACarouselProduct();
        product.switchToWindow();
        Assert.assertTrue(product.isItemIdPresent());
    }
    @Test(suiteName = "CartTest")
    public void addProductToCartTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        product.switchToWindow();
        CartPageBase cart=product.clickOnAddToCartBtn();
        Assert.assertTrue(cart.isCheckoutBtnPresent());
    }
    @Test(suiteName = "CartTest")
    public void deleteProductFromCartTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        product.switchToWindow();
        CartPageBase cart=product.clickOnAddToCartBtn();
        cart.clickOnDeleteBtn();
        Assert.assertTrue(cart.isConfirmationMessagePresent());
    }
    @Test(suiteName = "CartTest")
    public void changeQuantityTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        product.switchToWindow();
        CartPageBase cart=product.clickOnAddToCartBtn();
        String oldPrice= cart.getProductPrice();
        cart.selectQuantityOptions();
        pause(5L);
        String newPrice=cart.getProductPrice();
        Assert.assertFalse(oldPrice.equals(newPrice));
    }

    @AfterTest
    public void closeWindow() {
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        Set<String> handles = getDriver().getWindowHandles();
        Iterator it = handles.iterator();
        String parent = (String) it.next();
        if (((String) it.next()).isEmpty()) {
            getDriver().switchTo().window(parent);
        } else {
            String child = (String) it.next();
            getDriver().switchTo().window(child);
            getDriver().switchTo().window(parent);
        }
    }
}
