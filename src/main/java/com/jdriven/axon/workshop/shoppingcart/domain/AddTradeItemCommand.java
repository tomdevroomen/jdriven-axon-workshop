package com.jdriven.axon.workshop.shoppingcart.domain;

import com.jdriven.axon.workshop.shoppingcart.projections.TradeItem;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class AddTradeItemCommand {
    @TargetAggregateIdentifier
    UUID shoppingCartId;

    TradeItem tradeItem;
}
