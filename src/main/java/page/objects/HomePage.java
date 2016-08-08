package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import spring.constructors.DriverData;

public class HomePage {

    protected WebDriver driver;
    private DriverData driverConfig;
    private static final By ROOT = By.cssSelector("#app-main-1010");
    public LeftPanel tree;
    public RightPanel grid;

    public HomePage(WebDriver driver, DriverData driverConfig){
        this.driver = driver;
        this.driverConfig = driverConfig;
        WebDriverWait wait = new WebDriverWait(driver, driverConfig.getTimeout());
        wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT));
        tree = new LeftPanel();
        grid = new RightPanel();
    }

    public class LeftPanel {

        private final By TREE = By.cssSelector("#dd-tree-1011");

        public LeftPanel(){
            WebDriverWait wait = new WebDriverWait(driver, driverConfig.getTimeout());
            wait.until(ExpectedConditions.visibilityOfElementLocated(TREE));
        }
    }

    public class RightPanel {

        private final By GRID = By.cssSelector("#dd-grid-1018");

        public RightPanel(){
            WebDriverWait wait = new WebDriverWait(driver, driverConfig.getTimeout());
            wait.until(ExpectedConditions.visibilityOfElementLocated(GRID));
        }
    }
}
