package com.jdriven.axon.workshop.shoppingcart.configuration;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class AxonConfiguration {

    @Autowired
    public void configure(SimpleCommandBus simpleCommandBus) {
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Bean
    public EventStorageEngine eventStorageEngine(TransactionManager transactionManager, EntityManagerProvider entityManagerProvider) {
        return new JpaEventStorageEngine(entityManagerProvider, transactionManager);
    }

    @Bean
    public EntityManagerProvider entityManagerProvider(EntityManager entityManager) {
        return new SimpleEntityManagerProvider(entityManager);
    }
}
