package com.khala_arte.ms_orders.controller;

import com.khala_arte.ms_orders.dto.OrderItemsDTO;
import com.khala_arte.ms_orders.service.implementations.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @GetMapping("/getOrderItemById/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Long id) {
        ResponseEntity<?> response = null;
        Optional<OrderItemsDTO> orderItem = orderItemsService.getOrderItemById(id);

        if (orderItem.isPresent()) {
            response = new ResponseEntity<>(orderItem, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>("Order item with the id of: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrderItems() {
        ResponseEntity<?> response = null;
        Set<OrderItemsDTO> orderItems = orderItemsService.getAllOrderItems();

        if (orderItems.isEmpty()) {
            response = new ResponseEntity<>("No order items found.", HttpStatus.NO_CONTENT);
        }
        else {
            response = new ResponseEntity<>(orderItems, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrderItem(@RequestBody OrderItemsDTO orderItemDTO) {
        try {
            OrderItemsDTO newOrderItem = orderItemsService.addOrderItem(orderItemDTO);
            return new ResponseEntity<>("Order item created successfully. - " + newOrderItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating an order item. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrderItem(@RequestBody OrderItemsDTO orderItemDTO) {
        try {
            OrderItemsDTO updatedOrderItem = orderItemsService.updateOrderItem(orderItemDTO);
            return new ResponseEntity<>("Order item updated successfully. - " + updatedOrderItem, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the order item. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
