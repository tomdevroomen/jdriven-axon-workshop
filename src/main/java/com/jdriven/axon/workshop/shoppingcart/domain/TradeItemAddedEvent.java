package com.jdriven.axon.workshop.shoppingcart.domain;

import com.jdriven.axon.workshop.shoppingcart.projections.TradeItem;
import lombok.Value;

import java.util.UUID;

@Value
public class TradeItemAddedEvent {
    UUID shoppingCartId;
    TradeItem tradeItem;
}
