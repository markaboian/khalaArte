package com.khala_arte.ms_orders.controller;

import com.khala_arte.ms_orders.dto.CompleteOrderDTO;
import com.khala_arte.ms_orders.service.implementations.CompleteOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            response = new ResponseEntity<>("Complete order with the id of: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompleteOrders() {
        ResponseEntity<?> response = null;
        Set<CompleteOrderDTO> completeOrderDTOS = completeOrderService.getAllCompleteOrders();

        if (completeOrderDTOS.isEmpty()) {
            response = new ResponseEntity<>("No complete orders found", HttpStatus.NO_CONTENT);
        }
        else {
            response = new ResponseEntity<>(completeOrderDTOS, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCompleteOrder(@RequestBody CompleteOrderDTO completeOrderDTO) {
        try {
            CompleteOrderDTO newCompleteOrderDTO = completeOrderService.addCompleteOrder(completeOrderDTO);
            return new ResponseEntity<>("Complete order created successfully. - " + newCompleteOrderDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating a complete order: - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCompleteOrder(@RequestBody CompleteOrderDTO completeOrderDTO) {
        try {
            CompleteOrderDTO updatedCompleteOrderDTO = completeOrderService.updateCompleteOrder(completeOrderDTO);
            return new ResponseEntity<>("Complete order updated successfully. - " + updatedCompleteOrderDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the complete order. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompleteOrderById(@PathVariable Long id) {
        try {
            completeOrderService.deleteCompleteOrderById(id);
            return new ResponseEntity<>("Complete order with the id of: " + id + " deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting the complete order. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
