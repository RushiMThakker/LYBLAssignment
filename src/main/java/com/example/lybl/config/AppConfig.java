package com.example.lybl.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    /**
     * A method to create a bean of ResourceBundleMessageSource to which will be used to get the
     * messages from the properties file based on the passed labels
     *
     * @return an object of class ResourceBundleMessageSource
     */
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//        source.setBasenames("properties/labels");
//        source.setUseCodeAsDefaultMessage(true);
//        return source;
//    }

    /**
     * A method to create a bean of LocalValidatorFactoryBean which will be used to generate
     * validation messages based on the label mentioned in the properties file
     *
     * @return an object of class LocalValidatorFactoryBean
     */
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        var source = new ResourceBundleMessageSource();
//        source.setBasenames("properties/validation-labels");
//        source.setUseCodeAsDefaultMessage(true);
//
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(source);
//        return bean;
//    }

    /**
     * A method for swagger app related configuration
     *
     * @param appDescription a string contains the description about the application
     * @param appVersion a string contains the app version information
     * @return an object of class OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application-description}") String appDescription,
            @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Loan Engine API")
                                .version(appVersion)
                                .description(appDescription)
                                .termsOfService("http://swagger.io/terms/")
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public Logger log() {
        return LoggerFactory.getLogger("com.example.lybl");
    }

    /**
     * A method to generate a bean of WebMvcConfigurer
     *
     * @return an object of class WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedHeaders("*")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET");
            }
        };
    }

    /**
     * Bean of rest template
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
