import org.testng.annotations.Test;
import page.objects.HomePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FirstTest extends TestRunner {

    @Test
    public void defaultEnglishLetterState() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        List<String> expectedLetter = Stream.of("Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q", "P", "O", "N", "M", "L", "K").collect(Collectors.toList());
        //Sorted ASC
        //List<String> expectedLetter = Stream.of("K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z").collect(Collectors.toList());

        System.out.println("Before test-method01. Thread id is: " + Thread.currentThread().getId());
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat("List 'English letter' size was not as expected", actualLetter, hasSize(16));
        assertThat("List 'English letter' contains some unexpected letter or has unexpected sorted mode", actualLetter, is(contains(expectedLetter.toArray())));
    }

    @Test
    public void defaultEnglishAlphabetState() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        List<String> expected = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J").collect(Collectors.toList());

        System.out.println("Before test-method02. Thread id is: " + Thread.currentThread().getId());
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat("List 'English alphabet' size was not as expected", actualAlphabet, hasSize(10));
        assertThat("List 'English alphabet' contains some unexpected letter or has unexpected sorted mode", actualAlphabet, contains(expected.toArray()));
    }

    @Test
    public void removeLetterFromTheGRID() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        System.out.println("Before test-method03. Thread id is: " + Thread.currentThread().getId());
        homePage.grid.removeLetter("Q");
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat("List 'English letter' size was not as expected", actualLetter, hasSize(15));
    }

    @Test
    public void removeLetterFromTheTREE() {
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);
        System.out.println("Before test-method04. Thread id is: " + Thread.currentThread().getId());
        homePage.tree.removeLetter("H");
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat("List 'English alphabet' size was not as expected", actualAlphabet, hasSize(9));
    }

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

    @Test
    public void dragAndDropFromTreeToGrid() {
        List<String> expectedLetter = Stream.of("Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q", "P", "O", "N", "M", "L", "K", "E").collect(Collectors.toList());
        //Sorted ASC
        //List<String> expectedLetter = Stream.of("E","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z").collect(Collectors.toList());
        HomePage homePage = new HomePage(threadLocalDriver.get(), driverData);

        System.out.println("Before test-method06. Thread id is: " + Thread.currentThread().getId());
        homePage.tree.dragAndDropToTheGrid("E", "M");
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat("List 'English letter' size was not as expected", actualLetter, hasSize(17));
        assertThat("List 'English letter' contains some unexpected letter or has unexpected sorted mode", actualLetter, is(contains(expectedLetter.toArray())));
    }
}