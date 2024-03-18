package com.solvd.carina.demo.mobile.gui.pages.android;

import com.solvd.carina.demo.mobile.gui.pages.common.ContactUsPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
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
    @FindBy(css = "h2[class='vl-card-header__headline']")
    private ExtendedWebElement interest;
    @FindBy(xpath = "//div[@class='carousel vl-carousel carousel--slides carousel--peek']/descendant::button[@class='carousel__control carousel__control--next']")
    private ExtendedWebElement carouselNextButton;

    public void switchToWindow(){
        Set<String> handles=driver.getWindowHandles();
        Iterator it=handles.iterator();
        String parent= (String) it.next();
        driver.switchTo().window(parent);
    }

    @Override
    public void searchForAProduct(String input) throws InterruptedException {
        switchToWindow();
        searchBar.click();
        searchBar.type(input);
        searchButton.click();
        pause(5);
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
    public boolean areCarouselTitlesPresent(){
        swipe(interest,3);
        for (ExtendedWebElement e:todayDealsTitlesCarousel) {
            LOGGER.info(e.getText());
            if(e.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }
    @Override
    public ProductDetailPageBase clickOnACarouselProduct(){
        swipe(interest,4);
        //int index= (int) (Math.random()*todayDealsTitlesCarousel.size());
        int index=3;
        System.out.println(todayDealsTitlesCarousel.get(index).getText());
        carouselNextButton.click();
        pause(3L);
        todayDealsTitlesCarousel.get(index).click();
        pause(3L);
        return initPage(getDriver(), ProductDetailPageBase.class);
    }
}
