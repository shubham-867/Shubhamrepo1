package Application.Pages;

import Application.Base.Base;
import Application.Common.Common;
import Util.DB2Connect;
import Util.GenericMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class HomePage extends Base {

    public HomePage(WebDriver driver, ExtentTest test) throws IOException {
        super(driver,test);
    }

    Common common = new Common(driver,test);
    GenericMethods gm = new GenericMethods();
    Properties env = gm.envReader();
    DB2Connect db = new DB2Connect(driver,test);

    private static By popup= By.xpath("//a[@id=\"at-cv-lightbox-close\"]");
    private static By Tiltle = By.xpath("//a[contains(text(),'Selenium Easy')]");

    public void validateTitle()
    {
        common.verifyElement(Tiltle,"Selenium Easy");


    }
    // made  xpath declaratiion from public to private because not to expose it to outside code and concpet is encapsulation

    public void launchUrl()
    {
        driver.get(env.getProperty("AppUrl"));
       System.out.println(driver.getTitle()); //Selenium Easy Demo - Simple Form to Automate using Selenium
        common.clickAnElement(popup);
    }
    private By button1= By.xpath("");
    public void clickButton()
    { common.clickAnElement(button1);}

    private By radio1= By.xpath("");
    public void clickradio()
    {

        common.clickAnElement(radio1);

       // common.verifyElementIsSelected(radio1);
    }
    private By dynamictablerows = By.xpath("//table[@class=\"dataTable\"]//tbody//tr");
    private String dynamictablecolumn = "//table[@class=\"dataTable\"]//tbody//tr[%s]//td[2]";

    //url = http://demo.guru99.com/test/web-table-element.php#
    List<String> DBlist;
    public void verifyDynamicWebtable(String query, String col, String acct) throws Exception {
        List<WebElement> rowcount = driver.findElements(dynamictablerows);
        for(int i = 0; i<=rowcount.size();i++){
        String Group = common.getElement(common.getXpath(dynamictablecolumn,String.valueOf(i)));
        // getDB data;
           DBlist= db.fetchDBData(query,col,acct);


        }
    }

//    public void callpassmethod() {
//        List<List<String>> t2Rows = Arrays.asList(
//                Arrays.asList("Optical mouse", "120.00", "20", "2400.00"),
//                Arrays.asList("Gaming keyboard", "550.00", "30", "16500.00"),
//                Arrays.asList("320GB SATA HDD", "220.00", "32", "7040.00"),
//                Arrays.asList("500GB SATA HDD", "274.00", "13", "3562.00"),
//                Arrays.asList("1TB SATA HDD", "437.00", "11", "4807.00"),
//                Arrays.asList("RE-DVD ROM", "144.00", "29", "4176.00"),
//                Arrays.asList("DDR3 4GB RAM", "143.00", "13", "1859.00"),
//                Arrays.asList("Blu-ray DVD", "94.00", "28", "2632.00"),
//                Arrays.asList("WR-DVD", "122.00", "34", "4148.00"),
//                Arrays.asList("Adapter", "543.00", "28", "15204.00")
//        );
//
//        for (int i = 0; i < t2Rows.size(); i++) {
//
//            List<List<String>> ResultRows = Collections.singletonList(t2Rows.get(i));
//            common.pass(ResultRows);
//        }

//        common.pass("pass methoad");
    }

        // do toom

        //Check if a string contains only alphabets in Java using Regex //https://www.geeksforgeeks.org/check-if-a-string-contains-only-alphabets-in-java-using-regex/?ref=lbp
        //How to check if string contains only digits in Java//https://www.geeksforgeeks.org/how-to-check-if-string-contains-only-digits-in-java/?ref=lbp
        //Check if given string contains all the digits//https://www.geeksforgeeks.org/check-if-given-string-contains-all-the-digits/?ref=lbp
        //Traverse through a HashMap in Java//https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/?ref=leftbar-rightbar
        




