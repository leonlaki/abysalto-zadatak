package abysalto.order.model;

import jakarta.persistence.*;

@Embeddable
public class Buyer {

    private String name;
    private String surname;
    private String title;
    private String email;

    public Buyer() {}

    public Buyer(String name, String surname, String title, String email) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
