package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

@Listeners()
public class DesktopChromeTest implements IAbstractTest, ITestListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    String filePath = "src/test/resources/screenshots/";
    @Test
    public void testOpenPage() {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        home.open();
        Assert.assertTrue(home.isPageOpened(), "Home page is not opened");
    }
    @Test(suiteName = "HomeTest")
    public void searchbarTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        String search="iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search));
    }
    @Test(suiteName = "HomeTest")
    public void carouselTitlesTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        Assert.assertTrue(home.areCarouselTitlesPresent());
    }
    @Test(suiteName = "Product Test")
    public void productDetailsTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        home.clickOnACarouselProduct();
        home.switchToWindow();
        ProductDetailPageBase product=initPage(getDriver(), ProductDetailPageBase.class);
        product.isProductTitlePresent();
        product.isProductPricePresent();
        Assert.assertTrue(product.isBuyNowBtnPresent());
    }
    @Test(suiteName = "Product Test")
    public void itemIdTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        home.switchToWindow();
        Assert.assertTrue(product.isItemIdPresent());
    }
    @Test(suiteName = "CartTest")
    public void addProductToCartTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        home.switchToWindow();
        CartPageBase cart=product.clickOnAddToCartBtn();
        Assert.assertTrue(cart.isCheckoutBtnPresent());
    }
    @Test(suiteName = "CartTest")
    public void deleteProductFromCartTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        home.switchToWindow();
        CartPageBase cart=product.clickOnAddToCartBtn();
        cart.clickOnDeleteBtn();
        Assert.assertTrue(cart.isConfirmationMessagePresent());
    }
    @Test(suiteName = "CartTest")
    public void changeQuantityTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        ProductDetailPageBase product=home.clickOnACarouselProduct();
        home.switchToWindow();
        CartPageBase cart=product.clickOnAddToCartBtn();
        String oldPrice= cart.getProductPrice();
        cart.selectQuantityOptions();
        String newPrice=cart.getProductPrice();
        Assert.assertFalse(oldPrice.equals(newPrice));
    }
    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.debug("CarinaListener->onTestFailure");
        takeScreenshot(result);
    }
    public void takeScreenshot(ITestResult result) {
        String methodName = result.getName().toString().trim()+result.id();
        if (ITestResult.FAILURE == result.getStatus()) {
            //Convert web driver object to TakeScreenshot
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            //Call getScreenshotAs method to create image file
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            try {
                //Move image file to new destination
                FileUtils.moveFile(srcFile, new File(filePath+methodName+".png"));
                LOGGER.info("Placed screenshot in "+filePath+" as "+methodName+".png"+" ***");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
