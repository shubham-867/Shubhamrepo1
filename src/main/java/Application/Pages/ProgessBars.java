package Application.Pages;

import Application.Base.Base;
import Application.Common.Common;
import Util.DB2Connect;
import Util.GenericMethods;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ProgessBars extends Base {

    public ProgessBars(WebDriver driver, ExtentTest test){
        super(driver, test);
    }
    Common common = new Common(driver,test);
    GenericMethods gm = new GenericMethods();
    Properties env = gm.envReader();
    DB2Connect db = new DB2Connect(driver,test);

    private static By progessbardropdown = By.xpath("//li[@class=\"dropdown\"]//a[contains(text(),'Progress Bars')]");
    private static By bootstraprogress = By.xpath("//ul[@class=\"dropdown-menu\"]//li//a[contains(text(),'Bootstrap Progress bar')]");
    public void clickProgressBarsDropdown() {
        common.clickAnElement(progessbardropdown);
    }

    public void selectBootstrapProgessBar() {
       common.clickAnElement(bootstraprogress);
    }
}
