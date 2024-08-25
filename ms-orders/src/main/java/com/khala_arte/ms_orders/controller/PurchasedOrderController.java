package com.khala_arte.ms_orders.controller;

import com.khala_arte.ms_orders.dto.PurchasedOrderDTO;
import com.khala_arte.ms_orders.service.implementations.PurchasedOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class PurchasedOrderController {

    private final PurchasedOrderService purchasedOrderService;

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        ResponseEntity<?> response = null;

        Optional<PurchasedOrderDTO> order = purchasedOrderService.getOrderById(id);
        if (order.isPresent()) {
            response = new ResponseEntity<>(order, HttpStatus.OK);
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with the id of: " + id + " not found.");
        }
        return response;
    }

    @GetMapping("/getOrderByOrderDate/{orderDate}")
    public ResponseEntity<?> getOrderByOrderDate(@PathVariable Date orderDate) {
        ResponseEntity<?> response = null;

        Optional<PurchasedOrderDTO> order = purchasedOrderService.getOrderByOrderDate(orderDate);
        if (order.isPresent()) {
            response = new ResponseEntity<>(order, HttpStatus.OK);
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with the date of: " + orderDate + " not found.");
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        ResponseEntity<?> response = null;

        Set<PurchasedOrderDTO> orders = purchasedOrderService.getAllOrders();
        if (orders.isEmpty()) {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("No orders found.");
        }
        else {
            response = new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody PurchasedOrderDTO purchasedOrderDTO) {
        try {
            PurchasedOrderDTO newOrder = purchasedOrderService.addOrder(purchasedOrderDTO);
            return new ResponseEntity<>("Order created successfully. - " + purchasedOrderDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating an order. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody PurchasedOrderDTO purchasedOrderDTO) {
        try {
            PurchasedOrderDTO updatedOrder = purchasedOrderService.updateOrder(purchasedOrderDTO);
            return new ResponseEntity<>("Order udpated successfully. - " + updatedOrder, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the order. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        try {
            purchasedOrderService.deleteOrderById(id);
            return new ResponseEntity<>("Order deleted successfully.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting order with the id of: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
