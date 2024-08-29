package com.khala_arte.ms_orders.controller;

import com.khala_arte.ms_orders.dto.CompleteOrderDTO;
import com.khala_arte.ms_orders.service.implementations.CompleteOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/completeOrder")
public class CompleteOrderController {

    private final CompleteOrderService completeOrderService;

    @GetMapping("/getCompleteOrderById/{id}")
    public ResponseEntity<?> getCompleteOrderById(@PathVariable Long id) {
        ResponseEntity<?> response = null;
        Optional<CompleteOrderDTO> completeOrderDTO = completeOrderService.getCompleteOrderById(id);

        if (completeOrderDTO.isPresent()) {
            response = new ResponseEntity<>(completeOrderDTO, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>("Complete order with id of: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/getCompleteOrderByOrderDate/{orderDate}")
    public ResponseEntity<?> getCompleteOrderByOrderDate(@PathVariable Date orderDate) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/getCompleteOrderByUserId/{userId}")
    public ResponseEntity<?> getCompleteOrderByUserId(@PathVariable Long userId) {
        ResponseEntity<?> response = null;
        List<CompleteOrderDTO> completeOrderList = completeOrderService.getCompleteOrderByUserId(userId);

        if (completeOrderList.isEmpty()) {
            response = new ResponseEntity<>("The user with id of: " + userId + " does not have orders.", HttpStatus.NO_CONTENT);
        }
        else {
            response = new ResponseEntity<>(completeOrderList, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompleteOrders() {
        ResponseEntity<?> response = null;
        Set<CompleteOrderDTO> completeOrderSet = completeOrderService.getAllCompleteOrders();

        if (completeOrderSet.isEmpty()) {
            response = new ResponseEntity<>("No orders found", HttpStatus.NO_CONTENT);
        }
        else {
            response = new ResponseEntity<>(completeOrderSet, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCompleteOrder(@RequestBody CompleteOrderDTO completeOrderDTO) {
        try {
            CompleteOrderDTO newCompleteOrder = completeOrderService.addCompleteOrder(completeOrderDTO);
            return new ResponseEntity<>("Complete order created successfully created: " + newCompleteOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating a new complete order: - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCompleteOrder(@RequestBody CompleteOrderDTO completeOrderDTO) {
        try {
            CompleteOrderDTO updatedCompleteOrder = completeOrderService.updateCompleteOrder(completeOrderDTO);
            return new ResponseEntity<>("Complete order updated successfully created: " + updatedCompleteOrder, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the complete order: - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompleteOrderById(@PathVariable Long id) {
        try {
            completeOrderService.deleteCompleteOrderById(id);
            return new ResponseEntity<>("Complete order with id of: " + id + " deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting the complete order: - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
