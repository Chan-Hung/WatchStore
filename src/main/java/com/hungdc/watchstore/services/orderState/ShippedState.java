package com.hungdc.watchstore.services.orderState;

import com.hungdc.watchstore.dtos.OrderStatus;
import com.hungdc.watchstore.state.OrderState;

public class ShippedState implements OrderState {
    @Override
    public void handleRequest(OrderStatus order) {
        // Thực hiện các hành động khi ở trạng thái Đang vận chuyển
        System.out.println("Order is shipped. Processing...");
    }
}
