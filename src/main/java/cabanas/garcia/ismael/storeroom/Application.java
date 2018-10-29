package cabanas.garcia.ismael.storeroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {
        "cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration",
        "cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.configuration",
        "cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.controller",
        "cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration",
        "cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller"}
    )
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {
  public static void main(String[] args) {
    run(args);
  }

  public static ConfigurableApplicationContext run(String[] args) {
    return SpringApplication.run(Application.class, args);
  }

}
