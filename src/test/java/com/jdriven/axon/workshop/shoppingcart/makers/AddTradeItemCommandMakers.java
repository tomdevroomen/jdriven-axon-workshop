package com.jdriven.axon.workshop.shoppingcart.makers;

import com.jdriven.axon.workshop.shoppingcart.domain.AddTradeItemCommand;
import com.jdriven.axon.workshop.shoppingcart.projections.TradeItem;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;

import java.util.UUID;

import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.Property.newProperty;

public class AddTradeItemCommandMakers {
    public static final Property<AddTradeItemCommand, UUID> shoppingCartId = newProperty();
    public static final Property<AddTradeItemCommand, TradeItem> tradeItem = newProperty();

    public static Instantiator<AddTradeItemCommand> addTradeItemCommand = lookup -> new AddTradeItemCommand(
            lookup.valueOf(shoppingCartId, UUID.randomUUID()),
            lookup.valueOf(tradeItem, an(TradeItemMakers.tradeItem).make())
    );
}
