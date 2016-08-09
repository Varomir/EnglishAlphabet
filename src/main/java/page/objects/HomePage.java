package page.objects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import spring.constructors.DriverData;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    protected WebDriverWait wait;
    private static final By ROOT = By.cssSelector("#app-main-1010");
    public LeftPanel tree;
    public RightPanel grid;

    public HomePage(WebDriver driver, DriverData driverConfig){
        this.threadLocal.set(driver);
        wait = new WebDriverWait(threadLocal.get(), driverConfig.getTimeout());
        wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT));
        tree = new LeftPanel();
        grid = new RightPanel();
    }

    public class LeftPanel {

        private final By TREE = By.cssSelector("#dd-tree-1011");
        private final By ALPHABET_LIST = By.cssSelector("#treeview-1017-body tr[data-recordid^='ext-record-']>td>div>span");
        private final By DELETE_MENU = By.cssSelector("#menuitem-1014-textEl");

        public LeftPanel(){
            wait.until(ExpectedConditions.visibilityOfElementLocated(TREE));
        }

        public void removeLetter(String letter) {
            WebDriverWait wait2 = new WebDriverWait(threadLocal.get(), 15);
            String letterRow = "//tbody[@id='treeview-1017-body']/tr/td/div/span[contains(text(), '" + letter + "')]";
            rightClick(threadLocal.get().findElement(ROOT).findElement(TREE).findElement(By.xpath(letterRow)));
            threadLocal.get().findElement(DELETE_MENU).click();
            wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(letterRow)));
        }

        public List<String> getAlhpabetList() {
            WebDriverWait wait2 = new WebDriverWait(threadLocal.get(), 15);
            List<String> actual = new ArrayList<>(32);
            wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALPHABET_LIST));
            wait2.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALPHABET_LIST));
            for(WebElement item: threadLocal.get().findElements(ALPHABET_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }
    }

    public class RightPanel {

        private final By GRID = By.cssSelector("#dd-grid-1018");
        private final By LETTER_LIST = By.cssSelector("#gridview-1021-body td.x-grid-cell-last>div.x-grid-cell-inner");
        private final By REMOVE_BTN = By.cssSelector("#button-1024-btnEl");

        public RightPanel(){
            wait.until(ExpectedConditions.visibilityOfElementLocated(GRID));
        }

        public void removeLetter(String letter) {
            WebDriverWait wait2 = new WebDriverWait(threadLocal.get(), 15);
            String letterCheckbox = "//div[@id='gridview-1021']//tr[td[contains(@class, 'x-grid-cell-last')]/div[contains(text(), '" +
                    letter + "')]]/td[contains(@class, 'x-grid-cell-first')]/div/div";
            wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(letterCheckbox)));
            threadLocal.get().findElement(ROOT).findElement(GRID).findElement(By.xpath(letterCheckbox)).click();
            threadLocal.get().findElement(ROOT).findElement(GRID).findElement(REMOVE_BTN).click();
            wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(letterCheckbox)));
        }

        public List<String> getEnglishLettersList() {
            WebDriverWait wait2 = new WebDriverWait(threadLocal.get(), 15);
            List<String> actual = new ArrayList<>(32);
            wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LETTER_LIST));
            wait2.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LETTER_LIST));
            for(WebElement item: threadLocal.get().findElements(LETTER_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }
    }

    private void rightClick(WebElement element) {
        try {
            Actions action = new Actions(threadLocal.get()).contextClick(element);
            action.build().perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + element + " was not found in DOM "
                    + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable "
                    + e.getStackTrace());
        }
    }
}
