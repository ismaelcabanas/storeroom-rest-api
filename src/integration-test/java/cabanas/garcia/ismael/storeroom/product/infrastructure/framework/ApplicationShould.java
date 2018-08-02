package cabanas.garcia.ismael.storeroom.product.infrastructure.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("integration-test")
public class ApplicationShould {
  @Test
  public void contextLoad() {

  }
}
