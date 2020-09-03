package com.demo.store.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Not using HibernateConfig, using JPA, keeping here for reference
//@Configuration
//@EnableTransactionManagement
public class HibernateConfig {
    //@Autowired
    private ApplicationContext context;
    //@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.demo.store" );
        sessionFactory.setHibernateProperties(hibernateProperties());
 
        return sessionFactory;
    }
    //@Bean
    public DataSource dataSource() {
        DataSource ds = null;
        try {
            ds =  (DataSource) new JndiTemplate().lookup("java:jboss/datasources/storeDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }
    //@Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        hibernateProperties.setProperty(
            "hibernate.hbm2ddl.import_files", "META-INF/import.sql");
        hibernateProperties.setProperty(
               "hibernate.hbm2ddl.show_sql", "true");  
        return hibernateProperties;
    }    
}
