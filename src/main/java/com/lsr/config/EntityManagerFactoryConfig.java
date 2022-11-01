package com.lsr.config;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class EntityManagerFactoryConfig {

	final Logger logger = LoggerFactory.getLogger(EntityManagerFactoryConfig.class);
	
	@Autowired
    private Environment env;
	
	@Autowired
	private SpringHikariConfig springHikariConfig;
	
	@Bean(name = "dataSource")
	public HikariDataSource dataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		springHikariConfig.copyStateTo(hikariDataSource);
		try {
			hikariDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		hikariDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		hikariDataSource.setUsername(env.getProperty("spring.datasource.username"));
		hikariDataSource.setPassword(env.getProperty("spring.datasource.password"));
		
		return hikariDataSource;
	}
	
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.lsr.entity");
        emf.setJpaVendorAdapter(
            new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
    	jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
    	jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
    	jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
    	emf.setJpaProperties(jpaProperties);
        
        return emf;
    }
	
	
	
}
