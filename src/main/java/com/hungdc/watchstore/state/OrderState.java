package com.hungdc.watchstore.state;

import com.hungdc.watchstore.dtos.OrderStatus;

public interface OrderState {
    void handleRequest(OrderStatus order);
}
