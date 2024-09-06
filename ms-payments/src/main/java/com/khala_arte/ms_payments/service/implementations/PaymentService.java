package com.khala_arte.ms_payments.service.implementations;

import com.khala_arte.ms_payments.domain.Payment;
import com.khala_arte.ms_payments.dto.PaymentDTO;
import com.khala_arte.ms_payments.dto.completeOrder.CompleteOrderDTO;
import com.khala_arte.ms_payments.dto.user.UserDTO;
import com.khala_arte.ms_payments.repository.IPaymentRepository;
import com.khala_arte.ms_payments.repository.feign.ICompleteOrderRepository;
import com.khala_arte.ms_payments.repository.feign.IUserRepository;
import com.khala_arte.ms_payments.service.interfaces.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;
    private final IUserRepository userRepository;
    private final ICompleteOrderRepository completeOrderRepository;

    private PaymentDTO savePayment(PaymentDTO paymentDTO) {
        UserDTO user = userRepository.getUserById(paymentDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User with the id of: " + paymentDTO.getUserId() + " not found.");
        }

        CompleteOrderDTO completeOrder = completeOrderRepository.getCompleteOrderById(paymentDTO.getOrderId());
        if (completeOrder == null) {
            throw new RuntimeException("Complete order with the id of: " + paymentDTO.getOrderId() + " not found.");
        }

        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setUserId(paymentDTO.getUserId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(paymentDTO.getStatus());

        paymentRepository.save(payment);

        return convertPaymentToDTO(payment);
    }

    private PaymentDTO convertPaymentToDTO(Payment payment) {

        return PaymentDTO.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .build();
    }

    @Override
    public Optional<PaymentDTO> getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(this::convertPaymentToDTO);
    }

    @Override
    public Set<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(this::convertPaymentToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        return savePayment(paymentDTO);
    }

    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {
        return savePayment(paymentDTO);
    }

    @Override
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }
}
