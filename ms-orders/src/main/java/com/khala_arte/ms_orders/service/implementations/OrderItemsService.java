package com.khala_arte.ms_orders.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khala_arte.ms_orders.domain.CompleteOrder;
import com.khala_arte.ms_orders.domain.OrderItems;
import com.khala_arte.ms_orders.dto.OrderItemsDTO;
import com.khala_arte.ms_orders.dto.product.ProductDTO;
import com.khala_arte.ms_orders.repository.ICompleteOrderRepository;
import com.khala_arte.ms_orders.repository.IOrderItemsRepository;
import com.khala_arte.ms_orders.repository.feign.IProductRepository;
import com.khala_arte.ms_orders.service.interfaces.IOrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemsService implements IOrderItemsService {

    private final IOrderItemsRepository orderItemsRepository;
    private final ICompleteOrderRepository completeOrderRepository;
    private final IProductRepository productRepository;
    private final ObjectMapper mapper;

    @Transactional
    private OrderItemsDTO saveOrderItem(OrderItemsDTO orderItemDTO) {
        ProductDTO product = productRepository.getProductById(orderItemDTO.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + orderItemDTO.getProductId());
        }

        CompleteOrder completeOrder = completeOrderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Complete order not found with id: " + orderItemDTO.getOrderId()));


        OrderItems orderItem = mapper.convertValue(orderItemDTO, OrderItems.class);
        orderItem.setPrice(product.getPrice());
        orderItem.setCompleteOrder(completeOrder);

        orderItemsRepository.save(orderItem);

        updateTotalAmount(completeOrder.getId());
        return mapper.convertValue(orderItem, OrderItemsDTO.class);
    }

    private void updateTotalAmount(Long completeOrderId) {
        CompleteOrder completeOrder = completeOrderRepository.findById(completeOrderId)
                .orElseThrow(() -> new RuntimeException("CompleteOrder not found"));

        Double totalAmount = orderItemsRepository.findByCompleteOrderId(completeOrderId)
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        completeOrder.setTotalAmount(totalAmount);
        completeOrderRepository.save(completeOrder);
    }

    @Override
    public Optional<OrderItemsDTO> getOrderItemById(Long id) {
        return orderItemsRepository.findById(id)
                .map(orderItem -> mapper.convertValue(orderItem, OrderItemsDTO.class));
    }

    @Override
    public Set<OrderItemsDTO> getAllOrderItems() {
        return orderItemsRepository.findAll().stream().
                map(orderItem -> mapper.convertValue(orderItem, OrderItemsDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public OrderItemsDTO addOrderItem(OrderItemsDTO orderItemDTO) {
        return saveOrderItem(orderItemDTO);
    }

    @Override
    public OrderItemsDTO updateOrderItem(OrderItemsDTO orderItemDTO) {
        return saveOrderItem(orderItemDTO);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        orderItemsRepository.deleteById(id);
    }
}
