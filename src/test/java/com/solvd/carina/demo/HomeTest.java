package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.*;
import com.solvd.carina.demo.utils.MobileContextUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {
    @Test
    public void searchbarTest() throws InterruptedException {
        HomePageBase home = initPage(getDriver(), HomePageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.WEB_CHROME);
        String search="iPad";
        home.searchForAProduct(search);
        Assert.assertTrue(home.doResultsMatchSearch(search));
    }
}
