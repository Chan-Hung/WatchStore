package com.hungdc.watchstore.services.order;

import com.hungdc.watchstore.dtos.OrderStatus;
import com.hungdc.watchstore.dtos.order.OrderDto;
import com.hungdc.watchstore.entities.Order;
import com.hungdc.watchstore.exceptions.InvalidException;
import com.hungdc.watchstore.exceptions.NotFoundException;
import com.hungdc.watchstore.repositories.OrderRepository;
import com.hungdc.watchstore.services.orderState.PlacedState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Đơn đặt hàng có id %s không tồn tại", id)));
    }

    @Override
    public Order create(OrderDto dto) {
        Order order = new Order();
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email đặt hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getShippingFee())) {
            throw new InvalidException("Phí vận chuyển không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getItems())) {
            throw new InvalidException("Các mặt hàng không được bỏ trống");
        }

        order.setEmail(dto.getEmail().trim());
        order.setShippingFee(dto.getShippingFee());
        order.setItems(dto.getItems());
        order.setStatus("pending");
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setState(new PlacedState());
        orderStatus.process();
        orderRepository.save(order);
        return order;
    }


    @Override
    public Order update(String id, OrderDto dto) {
        Order order = getOrder(id);

        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email đặt hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getShippingFee())) {
            throw new InvalidException("Phí vận chuyển không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getItems())) {
            throw new InvalidException("Các mặt hàng không được bỏ trống");
        }

        order.setEmail(dto.getEmail().trim());
        order.setShippingFee(dto.getShippingFee());
        order.setItems(dto.getItems());
        order.setStatus("pending");

        orderRepository.save(order);
        return order;
    }

    @Override
    public Order delete(String id) {
        Order order = getOrder(id);
        this.orderRepository.delete(order);
        return order;
    }
}
