package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import spring.constructors.DriverData;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final By ROOT = By.cssSelector("#app-main-1010");
    public LeftPanel tree;
    public RightPanel grid;

    public HomePage(WebDriver driver, DriverData driverConfig){
        this.driver = driver;
        wait = new WebDriverWait(driver, driverConfig.getTimeout());
        wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT));
        tree = new LeftPanel();
        grid = new RightPanel();
    }

    public class LeftPanel {

        private final By TREE = By.cssSelector("#dd-tree-1011");
        private final By ALPHABET_LIST = By.cssSelector("#treeview-1017-body tr[data-recordid^='ext-record-']>td>div>span");

        public LeftPanel(){
            wait.until(ExpectedConditions.visibilityOfElementLocated(TREE));
        }

        public List<String> getAlhpabetList() {
            List<String> actual = new ArrayList<>(32);
            System.out.print(actual.size() + "_");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALPHABET_LIST));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALPHABET_LIST));
            for(WebElement item: driver.findElement(ROOT).findElement(TREE).findElements(ALPHABET_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }
    }

    public class RightPanel {

        private final By GRID = By.cssSelector("#dd-grid-1018");
        private final By LETTER_LIST = By.cssSelector("#gridview-1021-body td.x-grid-cell-last>div.x-grid-cell-inner");

        public RightPanel(){
            wait.until(ExpectedConditions.visibilityOfElementLocated(GRID));
        }

        public List<String> getEnglishLettersList() {
            List<String> actual = new ArrayList<>(32);
            System.out.print(actual.size() + "_");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LETTER_LIST));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LETTER_LIST));
            for(WebElement item: driver.findElement(ROOT).findElement(GRID).findElements(LETTER_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }
    }
}
