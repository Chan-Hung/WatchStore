package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.OrderStatus;
import com.hungdc.watchstore.dtos.order.OrderDto;
import com.hungdc.watchstore.entities.Order;
import com.hungdc.watchstore.repositories.OrderRepository;
import com.hungdc.watchstore.services.order.OrderService;
import com.hungdc.watchstore.services.orderState.CancelledState;
import com.hungdc.watchstore.services.orderState.CompletedState;
import com.hungdc.watchstore.services.orderState.PlacedState;
import com.hungdc.watchstore.services.orderState.ShippedState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderService.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable String id, @Valid @RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderService.update(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable String id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/process")
    public ResponseEntity<String> processOrder(@PathVariable String id, @Valid @RequestBody OrderDto dto){
        Order order = getOrder(id).getBody();
        OrderStatus orderStatus = new OrderStatus();
        if (dto.getStatus().equals("pending")) {
            orderStatus.setState(new PlacedState());
        } else if (dto.getStatus().equals("shipped")) {
            orderStatus.setState(new ShippedState());
        } else if (dto.getStatus().equals("completed")) {
            orderStatus.setState(new CompletedState());
        }else if (dto.getStatus().equals("canceled")) {
            orderStatus.setState(new CancelledState());
        }
        orderStatus.process();
        order.setStatus(dto.getStatus());
        orderRepository.save(order);
        return ResponseEntity.ok("Đã xử lý đơn hàng thành công");
    }
}
