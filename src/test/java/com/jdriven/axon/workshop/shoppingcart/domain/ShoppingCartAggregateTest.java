package com.jdriven.axon.workshop.shoppingcart.domain;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static com.jdriven.axon.workshop.shoppingcart.makers.AddTradeItemCommandMakers.addTradeItemCommand;
import static com.jdriven.axon.workshop.shoppingcart.makers.CreateShoppingCartCommandMakers.createShoppingCartCommand;
import static com.natpryce.makeiteasy.MakeItEasy.an;

public class ShoppingCartAggregateTest {

    private FixtureConfiguration<ShoppingCartAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(ShoppingCartAggregate.class);
    }

    @Test
    public void shoppingCartShouldBeCreated() {
        CreateShoppingCartCommand cmd = an(createShoppingCartCommand).make();
        fixture.givenNoPriorActivity()
                .when(cmd)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ShoppingCartCreatedEvent(cmd.getId()));
    }

    @Test
    public void tradeItemShouldBeAdded() {
        AddTradeItemCommand cmd = an(addTradeItemCommand).make();
        fixture.given(new ShoppingCartCreatedEvent(cmd.getShoppingCartId()))
                .when(cmd)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new TradeItemAddedEvent(cmd.getShoppingCartId(), cmd.getTradeItem()));
    }
}
