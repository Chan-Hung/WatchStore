package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.order.OrderDto;
import com.hungdc.watchstore.entities.Order;
import com.hungdc.watchstore.services.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable String id, @Valid @RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable String id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }
}
