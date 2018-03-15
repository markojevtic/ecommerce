package com.prodyna.ecommerce.server.resource.converter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;

import javax.annotation.PostConstruct;

public class ContextAwareConverter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected ConversionService conversionService() {
        return applicationContext.getBean(ConversionService.class);
    }
}
