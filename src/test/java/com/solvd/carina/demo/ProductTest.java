package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    @Test
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
    @Test
    public void itemIdTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        home.switchToWindow();
        ProductDetailPageBase product =home.clickOnACarouselProduct();
        product.switchToWindow();
        Assert.assertTrue(product.isItemIdPresent());
    }
}
