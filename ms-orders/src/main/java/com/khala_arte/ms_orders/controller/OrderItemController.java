package com.khala_arte.ms_orders.controller;

import com.khala_arte.ms_orders.dto.OrderItemDTO;
import com.khala_arte.ms_orders.service.implementations.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/getOrderItemById/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Long id) {
        ResponseEntity<?> response = null;
        Optional<OrderItemDTO> orderItemDTO = orderItemService.getOrderItemById(id);

        if (orderItemDTO.isPresent()) {
            response = new ResponseEntity<>(orderItemDTO, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>("Order item with the id of: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrderItems() {
        ResponseEntity<?> response = null;
        Set<OrderItemDTO> orderItemDTOS = orderItemService.getAllOrderItems();

        if (orderItemDTOS.isEmpty()) {
            response = new ResponseEntity<>(orderItemDTOS, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>("No order items found.", HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO newOrderItemDTO = orderItemService.addOrderItem(orderItemDTO);
            return new ResponseEntity<>("Order item created successfully. - " + newOrderItemDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating an order item. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO updatedOrderItemDTO = orderItemService.updateOrderItem(orderItemDTO);
            return new ResponseEntity<>("Order item updated successfully. - " + updatedOrderItemDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the order item. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderItemById(@PathVariable Long id) {
        try {
            orderItemService.deleteOrderItemById(id);
            return new ResponseEntity<>("Order item deleted successfully.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting the order item. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
