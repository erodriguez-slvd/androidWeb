package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.CartPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest{
    @Test
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
    @Test
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
    @Test
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
}
