import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

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
        //threadLocalDriver.get().get("http://yahoo.com/");
    }

    @Test
    public void stubTest03(){
        List<String> expected = Stream.of("A","B","C","D","E","F","G","H","I","J").collect(Collectors.toList());

        System.out.println("Before test-method03. Thread id is: " + Thread.currentThread().getId());
        List<String> actualLetter = homePage.grid.getEnglishLettersList();
        assertThat(actualLetter, hasSize(16));
        //*assertThat(actualLetter, contains(expected.toArray()));
        //threadLocalDriver.get().get("http://ya.ru/");
    }
}
