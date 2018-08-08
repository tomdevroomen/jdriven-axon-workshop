package com.jdriven.axon.workshop.shoppingcart.projections;

import com.jdriven.axon.workshop.shoppingcart.domain.ShoppingCartCreatedEvent;
import com.jdriven.axon.workshop.shoppingcart.domain.TradeItemAddedEvent;
import com.jdriven.axon.workshop.shoppingcart.makers.TradeItemMakers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppingCartProjectionsTest {

    @Autowired
    private ShoppingCartRepository repo;

    private ShoppingCartProjections projections;

    @Before
    public void setUp() {
        projections = new ShoppingCartProjections(repo);
    }

    @Test
    public void onShoppingCartCreated() {
        UUID shoppingCartId = randomUUID();
        projections.on(new ShoppingCartCreatedEvent(shoppingCartId));

        repo.findById(shoppingCartId).ifPresentOrElse(
                cart -> assertThat(cart.getId(), is(shoppingCartId)),
                () -> fail("cart not found")
        );
    }

    @Test
    public void onTradeItemAdded() {
        UUID shoppingCartId = randomUUID();
        projections.on(new ShoppingCartCreatedEvent(shoppingCartId));

        TradeItem jDrivenLamzac = an(TradeItemMakers.tradeItem,
                with(TradeItemMakers.name, "JDriven Lamzac")).make();

        projections.on(
                new TradeItemAddedEvent(
                        shoppingCartId,
                        jDrivenLamzac
                )
        );

        repo.findById(shoppingCartId).ifPresentOrElse(
                cart -> {
                    assertThat(cart.getId(), is(shoppingCartId));
                    assertThat(cart.getItems(), hasItem(jDrivenLamzac));
                },
                () -> fail("cart not found")
        );
    }
}
