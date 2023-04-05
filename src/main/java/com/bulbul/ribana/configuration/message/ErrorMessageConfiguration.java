package com.bulbul.ribana.configuration.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages/error_messages.properties")
public class ErrorMessageConfiguration {

    @Value("${result.length.and.field.length.must.be.equal}")
    public String resultLengthAndFieldLengthMustBeEqual;

    @Value("${property.and.direction.parameters.are.wrong}")
    public String propertyAndDirectionParametersAreWrong;

}
