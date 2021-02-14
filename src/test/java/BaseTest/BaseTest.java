package BaseTest;

import Util.CaptureScreenShot;
import Util.GenericMethods;
import com.aventstack.extentreports.ExtentReports;  // resolved by cntrl+alt+shift+S-->module>aventstack> test
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.page.Page;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public ChromeOptions chromeoptions;
    public WebDriver driver;
    GenericMethods gm = new GenericMethods();
    Properties env = gm.envReader();
    public ExtentTest test ;
    public ExtentReports extent;
    public ExtentSparkReporter htmlSparkReporter;


    @BeforeSuite
    public void beforeSuite() {

        Date date = new Date();
        SimpleDateFormat datenow = new SimpleDateFormat("yyyy_MM_dd"); // MM = month , mm= minutes;
        String projectPath = "E:\\SAMSUNG\\MavenPractice"; // report storage path

//       1.  create Report folder
        if (!new File(projectPath + "\\Report").exists()) {
            new File(projectPath + "\\Report").mkdir();
        }

//       2.  Create Report folder with today's date
        if (!new File(projectPath + "\\Report\\" + datenow.format(date) + "\\").exists()) {
            new File(projectPath + "\\Report\\" + datenow.format(date) + "\\").mkdir();
        }
//        3.  Create Screenshot folder
        if (!new File(projectPath + "\\ScreenShot").exists()) {
            new File(projectPath + "\\ScreenShot").mkdir();
        }

//       4.  Create Screenshot folder with today's date
        if (!new File(projectPath + "\\ScreenShot\\" + datenow.format(date) + "\\").exists()) {
            new File(projectPath + "\\ScreenShot\\" + datenow.format(date) + "\\").mkdir();
        }

        String testResultPath = projectPath + "\\Report\\" + datenow.format(date) + "\\";
        String ScreenShotPath = projectPath + "\\ScreenShot\\" + datenow.format(date) + "\\";
        System.setProperty("Screenshotpath", ScreenShotPath);

//        String strDateFormat = "yyyy_MM_dd_hh_mm_ss_a";
        String strDateFormat = "yyyy_MM_dd_HH_mm_a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String fortmattedDate = dateFormat.format(date);

//    ExtenthtmlSparkReporter= look n feel of report;
//    ExtentReports= to make entry of test case in report
//    extenttest= update the status of tc.
// STEP01:create the Reporter;
//        htmlSparkReporter = new ExtenthtmlSparkReporter(testResultPath + System.getProperty("user.name").toUpperCase() + "_" + fortmattedDate + ".html");
        htmlSparkReporter = new ExtentSparkReporter(testResultPath + fortmattedDate + ".html");
        System.out.println(testResultPath + fortmattedDate + ".html");
        htmlSparkReporter.config().setDocumentTitle("Test Report");
        htmlSparkReporter.config().setReportName("Test Report");
        htmlSparkReporter.config().setTheme(Theme.DARK);
        htmlSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
//        htmlSparkReporter.config().setAutoCreateRelativePathMedia(true);
        //        htmlSparkReporter.config().setChartVisibilityOnOpen(true);
         //        htmlSparkReporter.config().setTestViewChartLocation();

        extent = new ExtentReports();
        extent.attachReporter(htmlSparkReporter); // STEP2 : Attach reporter created
        extent.setSystemInfo("Hostname","LocalHost");
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("TesterName","Shubham");

    }
    @Parameters("browser")
    @BeforeMethod

    public void initilializeBrowser(String browser) {

        if (browser.equalsIgnoreCase("firefox")) {
//            System.setProperty("webdriver.gecko.driver", env.getProperty("FireFoxDriverpath"));
//            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
//            String driverpath ="C:\\Users\\Samsung\\chromedriver_win32\\chromedriver.exe";
//            System.setProperty("webdriver.chrome.driver",driverpath);
            System.setProperty("webdriver.chrome.driver", env.getProperty("ChormeDriverpath"));
            chromeoptions = new ChromeOptions();
            chromeoptions.addArguments("--no-sandbox");
            driver = new ChromeDriver(chromeoptions);
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", env.getProperty("EdgeDriverpath"));
            driver = new EdgeDriver();
        }

        //driver.get("https://toolsqa.com/automation-practice-form/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(env.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(env.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);

    }

    @AfterMethod  // after each method
    public void tearDown(ITestResult result) {
        long id = Thread.currentThread().getId();

        String screenshot = null;
        try {
            screenshot = CaptureScreenShot.captureScreen(driver, System.currentTimeMillis());
            System.out.println("destinatio file path"+screenshot);
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail(result.getThrowable());
                test.fail("Screen Shot" + test.addScreenCaptureFromPath(screenshot));
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("After method");
                test.pass(MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));
                test.pass("Screen Shot: " + test.addScreenCaptureFromPath(screenshot));
//                test.pass("SS", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            } else {
                test.skip(MarkupHelper.createLabel(result.getName() + " SKIPPED", ExtentColor.ORANGE));
                test.skip(result.getThrowable());
            }

        } catch (IOException e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    @AfterSuite
    public void endTest() {
        extent.flush(); // very important method, withou this report is not created.
        driver.quit();
    }


}
