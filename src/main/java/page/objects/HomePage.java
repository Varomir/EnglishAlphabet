package page.objects;

import core.WebDriverUtils;
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
    protected WebDriverUtils utils;
    private static final By ROOT = By.cssSelector("#app-main-1010");
    public LeftPanel tree;
    public RightPanel grid;

    public HomePage(WebDriver driver, DriverData driverConfig){
        this.driver = driver;
        wait = new WebDriverWait(driver, driverConfig.getTimeout());
        utils = new WebDriverUtils(driver, wait);
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
            utils.rightClick(utils.getElement(letterRow));
            utils.getElement(DELETE_MENU).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(letterRow)));
        }

        public void dragAndDropToTheGrid(String letterSource, String letterDest) {
            String sourceXPATH = getLetterXPATH(letterSource);
            System.out.println(sourceXPATH);
            String targetXPATH = grid.getLetterXPATH(letterDest);
            System.out.println(targetXPATH);
            utils.dragAndDrop(utils.getElement(sourceXPATH), utils.getElement(targetXPATH));
            String insertedLetter = grid.getLetterXPATH(letterSource);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(insertedLetter)));
        }

        public List<String> getAlhpabetList() {
            List<String> actual = new ArrayList<>(32);
            for(WebElement item: utils.getElements(ALPHABET_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }

        private String getLetterXPATH(String letter) {
            return "//tbody[@id='treeview-1017-body']/tr/td/div/span[text() = '" + letter + "']";
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
            utils.getElement(letterCheckbox).click();
            utils.getElement(REMOVE_BTN).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(letterCheckbox)));
        }

        public void dragAndDropToTheTree(String letterSource, String letterDest) {
            String sourceXPATH = getLetterXPATH(letterSource);
            String targetXPATH = tree.getLetterXPATH(letterDest);
            utils.dragAndDrop(utils.getElement(sourceXPATH), utils.getElement(targetXPATH));
            String insertedLetter = tree.getLetterXPATH(letterSource);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(insertedLetter)));
        }

        public List<String> getEnglishLettersList() {
            List<String> actual = new ArrayList<>(32);
            for(WebElement item: utils.getElements(LETTER_LIST)) {
                actual.add(item.getText().replaceAll(" ", ""));
            }
            return actual;
        }

        private String getLetterXPATH(String letter) {
            return "//div[@id='gridview-1021']//tr[td[contains(@class, 'x-grid-cell-last')]/div[contains(text(), '" +
                    letter + "')]]/td[contains(@class, 'x-grid-cell-first')]/div/div";
        }
    }
}