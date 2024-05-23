package com.solvd.carina.demo.mobile.gui.pages.android;

import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = WelcomePageBase.class)
public class WelcomePage extends WelcomePageBase {
    @FindBy(id = "com.android.chrome:id/search_box_text")
    private ExtendedWebElement searchBar;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePageBase goToEbayWeb() {
        driver.get("https://www.ebay.com/");
        return initPage(getDriver(), HomePageBase.class);
    }
}
