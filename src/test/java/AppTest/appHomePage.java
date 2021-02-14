package AppTest;

import Application.Pages.HomePage;
import BaseTest.BaseTest;
import Util.DB2Connect;
import Util.XlsReader;
import Util.queryConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class appHomePage extends BaseTest {
    private HomePage homePage;
    private DB2Connect con;

    @Test(enabled = true)
    public void TC01verifyElements() throws Exception {

        String testName = "TC01verifyElements";
        String testDescription = "TC01verifyElements";

        test= extent.createTest(testName,testDescription);
        homePage = new HomePage(driver,test);
        homePage.launchUrl();
        homePage.validateTitle();

//        homePage.callpassmethod();
//        con= new DB2Connect(driver);
//        con.Db2Connection();
//        con.fetchDBData(queryConstants.sqlSec,queryConstants.col,"acc");

    }

    @Test(enabled = false)
    public void TC02progressBars() throws  Exception{
        homePage = new HomePage(driver,test);
        homePage.launchUrl();


    }



//    1. career progression
//    list down roles, tech, plan
//
//    A.imporve java while in sel auto( 1 year in java min)
//    dev on tech in testing - docker,devops, some cloud concepts
//    B.parally DS
//    C.parally grow on mangerial skills
//    D.target master in admin if posb
//    E.CM, investment bank coverdomian
//
//
//2. health & fitness focus
//> exersice suites best
//> nutrition
//
//3. pesonl finance
//
//4. west settlement
//> yt for ppl whoo migrated
//> improve english
//> research best possible way to escape
//
//5. travel  & lifesyle
//> places to travel
//> experinces to have

}
