package com.prodyna.ecommerce.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Set;

@Configuration
public class BaseConfiguration {

    @Bean
    @Primary
    public ConversionService dtoConverters(Set<Converter<?, ?>> converters) {
        final DefaultConversionService defaultConversionService = new DefaultConversionService();
        converters.forEach(c -> defaultConversionService.addConverter(c));
        return defaultConversionService;
    }
}
