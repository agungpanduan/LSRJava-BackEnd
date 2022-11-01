package com.lsr.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
public class TransactionManagersConfig {

	final Logger logger = LoggerFactory.getLogger(TransactionManagersConfig.class);
	@Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    private DataSource dataSource;
 
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = 
            new JpaTransactionManager();
            tm.setEntityManagerFactory(entityManagerFactory);
            tm.setDataSource(dataSource);
        return tm;
    }
	
	
	
}