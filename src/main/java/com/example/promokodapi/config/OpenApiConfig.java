package com.example.promokodapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@OpenAPIDefinition(
        info = @Info(
                title = "Promo Code API documentation",
                version = "v1",
                description = "Doing CRUD operations",
                termsOfService = "Terms of service",
                contact = @Contact(
                        name = "PromoCode promocodlar.uz"
                ),
                license = @License(
                        name = "License by Promocodlar.uz"
                )
        ),
        servers = {
                /*@Server(
                        description = "Dev api",
                        url = "http://18.144.62.147:8081"
                ),*/
                @Server(
                        description = "Dev(test)",
                        url = "http://localhost:9090"
                )
        }


)
public class OpenApiConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }
}