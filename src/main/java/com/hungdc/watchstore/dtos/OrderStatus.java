package com.hungdc.watchstore.dtos;

import com.hungdc.watchstore.services.orderState.PlacedState;
import com.hungdc.watchstore.state.OrderState;

public class OrderStatus {
    private OrderState currentState;
    public OrderStatus(){
        currentState = new PlacedState();
    }
    public void setState(OrderState state){
        this.currentState = state;
    }
    public void process(){
        currentState.handleRequest(this);
    }
}
