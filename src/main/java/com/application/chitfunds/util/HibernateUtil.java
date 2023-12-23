/*
 * package com.application.chitfunds.util;
 * 
 * import java.util.Properties; import javax.sql.DataSource; import
 * org.hibernate.SessionFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.ComponentScan; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.jdbc.datasource.DriverManagerDataSource; import
 * org.springframework.orm.hibernate5.HibernateTransactionManager; import
 * org.springframework.orm.hibernate5.LocalSessionFactoryBean;
 * 
 * @PropertySource(value = { "classpath:application.properties" })
 * 
 * @Configuration class HibernateUtil { private static SessionFactory
 * sessionFactory;
 * 
 * @Value("${spring.datasource.url}") String url ;
 * 
 * @Value("${spring.datasource.driver-class-name}") String driver;
 * 
 * @Value("${spring.datasource.username}") String userName;
 * 
 * @Value("${spring.datasource.password}") String pass;
 * 
 * @Value("${spring.jpa.properties.hibernate.dialect}") String dialect;
 * 
 * @Bean public DataSource getDataSource() { DriverManagerDataSource dataSource
 * = new DriverManagerDataSource(url, userName, pass);
 * dataSource.setDriverClassName(driver); return dataSource; }
 * 
 * @Bean public LocalSessionFactoryBean sessionFactory() {
 * LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
 * factory.setDataSource(getDataSource());
 * factory.setHibernateProperties(hibernateProperties());
 * factory.setPackagesToScan(new String[] { "com.application.chitfunds.entites"
 * }); return factory; }
 * 
 * private Properties hibernateProperties() { Properties properties = new
 * Properties(); properties.put("hibernate.dialect", dialect);
 * properties.put("hibernate.hbm2ddl.auto", "update");
 * properties.put("hibernate.show_sql", "true");
 * properties.put("hibernate.format_sql", "true"); return properties; }
 * 
 * @Bean
 * 
 * @Autowired public HibernateTransactionManager
 * transactionManager(SessionFactory factory) { HibernateTransactionManager
 * transactionManager = new HibernateTransactionManager();
 * transactionManager.setSessionFactory(factory); return transactionManager; } }
 */