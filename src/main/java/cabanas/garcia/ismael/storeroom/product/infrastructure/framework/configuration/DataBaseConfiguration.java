package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

  @Resource
  private DataSource dataSource;

  @Value("${storeroom.database.query.timeout.seconds}")
  private Integer queryTimeout;

  @Bean
  public DataSourceConnectionProvider connectionProvider() {
    return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
  }

  @Bean
  public org.jooq.Configuration config(ConnectionProvider connectionProvider) {
    DefaultConfiguration config = new DefaultConfiguration();
    config.set(connectionProvider);
    config.set(SQLDialect.POSTGRES_9_5);
    config.settings().setQueryTimeout(queryTimeout);
    return config;
  }

  @Bean
  public DSLContext dslContext(org.jooq.Configuration config) {
    return DSL.using(config);
  }

  @Bean
  public NamedParameterJdbcTemplate configureNamedJdbcTemplate(JdbcTemplate jdbcTemplate) {
    if (queryTimeout != null) {
      jdbcTemplate.setQueryTimeout(queryTimeout);
    }
    return new NamedParameterJdbcTemplate(jdbcTemplate);
  }
}
