package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.Iterator;
import java.util.Set;

public class BaseTest implements IAbstractTest, IMobileUtils {
    @BeforeSuite
    public void openEbayWeb() throws InterruptedException {
        WelcomePageBase welcome=initPage(getDriver(), WelcomePageBase.class);
        welcome.goToEbayWeb();
        Thread.sleep(5000);
    }
    @AfterTest
    public void closeWindow(){
        Set<String> handles=getDriver().getWindowHandles();
        Iterator it=handles.iterator();
        String parent= (String) it.next();
        getDriver().switchTo().window(parent).close();
    }
}