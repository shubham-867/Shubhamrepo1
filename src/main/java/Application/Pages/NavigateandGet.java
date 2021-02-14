package Application.Pages;

import Application.Base.Base;
import Application.Common.Common;
import Util.DB2Connect;
import Util.GenericMethods;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class NavigateandGet extends Base {


    public NavigateandGet(WebDriver driver, ExtentTest test) throws IOException {
        super(driver,test);
    }

    Common common = new Common(driver,test);
    GenericMethods gm = new GenericMethods();
    Properties env = gm.envReader();
    DB2Connect db = new DB2Connect(driver,test);

    private static By popup= By.xpath("//a[@id=\"at-cv-lightbox-close\"]");
    private static By Home = By.xpath("//div[@class=\"navbar-brand navbar-brand-centered\"]//a[text()='Demo Home']");
    public void testNavigatefunction(){
        driver.get(env.getProperty("AppUrl"));
        common.clickAnElement(popup);
//        common.clickAnElement(Home);
        driver.get(env.getProperty("URL2"));
        driver.navigate().back();
        System.out.println("moved back");
        System.out.println(driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println("moved forward");
        System.out.println(driver.getCurrentUrl());

//        driver.get() and driver.navigate().to(), both navigate to the given webpage
//        From the link given in the answer, it can be concluded that: navigate().to()
//        and get() do exactly the same thing. But, here is a scenario that you gave
//        which explains the difference, ie. Single-Page Applications. The difference
//        between these two methods comes not from their behavior, but from the behavior
//        in the way the application works and how browser deal with it. navigate().to()
//        navigates to the page by changing the URL like doing forward/backward navigation.
//        Whereas, get() refreshes the page to changing the URL. So, in cases where application
//        domain changes, both the method behaves similarly. That is, page is refreshed in both the cases.
//        But, in single-page applications, while navigate().to() do not refreshes the page, get() do.


    }


    public void testGet(){

    }
}
