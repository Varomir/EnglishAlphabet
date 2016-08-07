import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import spring.constructors.DriverData;

@ContextConfiguration(locations={"/spring-config.xml"})
public class TestRunner extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("driver")
    protected DriverData driverData;
}
