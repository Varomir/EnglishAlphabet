import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import spring.constructors.DriverData;

@ContextConfiguration(locations={"/spring-config.xml"})
public class TestRunner extends AbstractTestNGSpringContextTests {

    protected ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @Autowired
    @Qualifier("driver")
    public DriverData driverData;

    @BeforeMethod
    public void init() {
        threadLocalDriver.set(initWebDriver(driverData.getBrowser()));
    }

    @AfterMethod(alwaysRun = true)
    public void webDriverTearDown() {
        threadLocalDriver.get().quit();
    }

    private WebDriver initWebDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
//            case "phantomjs":
//                driver = new PhantomJSDriver();
//                break;
            default:
                throw new RuntimeException("Unsopported broser [" + browser + "] exception");
        }
        return driver;
    }
}
