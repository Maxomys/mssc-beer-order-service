package com.myszor.beer.order.service.sm.actions;

import com.myszor.beer.order.service.config.JmsConfig;
import com.myszor.beer.order.service.domain.BeerOrder;
import com.myszor.beer.order.service.domain.BeerOrderEventEnum;
import com.myszor.beer.order.service.domain.BeerOrderStatusEnum;
import com.myszor.beer.order.service.repositories.BeerOrderRepository;
import com.myszor.beer.order.service.services.BeerOrderManagerImpl;
import com.myszor.beer.order.service.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, beerOrderMapper.beerOrderToDto(beerOrder));

        log.debug("Sent allocation request for order with id: " + beerOrderId);
    }

}
