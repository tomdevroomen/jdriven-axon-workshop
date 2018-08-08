package com.jdriven.axon.workshop.shoppingcart.projections;

import com.jdriven.axon.workshop.shoppingcart.domain.ShoppingCartCreatedEvent;
import com.jdriven.axon.workshop.shoppingcart.domain.TradeItemAddedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static java.lang.String.format;

@Component
public class ShoppingCartProjections {

    private final ShoppingCartRepository repo;

    public ShoppingCartProjections(ShoppingCartRepository repo) {
        this.repo = repo;
    }

    @EventHandler
    public void on(ShoppingCartCreatedEvent evt) {
        ShoppingCart cart = new ShoppingCart(evt.getId(), new ArrayList<>());
        repo.save(cart);
    }

    @EventHandler
    public void on(TradeItemAddedEvent evt) {
        ShoppingCart cart = repo.findById(evt.getShoppingCartId())
                .orElseThrow(() -> new EntityNotFoundException(format("Shopping cart [%s] not found", evt.getShoppingCartId())));
        cart.getItems().add(evt.getTradeItem());
    }
}
