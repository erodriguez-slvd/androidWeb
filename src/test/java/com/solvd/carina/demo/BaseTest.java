package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class BaseTest implements IAbstractTest {
    @BeforeSuite
    public void openEbayWeb() throws InterruptedException {
        WelcomePageBase welcome=initPage(getDriver(), WelcomePageBase.class);
        welcome.goToEbayWeb();
        Thread.sleep(3000);
    }

    @AfterTest
    public void closeBrowser(){

    }
}
