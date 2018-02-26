package sample.framework.spring.selenium;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"sample.framework", "sample.application"})
@PropertySource({"classpath:env/${test.env}.properties", "classpath:browser.properties"})
// Properties of test environment;
// test.env must be defined, e.g. by -Dtest.env=.. in VM variables
public class SeleniumTestConfiguration {
	 @Bean
	    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
	        return new ThreadScopeRegisteringBeanFactoryPostProcessor();
	    }
}
