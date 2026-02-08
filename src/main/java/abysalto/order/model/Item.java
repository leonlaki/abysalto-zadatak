package abysalto.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
public class Item {

    private String name;
    private int quantity;
    private BigDecimal price;

    //Kada zelimo izracunati ukupnu vrijednost narucenog artikla
    public BigDecimal getItemTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}
