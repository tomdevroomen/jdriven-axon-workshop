package com.jdriven.axon.workshop.shoppingcart.makers;

import com.jdriven.axon.workshop.shoppingcart.domain.CreateShoppingCartCommand;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;

import java.util.UUID;

import static com.natpryce.makeiteasy.Property.newProperty;
import static java.util.UUID.randomUUID;

public class CreateShoppingCartCommandMakers {
    public static final Property<CreateShoppingCartCommand, UUID> id = newProperty();

    public static Instantiator<CreateShoppingCartCommand> createShoppingCartCommand = lookup -> new CreateShoppingCartCommand(
            lookup.valueOf(id, randomUUID())
    );
}
