package com.jdriven.axon.workshop.shoppingcart.projections;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Value
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ShoppingCart {

    @Id
    UUID id;

    @OneToMany
    List<TradeItem> items;
}
