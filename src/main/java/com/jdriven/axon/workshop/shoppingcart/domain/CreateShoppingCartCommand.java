package com.jdriven.axon.workshop.shoppingcart.domain;

import lombok.Value;
import org.axonframework.commandhandling.model.AggregateIdentifier;

import javax.validation.Valid;
import java.util.UUID;

@Value
public class CreateShoppingCartCommand {
    @AggregateIdentifier
    UUID id;
}
