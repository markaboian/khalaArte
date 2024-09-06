package com.khala_arte.ms_payments.service.interfaces;

import com.khala_arte.ms_payments.dto.PaymentDTO;

import java.util.Optional;
import java.util.Set;

public interface IPaymentService {
    Optional<PaymentDTO> getPaymentById(Long id);
    Set<PaymentDTO> getAllPayments();

    PaymentDTO addPayment(PaymentDTO paymentDTO);
    PaymentDTO updatePayment(PaymentDTO paymentDTO);

    void deletePaymentById(Long id);
}
