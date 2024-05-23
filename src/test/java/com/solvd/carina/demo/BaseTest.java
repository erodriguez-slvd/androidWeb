package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.solvd.carina.demo.utils.MobileContextUtils;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.Set;

@Listeners()
public class BaseTest implements IAbstractTest, IMobileUtils, ITestListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    String filePath = "src/test/resources/screenshots/";

    //AndroidChromeTest
    @BeforeSuite
    public void openEbayWeb() throws InterruptedException {
        WelcomePageBase welcome = initPage(getDriver(), WelcomePageBase.class);
        welcome.goToEbayWeb();
        Thread.sleep(5000);
    }

    //AndroidChromeTest
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

    //DesktopChromeTest
    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.debug("CarinaListener->onTestFailure");
        takeScreenshot(result);
    }

    //DesktopChromeTest
    public void takeScreenshot(ITestResult result) {
        String methodName = result.getName().trim() + result.id();
        if (ITestResult.FAILURE == result.getStatus()) {
            //Convert web driver object to TakeScreenshot
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            //Call getScreenshotAs method to create image file
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            try {
                //Move image file to new destination
                FileUtils.moveFile(srcFile, new File(filePath + methodName + ".png"));
                LOGGER.info("Placed screenshot in " + filePath + " as " + methodName + ".png" + " ***");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
