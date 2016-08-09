import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FirstTest extends TestRunner {

    @Test
    public void defaultEnglishLetterState(){
        List<String> expectedLetter = Stream.of("Z","Y","X","W","V","U","T","S","R","Q","P","O","N","M","L","K").collect(Collectors.toList());

        System.out.println("Before test-method01. Thread id is: " + Thread.currentThread().getId());
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat(actualLetter, hasSize(16));
        assertThat(actualLetter, is(contains(expectedLetter.toArray())));
    }

    @Test
    public void defaultEnglishAlphabetState(){
        List<String> expected = Stream.of("A","B","C","D","E","F","G","H","I","J").collect(Collectors.toList());

        System.out.println("Before test-method02. Thread id is: " + Thread.currentThread().getId());
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat(actualAlphabet, hasSize(10));
        assertThat(actualAlphabet, contains(expected.toArray()));
    }

    @Test
    public void removeLetterFromTheGRID(){
        System.out.println("Before test-method03. Thread id is: " + Thread.currentThread().getId());
        homePage.grid.removeLetter("Q");
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat(actualLetter, hasSize(15));
    }

    @Test
    public void removeLetterFromTheTREE() {
        System.out.println("Before test-method04. Thread id is: " + Thread.currentThread().getId());
        homePage.tree.removeLetter("H");
        List<String> actualAlphabet = homePage.tree.getAlhpabetList();
        assertThat(actualAlphabet, hasSize(9));
    }
}
