package abysalto.order.controller;

import abysalto.order.model.Order;
import abysalto.order.service.OrderService;
import abysalto.order.utility.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Dodavanje nove narudzbe
    @Operation(summary = "Create a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order created."),
            @ApiResponse(responseCode = "400", description = "Invalid order data.")
    })
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    //Pregled svih narudzbi
    @Operation(summary = "Get all orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully.")
    })
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Change order status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order status successfully updated."),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PatchMapping("/{orderId}/status")
    //Promjena statusa određene narudžbe
    public ResponseEntity<Order> changeOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus) {
        Order updatedOrder = orderService.changeOrderStatus(orderId, orderStatus);
        return ResponseEntity.ok(updatedOrder);
    }

    @Operation(summary = "Get orders sorted by total amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orders sorted successfully.")
    })
    @GetMapping("/sorted")
    //Soritranje narudzbi prema ukupnom iznosu
    public ResponseEntity<List<Order>> getOrderSortedByTotalAmount(@RequestParam(defaultValue = "true") boolean ascending) {
        return ResponseEntity.ok(orderService.getOrdersSortedByTotalAmount(ascending));
    }

}
