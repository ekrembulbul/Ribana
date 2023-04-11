package com.bulbul.ribana.config.properties.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@SuppressWarnings("unused")
@Configuration
@PropertySource("classpath:settings/request_matchers.properties")
public class RequestMatchersConfig {

    @Value("#{${role.dev}}")
    public List<String> roleDev;

    @Value("#{${role.admin}}")
    public List<String> roleAdmin;

}
