package cabanas.garcia.ismael.storeroom;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * this class will automatically pick up all *.feature files
 * in src/test/resources and even recurse sub-directories
 */
@RunWith(Karate.class)
@CucumberOptions(features = "classpath:features/")
public final class StoreroomAcceptanceTest {

}
