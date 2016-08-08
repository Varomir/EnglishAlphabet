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

    public ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private DriverData driverConfig;
    private static final By ROOT = By.cssSelector("#app-main-1010");
    public LeftPanel tree;
    public RightPanel grid;

    //public HomePage(ThreadLocal<WebDriver> threadDriver, DriverData driverConfig){
    public HomePage(WebDriver driver, DriverData driverConfig){
        threadDriver.set(driver);
        this.driverConfig = driverConfig;
        WebDriverWait wait = new WebDriverWait(driver, driverConfig.getTimeout());
        wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT));
        tree = new LeftPanel();
        grid = new RightPanel();
    }

    public class LeftPanel {

        private final By TREE = By.cssSelector("#dd-tree-1011");
        //private final By ALPHABET_LIST = By.cssSelector("#treeview-1017-body tr[data-recordid^='ext-record-']>td>div>span");
        private final By ALPHABET_LIST = By.cssSelector("#treeview-1017-body tr[data-recordid^='ext-record-']>td span");

        public LeftPanel(){
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), driverConfig.getTimeout());
            wait.until(ExpectedConditions.visibilityOfElementLocated(TREE));
            //wait.until(ExpectedConditions.presenceOfElementLocated(TREE));
        }

        public int getAlphabetSize() {
            int result = threadDriver.get().findElement(ROOT).findElement(TREE).findElements(ALPHABET_LIST).size();
            return result;
        }

        public List<String> getAlhpabetList() {
            List<String> actual = new ArrayList<>(32);
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), driverConfig.getTimeout());
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALPHABET_LIST));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALPHABET_LIST));
            for(WebElement item: threadDriver.get().findElement(ROOT).findElement(TREE).findElements(ALPHABET_LIST)) {
            //for(WebElement item: threadDriver.get().findElements(ALPHABET_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            System.out.println("### getAlhpabetList.size()" + actual.size());
            return actual;
        }
    }

    public class RightPanel {

        private final By GRID = By.cssSelector("#dd-grid-1018");
        private final By LETTER_LIST = By.cssSelector("#gridview-1021-body td.x-grid-cell-last>div.x-grid-cell-inner");

        public RightPanel(){
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), driverConfig.getTimeout());
            wait.until(ExpectedConditions.visibilityOfElementLocated(GRID));
            //wait.until(ExpectedConditions.presenceOfElementLocated(GRID));
        }

        public int getEnglishLetterSize() {
            int result = threadDriver.get().findElement(ROOT).findElement(GRID).findElements(LETTER_LIST).size();
            return result;
        }

        public List<String> getEnglishLettersList() {
            List<String> actual = new ArrayList<>(32);
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), driverConfig.getTimeout());
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LETTER_LIST));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LETTER_LIST));
            for(WebElement item: threadDriver.get().findElement(ROOT).findElement(GRID).findElements(LETTER_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            System.out.println("### getEnglishLettersList.size()" + actual.size());
            return actual;
        }
    }
}
