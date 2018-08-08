package com.jdriven.axon.workshop.shoppingcart.projections;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, UUID> {}
