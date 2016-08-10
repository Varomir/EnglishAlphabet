package page.objects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import spring.constructors.DriverData;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public WebDriver driver;
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
        private final By DELETE_MENU = By.cssSelector("#menuitem-1014-textEl");

        public LeftPanel(){
            wait.until(ExpectedConditions.visibilityOfElementLocated(TREE));
        }

        public void removeLetter(String letter) {
            String letterRow = getLetterXPATH(letter);
            rightClick(driver.findElement(ROOT).findElement(TREE).findElement(By.xpath(letterRow)));
            driver.findElement(DELETE_MENU).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(letterRow)));
        }

        public List<String> getAlhpabetList() {
            List<String> actual = new ArrayList<>(32);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALPHABET_LIST));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALPHABET_LIST));
            for(WebElement item: driver.findElements(ALPHABET_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }

        private String getLetterXPATH(String letter) {
            return "//tbody[@id='treeview-1017-body']/tr/td/div/span[contains(text(), '" + letter + "')]";
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
            String letterCheckbox = getLetterXPATH(letter);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(letterCheckbox)));
            driver.findElement(ROOT).findElement(GRID).findElement(By.xpath(letterCheckbox)).click();
            driver.findElement(ROOT).findElement(GRID).findElement(REMOVE_BTN).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(letterCheckbox)));
        }

        public void dragAndDropToTheTree(String letterSource, String letterDest) {
            String sourceXPATH = getLetterXPATH(letterSource);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(sourceXPATH)));
            String targetXPATH = tree.getLetterXPATH(letterDest);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(targetXPATH)));
            dragAndDrop(driver.findElement(By.xpath(sourceXPATH)), driver.findElement(By.xpath(targetXPATH)));
        }

        public List<String> getEnglishLettersList() {
            List<String> actual = new ArrayList<>(32);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LETTER_LIST));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LETTER_LIST));
            for(WebElement item: driver.findElements(LETTER_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }

        private String getLetterXPATH(String letter) {
            return "//div[@id='gridview-1021']//tr[td[contains(@class, 'x-grid-cell-last')]/div[contains(text(), '" +
                    letter + "')]]/td[contains(@class, 'x-grid-cell-first')]/div/div";
        }
    }

    private void rightClick(WebElement element) {
        try {
            Actions action = new Actions(driver).contextClick(element);
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

    private void dragAndDrop(WebElement source, WebElement target) {
        (new Actions(driver)).dragAndDrop(source, target).perform();
    }
}