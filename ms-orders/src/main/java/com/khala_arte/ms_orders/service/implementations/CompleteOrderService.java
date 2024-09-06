package com.khala_arte.ms_orders.service.implementations;

import com.khala_arte.ms_orders.domain.CompleteOrder;
import com.khala_arte.ms_orders.domain.OrderItem;
import com.khala_arte.ms_orders.dto.CompleteOrderDTO;
import com.khala_arte.ms_orders.dto.OrderItemDTO;
import com.khala_arte.ms_orders.dto.user.UserDTO;
import com.khala_arte.ms_orders.repository.ICompleteOrderRepository;
import com.khala_arte.ms_orders.repository.feign.IUserRepository;
import com.khala_arte.ms_orders.service.interfaces.ICompleteOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompleteOrderService implements ICompleteOrderService {

    private final ICompleteOrderRepository completeOrderRepository;
    private final IUserRepository userRepository;

    private CompleteOrderDTO saveCompleteOrder(CompleteOrderDTO completeOrderDTO) {

        UserDTO user = userRepository.getUserById(completeOrderDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User with id of: " + completeOrderDTO.getUserId() + " not found.");
        }

        CompleteOrder completeOrder = new CompleteOrder();
        completeOrder.setUserId(completeOrderDTO.getUserId());
        completeOrder.setTotalAmount(completeOrderDTO.getTotalAmount());
        completeOrder.setShippingAddress(completeOrderDTO.getShippingAddress());
        completeOrder.setStatus(completeOrderDTO.getStatus());

        List<OrderItem> orderItems = completeOrderDTO.getOrderItemDTOS().stream()
                .map(dto -> convertToOrderItem(dto, completeOrder))
                .toList();

        completeOrder.setOrderItems(orderItems);

        CompleteOrder savedOrder = completeOrderRepository.save(completeOrder);
        return convertCompleteOrderToDTO(savedOrder);
    }

    private CompleteOrderDTO convertCompleteOrderToDTO(CompleteOrder completeOrder) {

        List<OrderItemDTO> itemsDTO = completeOrder.getOrderItems().stream()
                .map(this::convertOrderItemToDTO)
                .toList();

        return CompleteOrderDTO.builder()
                .id(completeOrder.getId())
                .userId(completeOrder.getUserId())
                .totalAmount(completeOrder.getTotalAmount())
                .shippingAddress(completeOrder.getShippingAddress())
                .status(completeOrder.getStatus())
                .orderItemDTOS(itemsDTO)
                .build();

    }

    private OrderItemDTO convertOrderItemToDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getCompleteOrder().getId())
                .productId(orderItem.getProductId())
                .price(orderItem.getPrice())
                .build();
    }

    private OrderItem convertToOrderItem(OrderItemDTO orderItemDTO, CompleteOrder completeOrder) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDTO.getProductId());
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setCompleteOrder(completeOrder);

        return orderItem;
    }

    @Override
    public Optional<CompleteOrderDTO> getCompleteOrderById(Long id) {
        return completeOrderRepository.findById(id)
                .map(this::convertCompleteOrderToDTO);
    }

    @Override
    public Set<CompleteOrderDTO> getAllCompleteOrders() {
        List<CompleteOrder> completeOrders = completeOrderRepository.findAll();

        return completeOrders.stream()
                .map(this::convertCompleteOrderToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public CompleteOrderDTO addCompleteOrder(CompleteOrderDTO completeOrderDTO) {
        return saveCompleteOrder(completeOrderDTO);
    }

    @Override
    public CompleteOrderDTO updateCompleteOrder(CompleteOrderDTO completeOrderDTO) {
        return saveCompleteOrder(completeOrderDTO);
    }

    @Override
    public void deleteCompleteOrderById(Long id) {
        completeOrderRepository.deleteById(id);
    }
}
