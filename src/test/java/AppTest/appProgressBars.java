package AppTest;

import Application.Pages.HomePage;
import Application.Pages.ProgessBars;
import BaseTest.BaseTest;
import Util.DB2Connect;
import org.testng.annotations.Test;

public class appProgressBars extends BaseTest {

    private HomePage homePage;
    private ProgessBars progessBars;
    private DB2Connect con;

    @Test
    public void TC01ProgressBar1()throws Exception{
        homePage = new HomePage(driver,test);
        progessBars = new ProgessBars(driver,test);

        homePage.launchUrl();
        progessBars.clickProgressBarsDropdown();
        progessBars.selectBootstrapProgessBar();

    }
}
