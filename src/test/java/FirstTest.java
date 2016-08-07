import org.testng.annotations.Test;

public class FirstTest extends TestRunner {

    @Test
    public void stubTest01(){
        System.out.println("=============================");
        System.out.println("Test thread #1 started");
        System.out.println(driverData.getBrowser());
        System.out.println(driverData.getTimeout());
        System.out.println(System.getProperty("user.dir") + System.getProperty("file.separator") + driverData.getAutRelativePath());
        System.out.println("Test thread #1 finished");
    }

    @Test
    public void stubTest02(){
        System.out.println("=============================");
        System.out.println("Test thread #2 started");
        System.out.println(driverData.getBrowser());
        System.out.println(driverData.getTimeout());
        System.out.println(System.getProperty("user.dir") + System.getProperty("file.separator") + driverData.getAutRelativePath());
        System.out.println("Test thread #2 finished");
    }

    @Test
    public void stubTest03(){
        System.out.println("=============================");
        System.out.println("Test thread #3 started");
        System.out.println(driverData.getBrowser());
        System.out.println(driverData.getTimeout());
        System.out.println(System.getProperty("user.dir") + System.getProperty("file.separator") + driverData.getAutRelativePath());
        System.out.println("Test thread #3 finished");
    }
}
