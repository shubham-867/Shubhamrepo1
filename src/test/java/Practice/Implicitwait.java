package Practice;

import Application.Base.Base;
import Util.GenericMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Implicitwait {

    public static void DT(){


        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SS");

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String date1= dateFormat.format(date);

        // Print the Date
        System.out.println("Current date and time is " +date1);
    }

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Samsung\\chromewebDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
//        System.out.println(System.currentTimeMillis());
        System.out.println("b4 pageload");
//        System.out.println(System.currentTimeMillis());
        Implicitwait.DT();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        System.out.println("afterpageload");
        Implicitwait.DT();
        System.out.println("b4 imlpicit");
        Implicitwait.DT();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("aftr imlpicit");
        Implicitwait.DT();
//        System.out.println(System.currentTimeMillis());

        driver.get("https://www.google.com/");



    }


}
