package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.Iterator;
import java.util.Set;

public class BaseTest implements IAbstractTest, IMobileUtils {
    @BeforeSuite
    public void openEbayWeb() throws InterruptedException {
        WelcomePageBase welcome = initPage(getDriver(), WelcomePageBase.class);
        welcome.goToEbayWeb();
        Thread.sleep(5000);
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
