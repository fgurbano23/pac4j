package com.example.demo;

import org.pac4j.core.config.Config;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.pac4j.springframework.web.SecurityInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan (basePackages = "org.pac4j.springframework.web")

public class securityConfig extends WebMvcConfigurerAdapter {
    ConfigBD configBD = new ConfigBD();
    private Config config = configBD.config();
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor(config, "FormClient","admin")).addPathPatterns("localhost:4200");
        // registry.addInterceptor(new SecurityInterceptor(config, "IndirectBasicAuthClient","admin")).addPathPatterns("/basicauth/*");
        registry.addInterceptor(new SecurityInterceptor(config)).addPathPatterns("/protected/*");
        // registry.addInterceptor(new SecurityInterceptor(config, "DirectBasicAuthClient,ParameterClient")).addPathPatterns("/dba/*");
    }

}
