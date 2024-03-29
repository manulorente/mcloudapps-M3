package es.codeurjc.orderservice.sm;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import es.codeurjc.orderservice.types.OrderEventEnum;
import es.codeurjc.orderservice.types.OrderStatusEnum;


@Configuration
@EnableStateMachineFactory
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum>{


    private final Action<OrderStatusEnum, OrderEventEnum>  reserveCreditAction;
    private final Action<OrderStatusEnum, OrderEventEnum>  creditFailureAction;
    private final Action<OrderStatusEnum, OrderEventEnum>  allocateOrderAction;
    private final Action<OrderStatusEnum, OrderEventEnum>  allocationFailureAction;
	private final Action<OrderStatusEnum, OrderEventEnum>  allocateDeliveryOrderAction;
	private final Action<OrderStatusEnum, OrderEventEnum>  allocationDeliveryFailureAction;
    
    public OrderStateMachineConfig(Action<OrderStatusEnum, OrderEventEnum> reserveCreditAction,
								   Action<OrderStatusEnum, OrderEventEnum> creditFailureAction,
								   Action<OrderStatusEnum, OrderEventEnum> allocateOrderAction,
								   Action<OrderStatusEnum, OrderEventEnum> allocationFailureAction,
								   Action<OrderStatusEnum, OrderEventEnum> allocateDeliveryOrderAction, Action<OrderStatusEnum, OrderEventEnum> allocationDeliveryFailureAction) {
    	
    	this.reserveCreditAction = reserveCreditAction;
    	this.creditFailureAction = creditFailureAction;
    	this.allocateOrderAction = allocateOrderAction;
    	this.allocationFailureAction = allocationFailureAction;
		this.allocateDeliveryOrderAction = allocateDeliveryOrderAction;
		this.allocationDeliveryFailureAction = allocationDeliveryFailureAction;
	}
    
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
        	.states(EnumSet.allOf(OrderStatusEnum.class))        
        	.initial(OrderStatusEnum.NEW)
            .end(OrderStatusEnum.APPROVED)
            .end(OrderStatusEnum.REJECTED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
    	
    	transitions.withExternal()
	        .source(OrderStatusEnum.NEW).target(OrderStatusEnum.ALLOCATION_PENDING)
	        .event(OrderEventEnum.ALLOCATE_ORDER)
	        .action(allocateOrderAction)
	   .and().withExternal()
	        .source(OrderStatusEnum.ALLOCATION_PENDING).target(OrderStatusEnum.ALLOCATED)
	        .event(OrderEventEnum.ALLOCATION_SUCCESS)			
	   .and().withExternal()
	        .source(OrderStatusEnum.ALLOCATION_PENDING).target(OrderStatusEnum.REJECTED)
	        .event(OrderEventEnum.ALLOCATION_FAILED)
	        .action(allocationFailureAction)
	   .and().withExternal()
	        .source(OrderStatusEnum.ALLOCATED).target(OrderStatusEnum.ALLOCATION_DELIVERY_PENDING)
	        .event(OrderEventEnum.ALLOCATE_DELIVERY_ORDER)
	        .action(allocateDeliveryOrderAction)
		.and().withExternal()
			.source(OrderStatusEnum.ALLOCATION_DELIVERY_PENDING).target(OrderStatusEnum.ALLOCATED_DELIVERY)
			.event(OrderEventEnum.ALLOCATION_DELIVERY_SUCCESS)
		.and().withExternal()
				.source(OrderStatusEnum.ALLOCATION_DELIVERY_PENDING).target(OrderStatusEnum.REJECTED)
				.event(OrderEventEnum.ALLOCATION_DELIVERY_FAILED)
				.action(allocationDeliveryFailureAction)
		.and().withExternal()
				.source(OrderStatusEnum.ALLOCATED_DELIVERY).target(OrderStatusEnum.CREDIT_PENDING)
				.event(OrderEventEnum.RESERVE_CREDIT)
				.action(reserveCreditAction)
	   .and().withExternal()
	        .source(OrderStatusEnum.CREDIT_PENDING).target(OrderStatusEnum.APPROVED)
	        .event(OrderEventEnum.CREDIT_APPROVED)
	   .and().withExternal()
	        .source(OrderStatusEnum.CREDIT_PENDING).target(OrderStatusEnum.REJECTED)
	        .event(OrderEventEnum.CREDIT_REJECTED)
	        .action(creditFailureAction);
    }
}