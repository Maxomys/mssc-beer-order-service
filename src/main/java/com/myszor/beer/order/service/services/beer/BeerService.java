package com.myszor.beer.order.service.services.beer;

import com.myszor.beer.order.service.services.beer.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID id);

    Optional<BeerDto> getBeerByUpc(String upc);

}
