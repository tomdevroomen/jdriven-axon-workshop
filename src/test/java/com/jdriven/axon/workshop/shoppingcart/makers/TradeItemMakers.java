package com.jdriven.axon.workshop.shoppingcart.makers;

import com.jdriven.axon.workshop.shoppingcart.projections.TradeItem;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

import static com.natpryce.makeiteasy.Property.newProperty;

public class TradeItemMakers {
    public static final Property<TradeItem, UUID> id = newProperty();
    public static final Property<TradeItem, String> name = newProperty();
    public static final Property<TradeItem, Integer> valueInCents = newProperty();
    public static final Property<TradeItem, Integer> amount = newProperty();

    public static Instantiator<TradeItem> tradeItem = lookup -> new TradeItem(
            lookup.valueOf(id, UUID.randomUUID()),
            lookup.valueOf(name, RandomStringUtils.randomAlphabetic(10)),
            lookup.valueOf(valueInCents, new Random().nextInt(250)),
            lookup.valueOf(amount, new Random().nextInt(10))
    );
}
