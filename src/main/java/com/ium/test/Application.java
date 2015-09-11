package com.ium.test;

import com.ium.test.service.security.SpringSecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaAuditing
public class Application extends SpringBootServletInitializer {
  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.H2);

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setPackagesToScan(getClass().getPackage().getName());
    factoryBean.setMappingResources("orm.xml");
    factoryBean.setJpaVendorAdapter(adapter);
    factoryBean.setDataSource(dataSource);

    return factoryBean;
  }

  @Bean
  SpringSecurityAuditorAware auditorAware() {
    return new SpringSecurityAuditorAware();
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
