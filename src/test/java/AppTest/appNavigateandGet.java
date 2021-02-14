package AppTest;

import Application.Pages.NavigateandGet;
import BaseTest.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class appNavigateandGet extends BaseTest {


    @Test(enabled = false)
    public void test() throws IOException {
        NavigateandGet navigateandGet = new NavigateandGet(driver,test);
        navigateandGet.testNavigatefunction();

    }
}
