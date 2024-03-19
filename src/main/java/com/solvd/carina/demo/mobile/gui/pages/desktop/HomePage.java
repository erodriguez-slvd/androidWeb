package com.solvd.carina.demo.mobile.gui.pages.desktop;

import com.solvd.carina.demo.gui.pages.common.BrandModelsPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(css ="input[id='gh-ac']")
    private ExtendedWebElement searchBar;
    @FindBy(css ="input[id='gh-btn']")
    private ExtendedWebElement searchButton;
    @FindBy(xpath ="//ul[@class='srp-results srp-list clearfix']/li")
    private List<ExtendedWebElement> resultsList;
    @FindBy(xpath = "//div[@class='carousel vl-carousel carousel--slides carousel--peek']/descendant::h3")
    private List<ExtendedWebElement> todayDealsTitlesCarousel;
    @FindBy(xpath = "//div[@class='carousel vl-carousel carousel--slides carousel--peek']/descendant::button[@class='carousel__control carousel__control--next']")
    private ExtendedWebElement carouselNextButton;

    @Override
    public void searchForAProduct(String input) throws InterruptedException {
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

    @Override
    public boolean areCarouselTitlesPresent() {
        for (ExtendedWebElement e:todayDealsTitlesCarousel) {
            System.out.println(e.getText());
            if(e.getText().isEmpty()){
                return false;
            }
        }
        return false;
    }

    @Override
    public ProductDetailPageBase clickOnACarouselProduct() {
        //int index= (int) (Math.random()*todayDealsTitlesCarousel.size());
        int index=7;
        System.out.println(todayDealsTitlesCarousel.get(index).getText());
        carouselNextButton.click();
        pause(3L);
        todayDealsTitlesCarousel.get(index).click();
        pause(3L);
        return initPage(driver, ProductDetailPageBase.class);
    }

    @Override
    public void switchToWindow() {
        Set<String> handles=driver.getWindowHandles();
        Iterator it=handles.iterator();
        String parent= (String) it.next();
        String child= (String) it.next();
        driver.switchTo().window(child);
    }
}
