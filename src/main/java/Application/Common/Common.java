package Application.Common;

import Application.Base.Base;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;


public class Common extends Base {

    public Common(WebDriver driver, ExtentTest test){
        super(driver,test);
        PageFactory.initElements(driver,this);
    }
//    WebDriverWait wait=new WebDriverWait(driver,30);
//try {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-users"))).click();
//    } catch (TimeoutException exception) {
//        System.out.println(exception.getCause().toString());
//    }


    public void clickAnElement(By element) {
        try{

            elementToBeClickable(element); // wait
            Assert.assertEquals(getWebElement(element).isEnabled(),true);
            System.out.println("Asssert"); // just for git practice ..
//            pass("In "+ pagename + "-"+getWebElement(element).getText() + "Element is successfully clicked");
//            pass("In " + "-"+getWebElement(element).getText() + "Element is successfully clicked");
// write for clicking a object wiout text
            getWebElement(element).click();
        }catch (NoSuchElementException e){  // what happens when element not found ??
//            fail("In "+ pagename + "-"+getWebElement(element).getText() + "Element is not clicked");
            fail("In "+  "-"+getWebElement(element).getText() + "Element is not clicked");
            fail(String.valueOf(e.getStackTrace()));
        }
    }

    public void selectByVisibleText(By element,String option)//dropdown
    {
        Select dropdown = new Select(getWebElement(element));
        dropdown.selectByVisibleText(option);

    }


    public void verfifyInputElement(By element, String expected){ // to verify a element value on screen, check visibility
        try{    visibilityOfElementLocated(element);
            Assert.assertEquals(getWebElement(element).getAttribute("value"),expected); // if assert fails no code is executed
        }
        catch (NoSuchElementException e) { // not executed bcz of assert error
            System.out.println("catch block");
            System.out.println(e);
//            follow link to solution for: to fail assertion after exception is caught..
//            https://sqa.stackexchange.com/questions/17414/testng-showing-as-test-passed-even-if-assertion-fails

        }

        //        boolean elestatus = false;
//        try{
//            Assert.assertEquals(getWebElement(element).isDisplayed(),true);
//            elestatus = getWebElement(element).isDisplayed();
//            if(elestatus==true){
//                Assert.assertEquals(getWebElement(element).getAttribute("value"),expected);
//                System.out.println("values matched");
//                pass("message");
//            }else{
//                System.out.println("values mismatched");
//                fail("msg") ;
//            }
//
//        }catch (NoSuchElementException e){
//            fail(String.valueOf(e.getStackTrace())); //fail();
//        }
    }
    public void verifyElement(By element, String expected){ // to verify a element value on screen, check visibility
try{    visibilityOfElementLocated(element);
        Assert.assertEquals(getWebElement(element).getText(),expected); // if assert fails no code is executed
    pass("Strings matched");
}
catch (NoSuchElementException e) { // not executed bcz of assert error
    System.out.println("catch block");
            System.out.println(e);
//            follow link to solution for: to fail assertion after exception is caught..
//            https://sqa.stackexchange.com/questions/17414/testng-showing-as-test-passed-even-if-assertion-fails

}
//        boolean elestatus = false;
//        try{
//            Assert.assertEquals(getWebElement(element).isDisplayed(),true);
//            elestatus = getWebElement(element).isDisplayed();
//            if(elestatus==true){
//                Assert.assertEquals(getWebElement(element).getText(),expected);
//                System.out.println("values matched");
//                pass("message");
//            }else{
//                System.out.println("values mismatched");
//                fail("msg") ;
//            }
//
//        }catch (Throwable e){
//            System.out.println("catch block");
//            System.out.println(e);
//            fail(String.valueOf(e.getStackTrace())); //fail();
//        }
    }

//    public void verfyElementIsEnabled(By element) {  //for radio, button
//        boolean elestatus = false;
//        try {
//            elestatus = getWebElement(element).isEnabled();
//            if (elestatus == true) {
//                System.out.println("isenabled");
//                //pass(message) extent
//            } else {
//                System.out.println("disabled");
//                //fail(msg) extent
//            }
//        } catch (NoSuchElementException e) {
//            e.getStackTrace(); //fail();}
//        }
//
//    }
    public boolean verifyElementIsDisabled(By element)
    {
        boolean elestatus = false;
        try {
            elestatus = getWebElement(element).isEnabled();
            if (elestatus == false) {
                System.out.println("isdisabled");
                //pass(message) extent
            } else {
                System.out.println("enabled");
                //fail(msg) extent
            }
        } catch (NoSuchElementException e) {
            e.getStackTrace(); //fail();}
        }

        return elestatus;
    }


    public boolean verifyEmentIsNotSelected(By radio1) {
        boolean elestatus = false;
        try {
            elestatus = getWebElement(radio1).isSelected();
            if (elestatus == false) {
                System.out.println("not selected");
                //pass(message) extent
            } else {
                System.out.println("already selected");
                //fail(msg) extent
            }
        } catch (NoSuchElementException e) {
            e.getStackTrace(); //fail();}
        }
        return elestatus;

    }

    public By getXpath(String dynamictablecolumn, String valueOf) {
        return By.xpath(String.format(dynamictablecolumn,valueOf)) ;

    }

    public String getElement(By xpath) {
        String value= null;
        Assert.assertEquals(getWebElement(xpath).isDisplayed(),true);
        value = getWebElement(xpath).getText();
        if(value.contains(",")|| value.contains("$")){
            value = value.replaceAll(",","");
            value = value.replaceAll("$","");

        }
        return value;
    }
    public  void HandleAlert(By element) {
//      soiurce: https://www.softwaretestinghelp.com/exception-handling-framework-selenium-tutorial-19/#:~:text=NoSuchElementException%3A%20This%20exception%20is%20due,is%20unable%20to%20initialize%20WebDriver.
         // placement inside or outsude of try block ?//
        try {
            alterToBePresent(element);
            driver.switchTo().alert().accept();
            } catch (NoAlertPresentException e) {
                System.out.println("An exceptional case");

        }
    }

}

