package com.solvd.carina.demo.mobile.gui.pages.ios;

import com.solvd.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @ExtendedFindBy( iosClassChain="**/XCUIElementTypeTextField[`label == \"Buscar en eBay\"`]")
    private ExtendedWebElement searchBar;
    @ExtendedFindBy(iosClassChain ="**/XCUIElementTypeButton[`label == \"Buscar\"`]")
    private ExtendedWebElement searchButton;
    @ExtendedFindBy(iosClassChain ="**/XCUIElementTypeLink[`value == \"3\"`][1]/XCUIElementTypeStaticText")
    private List<ExtendedWebElement> resultsList;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == \"main\"`]/XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeLink")
    private List<ExtendedWebElement> todayDealsPricesCarousel;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == \"main\"`]/XCUIElementTypeOther[4]/XCUIElementTypeOther[5]")
    private ExtendedWebElement popular;
    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`label == \"Ir a la diapositiva siguiente, Ofertas del día: todos con envío gratis - Carrusel\"`]")
    private ExtendedWebElement carouselNextButton;

    @Override
    public void searchForAProduct(String input) throws InterruptedException {
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
    public boolean areCarouselTitlesPresent() {
        swipe(popular,1);
        for (ExtendedWebElement e:todayDealsPricesCarousel) {
            LOGGER.info(e.getAttribute("name"));
            if(e.getAttribute("name").isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ProductDetailPageBase clickOnACarouselProduct() {
        swipe(popular,3);
        //int index= (int) (Math.random()*todayDealsTitlesCarousel.size());
        int index=1;
        LOGGER.info(todayDealsPricesCarousel.get(index).getAttribute("name"));
        todayDealsPricesCarousel.get(index).click();
        pause(3L);
        return initPage(getDriver(), ProductDetailPageBase.class);
    }

    @Override
    public void switchToWindow() {
        Set<String> handles=getDriver().getWindowHandles();
        Iterator it=handles.iterator();
        String parent= (String) it.next();
        getDriver().switchTo().window(parent);
    }
}
