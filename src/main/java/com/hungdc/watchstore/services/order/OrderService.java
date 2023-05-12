package com.hungdc.watchstore.services.order;

import com.hungdc.watchstore.dtos.category.CategoryDto;
import com.hungdc.watchstore.dtos.order.OrderDto;
import com.hungdc.watchstore.entities.Order;
import com.hungdc.watchstore.entities.embedded.Item;

public interface OrderService {
    Order getOrder(String id);

    Order create (OrderDto dto);

    Order update (String id, OrderDto dto);

    Order delete (String id);
}
