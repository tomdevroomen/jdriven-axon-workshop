package com.jdriven.axon.workshop.shoppingcart.domain;

import com.jdriven.axon.workshop.shoppingcart.projections.TradeItem;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Slf4j
public class ShoppingCartAggregate {

    @AggregateIdentifier
    private UUID id;

    private List<TradeItem> tradeItems;

    @CommandHandler
    public ShoppingCartAggregate(CreateShoppingCartCommand cmd) {
        apply(new ShoppingCartCreatedEvent(cmd.getId()));
    }

    @CommandHandler
    public void handle(AddTradeItemCommand cmd) {
        apply(new TradeItemAddedEvent(cmd.getShoppingCartId(), cmd.getTradeItem()));
    }

    @EventHandler
    public void on(ShoppingCartCreatedEvent evt) {
        this.id = evt.getId();
        this.tradeItems = new ArrayList<>();
        log.info("Shopping cart {} created", evt.getId());
    }

    @EventHandler
    public void on(TradeItemAddedEvent evt) {
        this.tradeItems.add(evt.getTradeItem());
        log.info("TradeItem {} added to Shopping cart {}", evt.getTradeItem(), evt.getShoppingCartId());
    }
}
