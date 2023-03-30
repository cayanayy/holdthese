package com.cayanay.holdthese.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfigurator {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
