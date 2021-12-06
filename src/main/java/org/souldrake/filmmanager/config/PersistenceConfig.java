package org.souldrake.filmmanager.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

/**
 * Configuration class for persistence
 *
 * @author SoulDrake
 * @create 2021-11-20 12:09
 **/

@Configuration
@EnableJpaRepositories(basePackages = {"org.souldrake.**.repository"})
@EnableTransactionManagement
@PropertySource(value = "classpath:database/database.properties")
public class PersistenceConfig {
    private final Environment environment;

    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(DIALECT, environment.getRequiredProperty("hibernate.dialect"));
        properties.put(SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
        properties.put(FORMAT_SQL, environment.getRequiredProperty("hibernate.format_sql"));
        properties.put(USE_SQL_COMMENTS, environment.getRequiredProperty("hibernate.use_sql_comments"));
        properties.put(JPA_PROXY_COMPLIANCE, environment.getRequiredProperty("hibernate.jpa_proxy_compliance"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("database.driver"));
        dataSource.setUrl(environment.getRequiredProperty("database.url"));
        dataSource.setUsername(environment.getRequiredProperty("database.username"));
        dataSource.setPassword(environment.getRequiredProperty("database.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("org.souldrake.**.model");
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaProperties(hibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }


    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
