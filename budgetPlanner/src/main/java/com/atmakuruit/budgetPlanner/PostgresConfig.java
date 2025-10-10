package com.atmakuruit.budgetPlanner;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@Profile("prod")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.atmakuruit.budgetPlanner.repositories",
    entityManagerFactoryRef = "pgEntityManagerFactory",
    transactionManagerRef = "pgTransactionManager"
)
public class PostgresConfig {

    @Bean(name = "pgDataSource")
    @Primary
    public DataSource pgDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/budgetplanner")
                .username("postgres")
                .password("Save@2847")
                .build();
    }

    @Bean(name = "pgEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean pgEntityManagerFactory(
            @Qualifier("pgDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.atmakuruit.budgetPlanner.model");
        em.setPersistenceUnitName("pgsql");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "pgTransactionManager")
    @Primary
    public PlatformTransactionManager pgTransactionManager(
            @Qualifier("pgEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}