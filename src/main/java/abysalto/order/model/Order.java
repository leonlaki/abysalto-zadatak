package abysalto.order.model;

import abysalto.order.utility.OrderStatus;
import abysalto.order.utility.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Ime kupca
    @Embedded
    private Buyer buyer;

    //Status (na čekanju, u pripremi, završena)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //Vrijeme narudžbe
    private LocalDateTime timeOfOrder;

    //Način plaćanja
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    //Adresa dostave
    @Embedded
    private Location deliveryAddress;

    //Kontakt broj
    @Pattern(regexp = "^[+0-9]+$")
    private String contactNumber;

    //Napomena
    private String note;

    //Popis artikala s nazivom, količinom i cijenom
    @ElementCollection
    private List<Item> items;

    //Ukupni iznos narudzbe
    private BigDecimal totalAmount;

    private String currency;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    //Izracunati ukupni iznos racuna
    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(Item::getItemTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order(Buyer buyer, OrderStatus orderStatus,
                 LocalDateTime timeOfOrder, PaymentMethod paymentMethod,
                 Location deliveryAddress, String contactNumber,
                 String note, List<Item> items,
                 BigDecimal totalAmount, String currency) {
        this.buyer = buyer;
        this.orderStatus = orderStatus;
        this.timeOfOrder = timeOfOrder;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.contactNumber = contactNumber;
        this.note = note;
        this.items = items;
        this.totalAmount = totalAmount;
        this.currency = currency;
    }

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Location getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Location deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
