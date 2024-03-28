package co.baboon.store;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MainConfiguration {
    @Bean
    public ConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }
    
    @Bean
    public org.jooq.Configuration jooqConfiguration(ConnectionProvider connectionProvider) {
        var configuration = new DefaultConfiguration();
        configuration.set(connectionProvider);
        configuration.setSQLDialect(SQLDialect.POSTGRES);
        return configuration;
    }
    
    @Bean
    public DSLContext dslContext(org.jooq.Configuration configuration) { return new DefaultDSLContext(configuration); }
}
