package pageFactory;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

public class TestPageFactory {

    WebDriver driver;
    Login objLogin;
    Home objHomePage;

    @BeforeTest
    public void setup(){
    	// 先引入chromedriver
        System.setProperty("webdriver.chrome.driver", "E:/eclipse/chromedriver.exe");
        //创建对象
        driver = new ChromeDriver();
        //隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //访问地址
        driver.get("https://www.testfire.net/login.jsp");
    }

    /**
     * This test go to https://www.testfire.net/login.jsp
     * Verify login page title as AltoroMutual
     * Login to application
     * Verify the home page using Dashboard message
     */
    @Test(priority=0)
    public void test_Home_Page_Appear_Correct(){
        //Create Login Page object
        objLogin = new Login(driver);
        //Verify login page title
        String loginPageTitle = objLogin.getLoginTitle();
        AssertJUnit.assertTrue(loginPageTitle.toLowerCase().contains("Altoro Mutual"));
        //login to application
        objLogin.loginToTestfire("admin", "admin");
        // go the next page
        driver.get("https://www.testfire.net/");
        objHomePage = new Home(driver);
        //Verify home page
        AssertJUnit.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("Congratulations!"));
    }
    @AfterTest
    public void close(){
        driver.quit();
    }
}