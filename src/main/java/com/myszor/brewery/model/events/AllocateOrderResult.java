package com.myszor.brewery.model.events;

import com.myszor.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderResult {

    private BeerOrderDto beerOrderDto;
    private boolean allocationError;
    private boolean pendingInventory;

}
