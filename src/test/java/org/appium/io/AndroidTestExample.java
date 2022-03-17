package org.appium.io;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class AndroidTestExample extends BaseTestNG {
//    private static AppiumDriverLocalService service;
    private final String PACKAGE = "io.appium.android.apis";
    private AndroidDriver<WebElement> driver;

    @BeforeSuite
    public void setUp() throws Exception {
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        File appDir = new File(classpathRoot, "");
//        File app = new File(appDir.getCanonicalPath(), "");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", "C:/JavaProjects/appium-maven-example/ApiDemos-debug.apk");
//        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
        capabilities.setCapability("noReset", false);
//        System.out.println("apk path: " + app.getAbsolutePath());
//        capabilities.setCapability("w3c", false);
//        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
//        driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFindElementsByAccessibilityId() {
        // Look for element by accessibility. In Android this is the "content-desc"
        List<WebElement> searchParametersElement = (List<WebElement>) driver.findElementsByAccessibilityId("Content");
        Assert.assertEquals(searchParametersElement.size(), 1);
    }

    @Test
    public void testFindElementsById() {
        // Look for element by ID. In Android this is the "resource-id"
        List<WebElement> actionBarContainerElements = (List<WebElement>) driver.findElementsById("android:id/action_bar_container");
        Assert.assertEquals(actionBarContainerElements.size(), 1);
    }

    @Test
    public void testFindElementsByClassName() {
        // Look for elements by the class name. In Android this is the Java Class Name of the view.
        List<WebElement> linearLayoutElements = (List<WebElement>) driver.findElementsByClassName("android.widget.FrameLayout");
        Assert.assertTrue(linearLayoutElements.size() > 1);
    }

    @Test
    public void testFindElementsByXPath() {
        // Find elements by XPath
        List<WebElement> linearLayoutElements = (List<WebElement>) driver.findElementsByXPath("//*[@class=\"android.widget.FrameLayout\"]");
        Assert.assertTrue(linearLayoutElements.size() > 1);
    }
}
