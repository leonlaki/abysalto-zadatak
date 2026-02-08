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
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @NotNull
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @NotEmpty
    private List<Item> items;

    //Ukupni iznos narudzbe
    private BigDecimal totalAmount;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(Item::getItemTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
