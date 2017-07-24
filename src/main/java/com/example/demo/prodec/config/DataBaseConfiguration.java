package com.example.demo.prodec.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean SFB = new LocalSessionFactoryBean();
		SFB.setDataSource(dataSource());
		SFB.setPackagesToScan("com.example.demo.prodec.model");
		SFB.setHibernateProperties(hibernateProperties());
		return SFB;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource DS = new DriverManagerDataSource();
		DS.setDriverClassName("com.mysql.jdbc.Driver");
		DS.setUrl("jdbc:mysql://localhost:3306/joshuacurso");
		DS.setUsername("joshuaCurso");
		DS.setPassword("joshuaCurso");
		return DS;
	}

	public Properties hibernateProperties() {
		Properties P = new Properties();
		P.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		P.put("show_sql", "true");
		return P;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager HT = new HibernateTransactionManager();
		HT.setSessionFactory(sessionFactory().getObject());
		return HT;
	}

}
