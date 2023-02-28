package com.example.demo.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "com.example.demo.mvc.repository")
public class MyBatisConfiguration {

	/*
	 * @Bean public SqlSessionFactory sqlSessionFactory(@Autowired DataSource
	 * dataSource, ApplicationContext applicationContext) throws Exception {
	 * SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	 * factoryBean.setDataSource(dataSource); factoryBean.setMapperLocations(new
	 * PathMatchingResourcePatternResolver().getResources(
	 * "classpath:mybatis/sql/*.xml")); SqlSessionFactory factory =
	 * factoryBean.getObject();
	 * factory.getConfiguration().setMapUnderscoreToCamelCase(true); return
	 * factoryBean.getObject(); }
	 * 
	 * @Bean public SqlSessionTemplate sqlSessionTemplate(@Autowired
	 * SqlSessionFactory sqlSessionFactory) { return new
	 * SqlSessionTemplate(sqlSessionFactory); }
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mybatis/sql/*.xml"));
		SqlSessionFactory factory = factoryBean.getObject();
		factory.getConfiguration().setMapUnderscoreToCamelCase(true);
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
