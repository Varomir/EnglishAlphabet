import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.objects.HomePage;
import spring.constructors.DriverData;

@ContextConfiguration(locations={"/spring-config.xml"})
public class TestRunner extends AbstractTestNGSpringContextTests {

    protected ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final String AUT_PATH = "file:///" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "/";
    protected HomePage homePage;

    @Autowired
    @Qualifier("driver")
    public DriverData driverData;

    @BeforeMethod
    public void init() {
        threadLocalDriver.set(initWebDriver(driverData.getBrowser()));
        threadLocalDriver.get().get(AUT_PATH + driverData.getAutRelativePath());
        homePage = new HomePage(threadLocalDriver.get(), driverData);
        //homePage = new HomePage(threadLocalDriver, driverData);
    }

    @AfterMethod(alwaysRun = true)
    public void webDriverTearDown() {
        threadLocalDriver.get().quit();
    }

    private WebDriver initWebDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "iexplore":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\driver\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
//            case "phantomjs":
//                driver = new PhantomJSDriver();
//                break;
            default:
                throw new RuntimeException("Unsupported broser [" + browser + "] exception");
        }
        return driver;
    }
}
