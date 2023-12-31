package com.pxp.lmsleaveservice.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.pxp.lmsleaveservice.repo",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean", transactionManagerRef = "leaveServiceTransactionManager")
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.configuration")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource(dataSourceProperties()))
                .packages("com.pxp.lmsleaveservice.entity")
                .persistenceUnit("leave-service")
                .build();
    }

    @Bean(name = "leaveServiceTransactionManager")
    public JpaTransactionManager leaveServiceTransactionManager(@Qualifier("localContainerEntityManagerFactoryBean") EntityManagerFactory leaveServiceEntityManagerFactory ) {
        return new JpaTransactionManager(leaveServiceEntityManagerFactory);
    }
}
