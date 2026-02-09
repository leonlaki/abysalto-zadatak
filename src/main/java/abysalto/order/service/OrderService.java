package abysalto.order.service;

import abysalto.order.model.Order;
import abysalto.order.repository.OrderRepository;
import abysalto.order.utility.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Dodati nove narudžbe
    public Order createOrder(Order order) {

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        order.setOrderStatus(OrderStatus.PENDING);
        order.setTimeOfOrder(LocalDateTime.now());
        order.calculateTotalAmount();

        return orderRepository.save(order);
    }

    //Pregledavati postojeće narudžbe
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //Promjena statusa narudžbe
    public Order changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found."));

        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    //Sortirati prikaz narudzbi prema ukupnom iznosu
    public List<Order> getOrdersSortedByTotalAmount(boolean ascending) {
        Sort sort = ascending ? Sort.by("totalAmount").ascending()
                : Sort.by("totalAmount").descending();

        return orderRepository.findAll(sort);
    }

}
