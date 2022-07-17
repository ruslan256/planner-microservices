package org.ruslan.todo.mc.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertySourcesPlaceholderConfig {

    private static final String NAME_PROPERTIES_FILE = "application-local.properties";

    public PropertySourcesPlaceholderConfig() {
        super();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{ new ClassPathResource(NAME_PROPERTIES_FILE) };
        pspc.setLocations(resources);
        pspc.setIgnoreResourceNotFound(true);
        pspc.setLocalOverride(true);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        pspc.setTrimValues(true);
        pspc.setOrder(1);
        return pspc;
    }

}