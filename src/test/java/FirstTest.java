import org.testng.annotations.Test;

public class FirstTest extends TestRunner {

    @Test
    public void stubTest01(){
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method01. Thread id is: " + id);
    }

    @Test
    public void stubTest02(){
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method02. Thread id is: " + id);
    }

    @Test
    public void stubTest03(){
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method03. Thread id is: " + id);
    }
}
