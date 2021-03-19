package com.myszor.beer.order.service.services;

import com.myszor.beer.order.service.domain.BeerOrder;

public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

}
