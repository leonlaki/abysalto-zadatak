package abysalto.order.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Embeddable
public class Item {

    private String name;

    @NotNull
    private int quantity;

    @Min(1)
    private BigDecimal price;

    //Kada zelimo izracunati ukupnu vrijednost narucenog artikla
    public BigDecimal getItemTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
