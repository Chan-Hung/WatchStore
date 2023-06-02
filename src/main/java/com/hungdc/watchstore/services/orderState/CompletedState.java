package com.hungdc.watchstore.services.orderState;

import com.hungdc.watchstore.dtos.OrderStatus;
import com.hungdc.watchstore.state.OrderState;

public class CompletedState implements OrderState {
    @Override
    public void handleRequest(OrderStatus order) {
        System.out.println("Order is completed. Processing...");
    }
}
