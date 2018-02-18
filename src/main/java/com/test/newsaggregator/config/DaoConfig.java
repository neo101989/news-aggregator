package com.test.newsaggregator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DaoConfig {
    @Autowired
    private Environment _environment;

    @Bean
    public DataSource dataSource() {
        String user = _environment.getProperty("db.username");
        String password = _environment.getProperty("db.password");
        String database = _environment.getProperty("db.name");
        String url = _environment.getProperty("db.url");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDataSourceName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setInitialPoolSize(2);
        dataSource.setCheckoutTimeout(60000);
        dataSource.setAutomaticTestTable("tb_test");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        Properties properties = new Properties();
        String dialect = _environment.getProperty("hibernate.dialect");
        String hbm2ddlAuto = _environment.getProperty("hibernate.hbm2ddl.auto");
        String driver = _environment.getProperty("hibernate.connection.driver_class");
        String showSql = _environment.getProperty("hibernate.show_sql");
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        properties.setProperty("hibernate.connection.driver_class", driver);
        properties.setProperty("hibernate.show_sql", showSql);
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.test.newsaggregator.db.entity");
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
        return hibernateTransactionManager;
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new HibernateObjectMapper();
    }


}