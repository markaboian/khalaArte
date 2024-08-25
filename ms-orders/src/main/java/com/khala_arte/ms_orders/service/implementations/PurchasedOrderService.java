package com.khala_arte.ms_orders.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khala_arte.ms_orders.domain.PurchasedOrder;
import com.khala_arte.ms_orders.dto.PurchasedOrderDTO;
import com.khala_arte.ms_orders.dto.user.UserDTO;
import com.khala_arte.ms_orders.repository.IPurchasedOrderRepository;
import com.khala_arte.ms_orders.repository.feign.IUserRepository;
import com.khala_arte.ms_orders.service.interfaces.IPurchasedOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchasedOrderService implements IPurchasedOrderService {

    private final IPurchasedOrderRepository purchasedOrderRepository;
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;

    @Transactional
    private PurchasedOrderDTO saveOrder(PurchasedOrderDTO purchasedOrderDTO) {
        UserDTO user = userRepository.getUserById(purchasedOrderDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with id: " + purchasedOrderDTO.getUserId());
        }
        PurchasedOrder purchasedOrder = mapper.convertValue(purchasedOrderDTO, PurchasedOrder.class);

        purchasedOrder.setUser_id(purchasedOrderDTO.getUserId());

        purchasedOrderRepository.save(purchasedOrder);

        return mapper.convertValue(purchasedOrder, PurchasedOrderDTO.class);
    }

    @Override
    public Optional<PurchasedOrderDTO> getOrderById(Long id) {
        return purchasedOrderRepository.findById(id)
                .map(purchasedOrder -> mapper.convertValue(purchasedOrder, PurchasedOrderDTO.class));
    }

    @Override
    public Optional<PurchasedOrderDTO> getOrderByOrderDate(Date orderDate) {
        return purchasedOrderRepository.getOrderByOrderDate(orderDate)
                .map(purchasedOrder -> mapper.convertValue(purchasedOrder, PurchasedOrderDTO.class));
    }

    @Override
    public Set<PurchasedOrderDTO> getAllOrders() {
        return purchasedOrderRepository.findAll().stream()
                .map(purchasedOrder -> mapper.convertValue(purchasedOrder, PurchasedOrderDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public PurchasedOrderDTO addOrder(PurchasedOrderDTO purchasedOrderDTO) {
        return saveOrder(purchasedOrderDTO);
    }

    @Override
    public PurchasedOrderDTO updateOrder(PurchasedOrderDTO purchasedOrderDTO) {
        return saveOrder(purchasedOrderDTO);
    }

    @Override
    public void deleteOrderById(Long id) {
        purchasedOrderRepository.deleteById(id);
    }
}
