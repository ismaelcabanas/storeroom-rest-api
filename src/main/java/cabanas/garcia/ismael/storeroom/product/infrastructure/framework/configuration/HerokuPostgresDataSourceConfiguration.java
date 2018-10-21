package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("heroku")
public class HerokuPostgresDataSourceConfiguration {
  @Bean
  public DataSource dataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
                               @Value("${storeroom.database.schema}") String schema)
          throws URISyntaxException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://"
            + dbUri.getHost()
            + ':'
            + dbUri.getPort()
            + dbUri.getPath()
            + "?currentSchema="
            + schema;

    DataSourceBuilder factory = DataSourceBuilder
            .create()
            .url(dbUrl)
            .username(username)
            .password(password)
            .driverClassName(driverClassName);
    return factory.build();
  }
}
