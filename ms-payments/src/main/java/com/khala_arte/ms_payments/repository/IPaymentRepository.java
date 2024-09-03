package com.khala_arte.ms_payments.repository;

import com.khala_arte.ms_payments.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
