package abysalto.order.service;

import abysalto.order.model.Order;
import abysalto.order.repository.OrderRepository;
import abysalto.order.utility.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CurrencyService currencyService;

    public OrderService(OrderRepository orderRepository, CurrencyService currencyService) {
        this.orderRepository = orderRepository;
        this.currencyService = currencyService;
    }

    //Dodati nove narudžbe
    public Order createOrder(Order order) {

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        order.setOrderStatus(OrderStatus.PENDING);
        order.setTimeOfOrder(LocalDateTime.now());
        order.setCurrency("EUR");
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

    public Order getOrderWithCurrency(Long orderId, String currency) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if(currency == null || currency.equalsIgnoreCase("EUR")) {
            order.setCurrency("EUR");
            return order;
        }

        order.setCurrency(currency.toUpperCase());

        order.setTotalAmount(currencyService.convertFromEuro(order.getTotalAmount(), currency));

        order.getItems().forEach(item -> item.setPrice(currencyService.convertFromEuro(item.getPrice(), currency)));

        return order;
    }

}
