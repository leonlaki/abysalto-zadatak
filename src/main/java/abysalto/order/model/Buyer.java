package abysalto.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "buyers")
public class Buyer {

    private Long id;
    private String name;
    private String surname;
    private String title;

}
