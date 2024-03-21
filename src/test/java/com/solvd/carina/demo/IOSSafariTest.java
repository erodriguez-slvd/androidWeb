package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import org.testng.Assert;
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

    @Test
    public void safariTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        String search="iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search));
    }
    @Test
    public void carouselTitlesTest(){
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.NATIVE);
        Assert.assertTrue(home.areCarouselTitlesPresent());
    }

}
