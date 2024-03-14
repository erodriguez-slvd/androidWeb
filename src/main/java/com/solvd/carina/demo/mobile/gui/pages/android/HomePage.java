package com.solvd.carina.demo.mobile.gui.pages.android;

import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css ="input[id='gh-ac']")
    private ExtendedWebElement searchBar;
    @FindBy(css ="input[id='gh-btn']")
    private ExtendedWebElement searchButton;
    @FindBy(xpath ="//ul[@class='srp-results srp-list clearfix']/li")
    private List<ExtendedWebElement> resultsList;

    @Override
    public void searchForAProduct(String input) throws InterruptedException {
        pause(4);
        searchBar.click();
        searchBar.type(input);
        searchButton.click();
    }

    @Override
    public boolean doResultsMatchSearch(String input) {
        for (ExtendedWebElement e:resultsList) {
            if (e.getText().contains(input)){
                return true;
            }
        }
        return false;
    }
}
