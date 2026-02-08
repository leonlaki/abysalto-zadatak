package abysalto.order.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {

    private String city;
    private String postalCode;
    private String street;
    private int streetNumber;

}
