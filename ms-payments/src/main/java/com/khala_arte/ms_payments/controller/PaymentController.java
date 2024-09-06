package com.khala_arte.ms_payments.controller;

import com.khala_arte.ms_payments.dto.PaymentDTO;
import com.khala_arte.ms_payments.service.implementations.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        ResponseEntity<?> response = null;
        Optional<PaymentDTO> paymentDTO = paymentService.getPaymentById(id);

        if (paymentDTO.isPresent()) {
            response = new ResponseEntity<>(paymentDTO, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>("Payment with the id of: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPayments() {
        ResponseEntity<?> response = null;
        Set<PaymentDTO> paymentDTOS = paymentService.getAllPayments();

        if (paymentDTOS.isEmpty()) {
            response = new ResponseEntity<>("No payments found.", HttpStatus.NO_CONTENT);
        }
        else {
            response = new ResponseEntity<>(paymentDTOS, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            PaymentDTO newPaymentDTO = paymentService.addPayment(paymentDTO);
            return new ResponseEntity<>("Payment created succesfully. - " + newPaymentDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating a payment. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            PaymentDTO updatedPaymentDTO = paymentService.updatePayment(paymentDTO);
            return new ResponseEntity<>("Payment updated successfully. - " + updatedPaymentDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the payment. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaymentById(@PathVariable Long id) {
        try {
            paymentService.deletePaymentById(id);
            return new ResponseEntity<>("Payment deleted succesfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting the payment. - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
