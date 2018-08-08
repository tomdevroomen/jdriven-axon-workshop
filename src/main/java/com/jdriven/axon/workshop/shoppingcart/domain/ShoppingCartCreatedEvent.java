package com.jdriven.axon.workshop.shoppingcart.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class ShoppingCartCreatedEvent {
    UUID id;
}
