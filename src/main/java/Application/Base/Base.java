package Application.Base;

import Util.CaptureScreenShot;
import Util.GenericMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sun.nio.cs.ext.MacDingbat;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public ExtentTest test;
    GenericMethods gm = new GenericMethods();
    Properties env = gm.envReader();
    WebDriverWait wait;

    public Base(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        wait= new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void pass(String msg) {
        try {
            MediaEntityModelProvider screenshot =
                    MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenShot.captureScreen(driver, System.currentTimeMillis())).build();
            test.pass(msg,screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void fail(String msg)  {
//        test.fail(msg);
       
       try{ MediaEntityModelProvider screenshot =
                MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenShot.captureScreen(driver,System.currentTimeMillis())).build();
        test.fail(msg, screenshot);
//        gm.UpdateStatus("Fail");
//        Assert.fail(msg);
        }
       catch (Exception ignored){

       }
    }


    private int implycitlyWait()   // where to use it.
    { 
        return Integer.parseInt(env.getProperty("implicitlyWait"));  // can we code this here only ? and how?
    }

    public void visibilityOfElementLocated(By element) {
        //        WebDriverWait wait = new WebDriverWait(driver, implycitlyWait()); //depcricated
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       try{
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));}
       catch (TimeoutException e){
           System.out.println(e);
       }


    }

    public void writeText(By elementLocation, String text) {
        visibilityOfElementLocated(elementLocation);
        driver.findElement(elementLocation).sendKeys(text);
    }
    public String readText(By elementLocation) {
        visibilityOfElementLocated(elementLocation);
        return driver.findElement(elementLocation).getText();
    }

    public WebElement getWebElement(By Element) {
        return driver.findElement(Element);
    }

    public void elementToBeClickable(By element) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(scrolljsExecutor(element))));
    }

    private By scrolljsExecutor(By element) { // Need to check again
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getWebElement(element));
        return element;
        
    }
// isclickble
    public void elementToBeClickable(String element) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(By.xpath(element))));
        //elementToBeClickable is used for checking an element is visible and enabled such that you can click it.
    }
// isenabled
    public void elementisEnabled(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void alterToBePresent(By element){
        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());

        }
        catch (TimeoutException e){
            System.out.println("WebDriver couldn’t locate the Alert");}

    }


}
