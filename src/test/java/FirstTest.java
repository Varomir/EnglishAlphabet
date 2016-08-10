import org.testng.annotations.Test;
import page.objects.HomePage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Features({"Smoke suite"})
public class FirstTest extends TestRunner {

    @Stories({"Default view state"})
    @Description("Check tree view: 'English letter' list and sorted mode")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void defaultEnglishLetterState() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        List<String> expectedLetter = Stream.of("Z","Y","X","W","V","U","T","S","R","Q","P","O","N","M","L","K").collect(Collectors.toList());
        //Sorted ASC
        //List<String> expectedLetter = Stream.of("K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z").collect(Collectors.toList());

        System.out.println("Before test-method01. Thread id is: " + Thread.currentThread().getId());
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat("List 'English letter' size was not as expected", actualLetter, hasSize(16));
        assertThat("List 'English letter' contains some unexpected letter or has unexpected sorted mode.", actualLetter, is(contains(expectedLetter.toArray())));
    }

    @Stories({"Default view state"})
    @Description("Check tree view: 'English alphabet' list and sorted mode")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void defaultEnglishAlphabetState() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        List<String> expected = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J").collect(Collectors.toList());

        System.out.println("Before test-method02. Thread id is: " + Thread.currentThread().getId());
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat("List 'English alphabet' size was not as expected", actualAlphabet, hasSize(10));
        assertThat("List 'English alphabet' contains some unexpected letter or has unexpected sorted mode", actualAlphabet, contains(expected.toArray()));
    }

    @Stories({"Remove item"})
    @Description("Remove item from grid")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void removeLetterFromTheGRID() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        System.out.println("Before test-method03. Thread id is: " + Thread.currentThread().getId());
        homePage.grid.removeLetter("Q");
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat("List 'English letter' size was not as expected", actualLetter, hasSize(15));
    }

    @Stories({"Remove item"})
    @Description("Remove item from tree")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void removeLetterFromTheTREE() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        System.out.println("Before test-method04. Thread id is: " + Thread.currentThread().getId());
        homePage.tree.removeLetter("H");
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat("List 'English alphabet' size was not as expected", actualAlphabet, hasSize(9));
    }

    @Stories({"Drag&Drop item"})
    @Description("Drag and Drip from grid to tree")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void dragAndDropFromGridToTree() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        List<String> expected = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "Q").collect(Collectors.toList());

        System.out.println("Before test-method05. Thread id is: " + Thread.currentThread().getId());
        homePage.grid.dragAndDropToTheTree("Q", "E");
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat("List 'English alphabet' size was not as expected", actualAlphabet, hasSize(11));
        assertThat("List 'English alphabet' contains some unexpected letter or has unexpected sorted mode", actualAlphabet, contains(expected.toArray()));
    }

    @Stories({"Drag&Drop item"})
    @Description("Drag and Drip from tree to grid")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void dragAndDropFromTreeToGrid() {
        List<String> expectedLetter = Stream.of("Z","Y","X","W","V","U","T","S","R","Q","P","O","N","M","L","K","E").collect(Collectors.toList());
        //Sorted ASC
        //List<String> expectedLetter = Stream.of("E","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z").collect(Collectors.toList());
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);

        System.out.println("Before test-method06. Thread id is: " + Thread.currentThread().getId());
        homePage.tree.dragAndDropToTheGrid("E", "M");
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat("List 'English letter' size was not as expected", actualLetter, hasSize(17));
        assertThat("List 'English letter' contains some unexpected letter or has unexpected sorted mode.", actualLetter, is(contains(expectedLetter.toArray())));
    }
}