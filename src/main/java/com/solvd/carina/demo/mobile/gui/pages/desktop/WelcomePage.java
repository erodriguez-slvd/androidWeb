package com.solvd.carina.demo.mobile.gui.pages.desktop;

import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass =WelcomePageBase.class)
public class WelcomePage extends WelcomePageBase {
    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePageBase goToEbayWeb() {
        return initPage(getDriver(), HomePageBase.class);
    }
}
