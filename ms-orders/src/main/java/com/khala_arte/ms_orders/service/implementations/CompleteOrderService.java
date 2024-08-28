package com.khala_arte.ms_orders.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khala_arte.ms_orders.domain.CompleteOrder;
import com.khala_arte.ms_orders.dto.CompleteOrderDTO;
import com.khala_arte.ms_orders.dto.user.UserDTO;
import com.khala_arte.ms_orders.repository.ICompleteOrderRepository;
import com.khala_arte.ms_orders.repository.feign.IUserRepository;
import com.khala_arte.ms_orders.service.interfaces.ICompleteOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompleteOrderService implements ICompleteOrderService {

    private final ICompleteOrderRepository completeOrderRepository;
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;

    private CompleteOrderDTO saveCompleteOrder(CompleteOrderDTO completeOrderDTO) {

        UserDTO user = userRepository.getUserById(completeOrderDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with id of: " + completeOrderDTO.getUserId());
        }

        CompleteOrder completeOrder = mapper.convertValue(completeOrderDTO, CompleteOrder.class);
        completeOrderRepository.save(completeOrder);

        return mapper.convertValue(completeOrder, CompleteOrderDTO.class);
    }

    @Override
    public Optional<CompleteOrderDTO> getCompleteOrderById(Long id) {
        return completeOrderRepository.findById(id)
                .map(completeOrder -> mapper.convertValue(completeOrder, CompleteOrderDTO.class));
    }

    @Override
    public Optional<CompleteOrderDTO> getCompleteOrderByOrderDate(Date orderDate) {
        return completeOrderRepository.getCompleteOrderByOrderDate(orderDate)
                .map(completeOrder -> mapper.convertValue(completeOrder, CompleteOrderDTO.class));
    }

    @Override
    public List<CompleteOrderDTO> getCompleteOrderByUserId(Long id) {
        UserDTO user = userRepository.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User with the id of: " + id + " not found.");
        }

        List<CompleteOrder> completeOrdersList = completeOrderRepository.getCompleteOrderByUserId(id);

        return completeOrdersList.stream()
                .map(completeOrder -> mapper.convertValue(completeOrder, CompleteOrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Set<CompleteOrderDTO> getAllCompleteOrders() {
        return completeOrderRepository.findAll()
                .stream().map(completeOrder -> mapper.convertValue(completeOrder, CompleteOrderDTO.class))
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
