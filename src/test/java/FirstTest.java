import org.testng.annotations.Test;

public class FirstTest extends TestRunner {

    @Test
    public void stubTest01(){
        System.out.println("Before test-method01. Thread id is: " + Thread.currentThread().getId());
        threadLocalDriver.get().get("http://google.com/");
    }

    @Test
    public void stubTest02(){
        System.out.println("Before test-method02. Thread id is: " + Thread.currentThread().getId());
        threadLocalDriver.get().get("http://yahoo.com/");
    }

    @Test
    public void stubTest03(){
        System.out.println("Before test-method03. Thread id is: " + Thread.currentThread().getId());
        threadLocalDriver.get().get("http://ya.ru/");
    }
}
