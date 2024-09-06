package com.khala_arte.ms_orders.service.implementations;

import com.khala_arte.ms_orders.domain.CompleteOrder;
import com.khala_arte.ms_orders.domain.OrderItem;
import com.khala_arte.ms_orders.dto.CompleteOrderDTO;
import com.khala_arte.ms_orders.dto.OrderItemDTO;
import com.khala_arte.ms_orders.dto.product.ProductDTO;
import com.khala_arte.ms_orders.repository.ICompleteOrderRepository;
import com.khala_arte.ms_orders.repository.IOrderItemRepository;
import com.khala_arte.ms_orders.repository.feign.IProductRepository;
import com.khala_arte.ms_orders.service.interfaces.IOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService implements IOrderItemService {

    private final IOrderItemRepository orderItemRepository;
    private final IProductRepository productRepository;
    private final ICompleteOrderRepository completeOrderRepository;

    private OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO) {

        ProductDTO product = productRepository.getProductById(orderItemDTO.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found with id of: " + orderItemDTO.getProductId());
        }

        CompleteOrder completeOrder = completeOrderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Complete order with id of: " + orderItemDTO.getOrderId() + " not found."));

        OrderItem orderItem = new OrderItem();
        orderItem.setCompleteOrder(completeOrder);
        orderItem.setProductId(product.getId());
        orderItem.setPrice(product.getPrice());

        orderItemRepository.save(orderItem);

        return convertOrderItemToDTO(orderItem);
    }


    private OrderItemDTO convertOrderItemToDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getCompleteOrder().getId())
                .productId(orderItem.getProductId())
                .price(orderItem.getPrice())
                .build();
    }


    @Override
    public Optional<OrderItemDTO> getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(this::convertOrderItemToDTO);
    }

    @Override
    public Set<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream()
                .map(this::convertOrderItemToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public OrderItemDTO addOrderItem(OrderItemDTO orderItemDTO) {
        return saveOrderItem(orderItemDTO);
    }

    @Override
    public OrderItemDTO updateOrderItem(OrderItemDTO orderItemDTO) {
        return saveOrderItem(orderItemDTO);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
