package Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureScreenShot {

    public  static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyy_MM_dd SSS");

    public static String captureScreen(WebDriver driver, long time) throws IOException {
        TakesScreenshot screen = (TakesScreenshot)driver;
        File src= screen.getScreenshotAs(OutputType.FILE);

//        File src = (TakesScreenshot)driver.getScreenshotAs(OutputType.FILE); same as above in a single line

        String dest = System.getProperty("Screenshotpath")+"Test-Screenshots\\"+ time+".png";
        System.out.println(System.getProperty("Screenshotpath"));
//        E:\SAMSUNG\MavenPractice\ScreenShot\2021_01_19\Test-Screenshots\1611001277807.png
        
        File target = new File(dest);
        FileUtils.copyFile(src, target);  //COpy the file to required destination
        return dest;
    }

    public static String generateFileName(ITestResult result)
    {
        Date date = new Date();
        String filename = result.getName()+"_"+dateFormat.format(date);
        return filename;

    }
}
