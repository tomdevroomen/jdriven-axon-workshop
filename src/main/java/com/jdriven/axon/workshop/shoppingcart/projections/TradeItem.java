package com.jdriven.axon.workshop.shoppingcart.projections;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Value
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class TradeItem {

    @Id
    UUID id;

    String name;
    int valueInCents;
    int amount;
}
